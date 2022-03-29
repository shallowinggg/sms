package io.github.shallowinggg.sms.model;

/**
 * @author ding shimin
 */
public class Captcha {

    private final String format;

    private final byte[] content;

    public Captcha(String format, byte[] content) {
        this.format = format;
        this.content = content;
    }

    public static Captcha forImage(byte[] content) {
        return new Captcha("image", content);
    }

    public static Captcha forVoice(byte[] content) {
        return new Captcha("voice", content);
    }

    public String getFormat() {
        return format;
    }

    public byte[] getContent() {
        return content;
    }
}
