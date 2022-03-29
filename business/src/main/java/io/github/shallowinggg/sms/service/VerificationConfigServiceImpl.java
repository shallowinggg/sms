package io.github.shallowinggg.sms.service;

import io.github.shallowinggg.sms.model.VerificationConfig;
import io.github.shallowinggg.sms.repository.VerificationConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ding shimin
 */
@Service
public class VerificationConfigServiceImpl implements IVerificationConfigService {

    private final VerificationConfigRepository verificationConfigRepository;

    @Autowired
    public VerificationConfigServiceImpl(VerificationConfigRepository verificationConfigRepository) {
        this.verificationConfigRepository = verificationConfigRepository;
    }

    @Override
    public Optional<VerificationConfig> findOnlineById(long id) {
        Optional<VerificationConfig> config = verificationConfigRepository.findById(id);
        return config.filter(VerificationConfig::isAvailable);
    }
}
