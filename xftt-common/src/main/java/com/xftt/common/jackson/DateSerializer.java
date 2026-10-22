package com.xftt.common.jackson;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.util.Date;

/**
 * 用于处理日期序列化输�?
 */
public class DateSerializer extends ValueSerializer<Object> {

    public void serialize(Object value, JsonGenerator jsonGenerator, SerializationContext serializers) throws JacksonException {
        try {
            if (value != null) {
                jsonGenerator.writeNumber(((Date) value).getTime());
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        serializers.writeValue(jsonGenerator, value);
    }
}
