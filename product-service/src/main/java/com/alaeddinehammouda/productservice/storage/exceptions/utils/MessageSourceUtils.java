package com.alaeddinehammouda.productservice.storage.exceptions.utils;

import com.alaeddinehammouda.productservice.storage.enums.Locale;
import jakarta.annotation.Nullable;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public abstract class MessageSourceUtils {
    private MessageSourceUtils() {
    }


    public static String fetchMessage(String messageKey, @Nullable Locale locale, @Nullable Object[] args) {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        if (Objects.nonNull(locale)) source.setDefaultLocale(locale.getValue());
        source.setBasename("i18n/messages");
        return source.getMessage(messageKey, args, java.util.Locale.getDefault());

    }


    public static String fetchMessage(String messageKey, @Nullable Locale locale) {
        return fetchMessage(messageKey, locale, null);
    }

    public static String fetchMessage(String messageKey) {
        return fetchMessage(messageKey, null);
    }
}
