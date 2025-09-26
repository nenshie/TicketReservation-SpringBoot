package com.nevena.service.util;

public final class Preconditions {
    private Preconditions() {}
    public static void check(boolean condition, String message) {
        if (!condition) throw new com.nevena.service.exception.BusinessException(message);
    }
}
