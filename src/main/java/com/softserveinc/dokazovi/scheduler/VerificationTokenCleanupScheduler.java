package com.softserveinc.dokazovi.scheduler;

import com.softserveinc.dokazovi.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationTokenCleanupScheduler {
    private final VerificationTokenService verificationTokenService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredTokens() {
        verificationTokenService.deleteExpiredTokens();
    }
}
