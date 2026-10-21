package com.xinfei.common.jackson;

import com.xinfei.model.annotation.IdEncrypt;
import tools.jackson.databind.BeanDescription;
import tools.jackson.databind.DeserializationConfig;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.BeanDeserializerBuilder;
import tools.jackson.databind.deser.SettableBeanProperty;
import tools.jackson.databind.deser.ValueDeserializerModifier;

import java.util.Iterator;

/**
 * 用于过滤反序列化时处理的字段
 */
public class ConfusionDeserializerModifier extends ValueDeserializerModifier {

    @Override
    public BeanDeserializerBuilder updateBuilder(final DeserializationConfig config, final BeanDescription.Supplier beanDescription, final BeanDeserializerBuilder builder) {
        Iterator<SettableBeanProperty> it = builder.getProperties();

        while (it.hasNext()) {
            SettableBeanProperty p = it.next();
            if (!p.getType().getTypeName().contains("Date")
                    && (null != p.getAnnotation(IdEncrypt.class) || p.getName().equalsIgnoreCase("id"))) {
                builder.addOrReplaceProperty(p.withValueDeserializer(new ConfusionDeserializer(p.getValueDeserializer(), p.getType())), true);
            } else if (p.getType().getTypeName().contains("Date")) {
                builder.addOrReplaceProperty(p.withValueDeserializer(new DateDeserializer(p.getValueDeserializer())), true);
            }
        }
        return builder;
    }
}
