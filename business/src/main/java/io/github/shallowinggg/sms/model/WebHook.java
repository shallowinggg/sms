package io.github.shallowinggg.sms.model;

import javax.persistence.Converter;
import java.io.Serializable;

/**
 * @author ding shimin
 */
public class WebHook implements Serializable {

    private String method;

    private String url;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
