//package com.rivers.user.config;
//
//import cn.hutool.core.util.StrUtil;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
//import java.io.IOException;
//
//@Configuration
//public class JacksonProtoBufSupport {
//
//
//    @Bean
//    @SuppressWarnings("unchecked")
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//        return jacksonObjectMapperBuilder -> {
//            jacksonObjectMapperBuilder.featuresToDisable(
//                    JsonGenerator.Feature.IGNORE_UNKNOWN,
//                    MapperFeature.DEFAULT_VIEW_INCLUSION,
//                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
//                    SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
//            );
//            jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
//            jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);//如果字段都是驼峰命名规则，需要这一句
//            jacksonObjectMapperBuilder.modulesToInstall(ProtobufModule.class);
//        };
//    }
//}
