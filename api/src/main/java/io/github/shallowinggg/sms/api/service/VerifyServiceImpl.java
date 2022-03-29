package io.github.shallowinggg.sms.api.service;

import io.github.shallowinggg.sms.api.dto.CaptchaBuildResult;
import io.github.shallowinggg.sms.model.VerificationConfig;
import io.github.shallowinggg.sms.service.IVerificationConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ding shimin
 */
@Service
public class VerifyServiceImpl implements IVerifyService {

    private final IVerificationConfigService verificationConfigService;

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public VerifyServiceImpl(IVerificationConfigService verificationConfigService,
                             StringRedisTemplate redisTemplate) {
        this.verificationConfigService = verificationConfigService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public CaptchaBuildResult buildCaptcha(long configId) {
        Optional<VerificationConfig> config = verificationConfigService.findOnlineById(configId);
        if (!config.isPresent()) {
            return null;
        }


        return null;
    }

    @Override
    public void sendSmsCode() {

    }

    @Override
    public void verifySmsCode(String phone, String code) {

    }

    @Override
    public void verifySmsCode() {

    }
}
