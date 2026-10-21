package com.xinfei.common.jackson;

import com.xinfei.utils.common.IdsUtils;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * 用于序列化自增数字的混淆
 */
public class ConfusionSerializer extends ValueSerializer<Object> {

    public void serialize(Object value, JsonGenerator jsonGenerator, SerializationContext serializers) throws JacksonException {
        try {
            if (value != null) {
                jsonGenerator.writeString(IdsUtils.encryptNumber(Long.valueOf(value.toString())));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        serializers.writeValue(jsonGenerator, value);
    }
}
