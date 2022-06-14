package cn.lycan.kk.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Makkapakka
 * @date 2022-6-14
 * @package_name cn.lycan.kk.redis
 * @description 缓存实现:存储对象均被视为 Object，如果存储对象为 String，
 * 可以进一步使用 StringRedisTemplate 来实现更贴合字符串的处理方法。
 */
@Service
public class RedisService {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    // 设置带过期时间的缓存
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }
    
    // 设置缓存
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    
    // 根据 key 获得缓存
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    // 根据 key 删除缓存
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }
    
    // 根据 keys 集合批量删除缓存
    public Long delete(Set<String> keys) {
        return redisTemplate.delete(keys);
    }
    
    // 根据正则表达式匹配 keys 获取缓存
    public Set<String> getKeysByPattern(String pattern) {
        return redisTemplate.keys(pattern);
    }
    
}
