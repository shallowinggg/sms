package io.github.shallowinggg.sms.api.controller;

import io.github.shallowinggg.sms.api.dto.CaptchaBuildResult;
import io.github.shallowinggg.sms.api.dto.SmsCodeSendCommand;
import io.github.shallowinggg.sms.api.dto.SmsCodeSendResult;
import io.github.shallowinggg.sms.api.dto.VerificationConfigResult;
import io.github.shallowinggg.sms.api.service.IVerifyService;
import io.github.shallowinggg.sms.model.VerificationConfig;
import io.github.shallowinggg.sms.service.IVerificationConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author ding shimin
 */
@RequestMapping("/api")
@RestController
public class ApiController {

    private final IVerificationConfigService verificationConfigService;

    private final IVerifyService verifyService;

    @Autowired
    public ApiController(IVerificationConfigService verificationConfigService,
                         IVerifyService verifyService) {
        this.verificationConfigService = verificationConfigService;
        this.verifyService = verifyService;
    }


    @GetMapping("/verificationConfig/{id}")
    public ResponseEntity<VerificationConfigResult> queryVerificationConfig(@PathVariable long id) {
        if (id < 0) {
            return ResponseEntity.badRequest().build();
        }

        Optional<VerificationConfig> config = verificationConfigService.findOnlineById(id);
        if (!config.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        VerificationConfigResult result = config
                .map(c -> new VerificationConfigResult(c.getName(), c.isNeedCaptcha()))
                .get();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/captcha/{id}")
    public ResponseEntity<CaptchaBuildResult> buildCaptcha(@PathVariable long id) {
        if (id < 0) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(verifyService.buildCaptcha(id));
    }

    @PostMapping("/smsCode")
    public ResponseEntity<SmsCodeSendResult> sendSmsCode(@RequestBody SmsCodeSendCommand command) {
        return null;
    }

    @GetMapping("/smsCode")
    public ResponseEntity<Boolean> verifySmsCode() {
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PostMapping("/smsCodeAndProxy")
    public ResponseEntity<Object> verifySmsCodeAndProxy() {
        return null;
    }
}
