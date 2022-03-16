package io.github.shallowinggg.sms.api.service;

/**
 * 短信验证服务
 *
 * @author ding shimin
 */
public interface IVerifyService {

    void buildCaptcha();

    void sendSmsCode();

    void verifySmsCode(String phone, String code);

    void verifySmsCode();
}
