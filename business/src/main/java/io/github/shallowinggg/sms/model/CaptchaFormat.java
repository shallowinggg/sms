package io.github.shallowinggg.sms.model;

import io.github.shallowinggg.sms.utils.ImageUtils;

/**
 * @author ding shimin
 */
public enum CaptchaFormat {
    NONE {
        @Override
        public Captcha build(String code) {
            return null;
        }
    },

    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public Captcha build(String code) {
            try {
                byte[] image = ImageUtils.buildImage(120, 40, code);
                return Captcha.forImage(image);
            } catch (Exception e) {
                return null;
            }
        }
    },

    /**
     * 语音验证码
     */
    VOICE {
        @Override
        public Captcha build(String code) {
            return null;
        }
    };

    public abstract Captcha build(String code);
}
