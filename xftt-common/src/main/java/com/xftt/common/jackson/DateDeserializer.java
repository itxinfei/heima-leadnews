package com.xftt.common.jackson;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.util.Date;

/**
 * 用于处理日期输入反序列化
 */
public class DateDeserializer extends ValueDeserializer<Object> {

    ValueDeserializer<Object> deserializer = null;

    public DateDeserializer(ValueDeserializer<Object> deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        try {
            String tmp = "" + p.getValueAsLong();
            if (tmp.length() == 13) {
                return new Date(p.getValueAsLong());
            } else if (tmp.length() == 10) {
                return new Date(p.getValueAsLong() * 1000);
            } else
                return null;
        } catch (Exception e) {
            if (deserializer != null) {
                return deserializer.deserialize(p, ctxt);
            } else {
                return p.currentValue();
            }
        }
    }
}
