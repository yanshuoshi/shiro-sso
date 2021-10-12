package com.example.project_b.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Set;

/**
 * redis工具类
 *
 */
@Component
public class RedisManager {

    @Resource
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    /**
     * 添加缓存数据（给定key已存在，进行覆盖）
     * @param key
     * @param obj
     * @throws DataAccessException
     */
    public <T> void set(String key, T obj) throws DataAccessException{
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = SerializerUtil.serialize(obj);
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.set(bkey, bvalue);
            return null;
        });
    }

    /**
     * 添加缓存数据（给定key已存在，不进行覆盖，直接返回false）
     * @param key
     * @param obj
     * @return 操作成功返回true，否则返回false
     * @throws DataAccessException
     */
    public <T> boolean setNX(String key, T obj) throws DataAccessException{
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = SerializerUtil.serialize(obj);
        boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setNX(bkey, bvalue));

        return result;
    }

    /**
     * 添加缓存数据，设定缓存失效时间
     * @param key
     * @param obj
     * @param expireSeconds 过期时间，单位 秒
     * @throws DataAccessException
     */
    public <T> void setEx(String key, T obj, final long expireSeconds) throws DataAccessException{
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = SerializerUtil.serialize(obj);
        redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            connection.setEx(bkey, expireSeconds, bvalue);
            return true;
        });
    }

    /**
     * 获取key对应value
     * @param key
     * @return
     * @throws DataAccessException
     */
    public <T> T get(final String key) throws DataAccessException{
        byte[] result = redisTemplate.execute((RedisCallback<byte[]>) connection -> connection.get(key.getBytes()));
        if (result == null) {
            return null;
        }
        return SerializerUtil.deserialize(result);
    }

    /**
     * 删除指定key数据
     * @param key
     * @return 返回操作影响记录数
     */
    public Long del(final String key){
        if (StringUtils.isEmpty(key)) {
            return 0l;
        }
        Long delNum = redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] keys = key.getBytes();
            return connection.del(keys);
        });
        return delNum;
    }

    public Set<byte[]> keys(final String key){
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        Set<byte[]> bytesSet = redisTemplate.execute((RedisCallback<Set<byte[]>>) connection -> {
            byte[] keys = key.getBytes();
            return connection.keys(keys);
        });

        return bytesSet;
    }

}

