package io.github.shallowinggg.sms.repository;

import io.github.shallowinggg.sms.model.WebHook;
import io.github.shallowinggg.sms.utils.JsonUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * web hook配置在database中存储为json string
 *
 * @author ding shimin
 */
@Converter
public class WebHookConverter implements AttributeConverter<WebHook, String> {

    @Override
    public String convertToDatabaseColumn(WebHook attribute) {
        return JsonUtils.writeSilent(attribute);
    }

    @Override
    public WebHook convertToEntityAttribute(String dbData) {
        return JsonUtils.readSilent(dbData, WebHook.class);
    }
}
