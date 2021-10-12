package com.example.project_a.redis;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * 序列化工具类
 *
 */
public class SerializerUtil {

    private static final JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();

    /**
     * 序列化对象
     * @param obj 任意类对象
     */
    public static <T> byte[] serialize(T obj){
        try {
            return jdkSerializationRedisSerializer.serialize(obj);
        } catch (Exception e) {
            throw new RuntimeException("序列化失败!", e);
        }
    }

    /**
     * 反序列化对象
     * @param bytes 字节数组
     * @return
     */
    public static <T> T deserialize(byte[] bytes){
        try {
            return (T) jdkSerializationRedisSerializer.deserialize(bytes);
        } catch (Exception e) {
            throw new RuntimeException("反序列化失败!", e);
        }
    }
}
