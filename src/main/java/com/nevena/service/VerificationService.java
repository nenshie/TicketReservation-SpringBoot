package com.nevena.service;

public interface VerificationService {
    String verifyByPublicCode(String publicCode, Long adminUserId);
}
