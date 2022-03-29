package io.github.shallowinggg.sms.service;

import io.github.shallowinggg.sms.model.VerificationConfig;

import java.util.Optional;

/**
 * @author ding shimin
 */
public interface IVerificationConfigService {

    Optional<VerificationConfig> findOnlineById(long id);
}
