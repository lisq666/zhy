package com.example.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtils {

    public static final String JSON = "JSON";

    public static final String EMPTY = "";
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * Jackson 映射器API，用以实现 JSON 和 Object 之间的转换
     */
    public ObjectMapper objectMapper;
    /**
     * 构造器
     *
     * @param jsonOrXml mapper的类型
     */
    public JsonUtils(String jsonOrXml) {
        if (StringUtils.isBlank(jsonOrXml)) {
            jsonOrXml = JSON;
        }
        if (jsonOrXml.equalsIgnoreCase(JSON)) {
            objectMapper = new ObjectMapper();
        } else {
            objectMapper = new XmlMapper();
        }
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Object --> JSON
     * 忽略 Object 对象中为 null 的属性
     */
    public JsonUtils ignoreEmpty() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return this;
    }

    /**
     * Object --> JSON 方法
     *
     * @param object targetObject instance
     * @return jsonText
     */
    public String serialize(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("toJSON JsonProcessingException：{}", e.getMessage(), e);
            return EMPTY;
        }
    }

    /**
     * JSON --> Object 方法
     *
     * @param source  json字符串
     * @param classes 映射Java Class
     * @param <T>     target Object Type
     * @return target Object Type
     */
    public <T> T deserialize(String source, Class<T> classes) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        try {
            return objectMapper.readValue(source, classes);
        } catch (IOException e) {
            logger.error("toObject IOException：{}", e.getMessage(), e);
            return null;
        }
    }
}
