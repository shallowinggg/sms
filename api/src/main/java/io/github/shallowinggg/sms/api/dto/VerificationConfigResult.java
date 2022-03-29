package io.github.shallowinggg.sms.api.dto;

/**
 * @author ding shimin
 */
public class VerificationConfigResult {

    public final String name;

    public final boolean needCaptcha;

    public VerificationConfigResult(String name, boolean needCaptcha) {
        this.name = name;
        this.needCaptcha = needCaptcha;
    }
}
