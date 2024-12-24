package com.eazybytes.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AuditAwareImpls")
public class AuditAwareImpl implements AuditorAware {
    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional getCurrentAuditor() {

        return Optional.of("AccountsMicroservice");
    }
}
