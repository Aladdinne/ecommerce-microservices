package com.alaeddinehammouda.productservice.storage.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.yellowit.jobgate.core.auth.security.AuthFacade;
import tn.yellowit.jobgate.core.auth.security.authorizations.CandidateAccess;
import tn.yellowit.jobgate.core.auth.user.User;
import tn.yellowit.jobgate.core.auth.user.UserRepository;
import tn.yellowit.jobgate.core.storage.CustomFile;
import tn.yellowit.jobgate.core.storage.CustomFileRepository;
import tn.yellowit.jobgate.core.storage.StorageService;
import tn.yellowit.jobgate.core.storage.enums.FileType;
import tn.yellowit.jobgate.core.storage.properties.TunijobsProperties;
import tn.yellowit.jobgate.domain.entities.Company;
import tn.yellowit.jobgate.domain.entities.candidate.Candidate;
import tn.yellowit.jobgate.domain.exceptions.exceptions.CustomException;
import tn.yellowit.jobgate.domain.repositories.CandidateRepository;
import tn.yellowit.jobgate.domain.repositories.CompanyRepository;
import tn.yellowit.jobgate.domain.resumeparser.services.PDFToStringConverterService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/upload-files")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;
    private final CustomFileMapper customFileMapper;
    private final TunijobsProperties tunijobsProperties;
    private final CustomFileRepository customFileRepository;
    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final AuthFacade authFacade;
    private final PDFToStringConverterService PDFToStringConverterService;

    @Transactional
    @PostMapping("")
    public ResponseEntity<List<CustomFileDto>> uploadFiles(@RequestPart("files") MultipartFile[] files,
                                                           @RequestParam(defaultValue = "ANY", name = "accepted-extension") List<String> acceptedExtension,
                                                           @RequestParam(defaultValue = FileType.DEFAULT_VALUE, name = "type", required = false) FileType fileType) {
        List<CustomFile> customFiles = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {
            CustomFile uploadedFile = this.storageService.uploadFile(file, tunijobsProperties.getFile().getPathByFileType(fileType), acceptedExtension.toArray(String[]::new));
            customFiles.add(uploadedFile);
        });
        User user = this.authFacade.getAuthenticated();
        if (FileType.RECRUITER_PHOTO.equals(fileType) || FileType.CANDIDATE_PHOTO.equals(fileType)) {
            if (user.getPhoto() != null) {
                Optional<CustomFile> currentPhoto = this.customFileRepository.findById(user.getPhoto().getId());
                currentPhoto.ifPresent(this.customFileRepository::delete);
            }
            user.setPhoto(customFiles.get(0));
            this.userRepository.save(user);
        }
        if (FileType.COMPANY_LOGO.equals(fileType)) {
            Company company = user.getRecruiter().getCompany();
            company.setLogo(customFiles.get(0));
            this.companyRepository.saveAndFlush(company);
        }
        if (FileType.COMPANY_GALLERY.equals(fileType)) {
            Company company = user.getRecruiter().getCompany();
            company.getImageGallery().add(customFiles.get(0));
            this.companyRepository.saveAndFlush(company);
        }
        return ResponseEntity.ok(customFileMapper.toDto(customFiles));
    }


    @CandidateAccess
    @PostMapping("candidate/")
    public ResponseEntity<List<CustomFileDto>> uploadCV(
            @RequestPart("files") MultipartFile[] files,
            @RequestParam(defaultValue = "ANY", name = "accepted-extension") List<String> acceptedExtension,
            @RequestParam(defaultValue = FileType.DEFAULT_VALUE, name = "type", required = false) FileType fileType) {
        List<CustomFile> customFiles = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {
            CustomFile uploadedFile = this.storageService.uploadFile(file, tunijobsProperties.getFile().getPathByFileType(fileType), acceptedExtension.toArray(String[]::new));
            customFiles.add(uploadedFile);
        });
        User user = this.authFacade.getAuthenticated();
        Optional<Candidate> optionalCandidate = this.candidateRepository.findById(user.getId());
        if (optionalCandidate.isPresent()) {
            Candidate candidate = optionalCandidate.get();
            candidate.setResume(customFiles.get(0));
            candidateRepository.save(candidate);
            return ResponseEntity.ok(customFileMapper.toDto(customFiles));
        }
        throw new CustomException("user not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CandidateAccess
    @PostMapping("candidate/cover-lettre")
    public ResponseEntity<List<CustomFileDto>> changeCandidateCoverLettre(
            @RequestPart("files") MultipartFile[] files,
            @RequestParam(defaultValue = "ANY", name = "accepted-extension") List<String> acceptedExtension,
            @RequestParam(defaultValue = FileType.DEFAULT_VALUE, name = "type", required = false) FileType fileType) {
        List<CustomFile> customFiles = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {
            CustomFile uploadedFile = this.storageService.uploadFile(file, tunijobsProperties.getFile().getPathByFileType(fileType), acceptedExtension.toArray(String[]::new));
            customFiles.add(uploadedFile);
        });
        User user = this.authFacade.getAuthenticated();
        Optional<Candidate> optionalCandidate = this.candidateRepository.findById(user.getId());
        if (optionalCandidate.isPresent()) {
            Candidate candidate = optionalCandidate.get();
            candidate.setCoverLettre(customFiles.get(0));
            candidateRepository.save(candidate);
            return ResponseEntity.ok(customFileMapper.toDto(customFiles));
        }
        throw new CustomException("user not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable("id") Integer fileId) {
        customFileRepository.deleteById(fileId);
        return new ResponseEntity<>("file deleted", HttpStatus.OK);
    }

    @GetMapping("pdf/")
    public String extractTextFromPDF(@RequestPart("cv") MultipartFile cv) throws IOException {
        return this.PDFToStringConverterService.extractTextFromPdf(cv);
    }
}