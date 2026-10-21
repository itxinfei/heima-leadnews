package com.xinfei.common.jackson;

import com.xinfei.utils.common.IdsUtils;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;

/**
 * 用于反序列化自增数字的混淆解�?
 */
public class ConfusionDeserializer extends ValueDeserializer<Object> {

    ValueDeserializer<Object> deserializer = null;
    JavaType type = null;

    public ConfusionDeserializer(ValueDeserializer<Object> deserializer, JavaType type) {
        this.deserializer = deserializer;
        this.type = type;
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        try {
            if (type != null) {
                if (type.getTypeName().contains("Long")) {
                    return IdsUtils.decryptLong(p.getValueAsString());
                }
                if (type.getTypeName().contains("Integer")) {
                    return IdsUtils.decryptInt(p.getValueAsString());
                }
            }
            return IdsUtils.decryptLong(p.getValueAsString());
        } catch (Exception e) {
            if (deserializer != null) {
                return deserializer.deserialize(p, ctxt);
            } else {
                return p.currentValue();
            }
        }
    }
}
