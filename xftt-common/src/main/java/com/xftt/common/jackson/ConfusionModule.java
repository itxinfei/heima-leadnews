package com.xftt.common.jackson;

import tools.jackson.core.Version;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.JacksonModule;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.json.JsonMapper;

public class ConfusionModule extends JacksonModule {

    public final static String MODULE_NAME = "jackson-confusion-encryption";
    public final static Version VERSION = new Version(1, 0, 0, null, "xftt", MODULE_NAME);

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }

    @Override
    public Version version() {
        return VERSION;
    }

    @Override
    public void setupModule(SetupContext context) {
        context.addSerializerModifier(new ConfusionSerializerModifier());
        context.addDeserializerModifier(new ConfusionDeserializerModifier());
    }

    /**
     * 注册当前模块
     * @return
     */
    public static JsonMapper registerModule(JsonMapper.Builder builder) {
        //CamelCase策略，Java对象属性：personId，序列化后属性：persionId
        //PascalCase策略，Java对象属性：personId，序列化后属性：PersonId
        //SnakeCase策略，Java对象属性：personId，序列化后属性：person_id
        //KebabCase策略，Java对象属性：personId，序列化后属性：person-id
        // 忽略多余字段，抛错
        return builder.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .addModule(new ConfusionModule())
                .build();
    }

}
