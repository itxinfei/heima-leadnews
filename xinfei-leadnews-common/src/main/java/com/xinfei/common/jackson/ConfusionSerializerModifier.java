package com.xinfei.common.jackson;

import com.xinfei.model.annotation.IdEncrypt;
import tools.jackson.databind.BeanDescription;
import tools.jackson.databind.SerializationConfig;
import tools.jackson.databind.ser.BeanPropertyWriter;
import tools.jackson.databind.ser.ValueSerializerModifier;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于过滤序列化时处理的字�?
 */
public class ConfusionSerializerModifier extends ValueSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                     BeanDescription.Supplier beanDesc, List<BeanPropertyWriter> beanProperties) {
        List<BeanPropertyWriter> newWriter = new ArrayList<>();
        for (BeanPropertyWriter writer : beanProperties) {
            String name = writer.getType().getTypeName();
            if (null == writer.getAnnotation(IdEncrypt.class)
                    && !name.equalsIgnoreCase("Date")
                    && !writer.getName().equalsIgnoreCase("id")) {
                newWriter.add(writer);
            } else if (name.contains("Date")) {
                writer.assignSerializer(new DateSerializer());
                newWriter.add(writer);
            } else {//id  有idEncrypt注解的属性都会走这个逻辑  都会进行加密混淆
                writer.assignSerializer(new ConfusionSerializer());
                newWriter.add(writer);
            }
        }
        return newWriter;
    }


}
