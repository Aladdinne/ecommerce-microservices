package com.alaeddinehammouda.productservice.storage.exceptions.utils;

import tn.yellowit.jobgate.core.storage.exceptions.CustomException;

import java.util.function.Supplier;

public abstract class CheckerUtils {
    private CheckerUtils() {
    }

    public static boolean returnFalseOrThrowException(Supplier<? extends CustomException> exceptionSupplier, boolean throwExceptionOnFalse) {
        if (throwExceptionOnFalse) {
            throw exceptionSupplier.get();
        }
        return false;
    }
}
