package io.github.shallowinggg.sms.api.service;

import io.github.shallowinggg.sms.api.dto.CaptchaBuildResult;

/**
 * 短信验证服务
 *
 * @author ding shimin
 */
public interface IVerifyService {

    /**
     * 生成captcha
     *
     * @return captcha生成结果
     */
    CaptchaBuildResult buildCaptcha(long configId);

    void sendSmsCode();

    void verifySmsCode(String phone, String code);

    void verifySmsCode();
}
