package io.github.shallowinggg.sms.repository;

import io.github.shallowinggg.sms.model.WebHook;
import io.github.shallowinggg.sms.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;

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
        if (attribute == null) {
            return null;
        }
        return JsonUtils.writeSilent(attribute);
    }

    @Override
    public WebHook convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return null;
        }
        return JsonUtils.readSilent(dbData, WebHook.class);
    }
}
