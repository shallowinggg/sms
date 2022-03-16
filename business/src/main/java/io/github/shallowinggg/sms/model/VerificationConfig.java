package io.github.shallowinggg.sms.model;


import com.google.common.base.Preconditions;
import io.github.shallowinggg.sms.repository.WebHookConverter;

import javax.persistence.*;

/**
 * @author ding shimin
 */
@Entity
public class VerificationConfig {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置描述
     */
    private String description;

    /**
     * 是否需要captcha校验
     */
    private boolean needCaptcha;

    /**
     * captcha格式
     * 1: 图片
     * 2: 语音
     */
    @Enumerated(value = EnumType.STRING)
    private CaptchaFormat captchaFormat;

    /**
     * 每天短信验证码最大发送次数
     */
    private int maxVerifyCount;

    /**
     * web hook
     */
    @Convert(converter = WebHookConverter.class)
    private WebHook callback;

    /**
     * 配置状态
     */
    @Enumerated
    private Status status;

    public boolean isNeedCaptcha() {
        return this.needCaptcha;
    }

    /**
     * 生成对应格式的captcha
     *
     * @param code 验证码
     * @return captcha
     * @throws IllegalStateException 如果当前配置不支持captcha
     */
    public Captcha buildCaptcha(String code) {
        if (!isNeedCaptcha()) {
            throw new IllegalStateException("当前配置不支持captcha");
        }

        return this.captchaFormat.build(code);
    }

    public boolean isAvailable() {
        return this.status == Status.ONLINE;
    }


    // =====================
    // builders
    // =====================

    public static VerificationConfig base(String name, String description) {
        VerificationConfig config = new VerificationConfig();
        config.name = name;
        config.description = description;
        return config;
    }

    public VerificationConfig captcha(CaptchaFormat format) {
        Preconditions.checkNotNull(format, "captcha format must not be null");

        this.needCaptcha = true;
        this.captchaFormat = format;
        return this;
    }

    public VerificationConfig maxVerifyCount(int maxVerifyCount) {
        Preconditions.checkArgument(maxVerifyCount > 0, "maxVerifyCount must be positive");

        this.maxVerifyCount = maxVerifyCount;
        return this;
    }

    public VerificationConfig webHook(WebHook callback) {
        Preconditions.checkArgument(callback != null, "callback must not be null");

        this.callback = callback;
        return this;
    }
}
