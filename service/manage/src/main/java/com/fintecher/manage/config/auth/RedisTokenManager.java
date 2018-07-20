package com.fintecher.manage.config.auth;

import com.alibaba.fastjson.JSON;
import com.fintecher.entity.SysUser;
import com.fintecher.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储和验证token的实现类
 * @author jwdstef
 * @date 2017/7/31.
 */
@Component("tokenManager")
public class RedisTokenManager implements TokenManager {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setRedis(StringRedisTemplate redis) {
        this.stringRedisTemplate = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public String createToken(SysUser user) {
        String token = UUID.randomUUID().toString().replace("-", "");
        String valiToken = String.format("%s_%s", user.getId(), token);
        //存储到redis并设置过期时间
        stringRedisTemplate.opsForValue().set(valiToken, JSON.toJSONString(user), (long)Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return valiToken;
    }

    @Override
    public boolean checkToken(String authentication) {
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        if (StringUtils.isBlank(authentication)) {
            return false;
        }
        return stringRedisTemplate.expire(authentication, (long)Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
    }

    @Override
    public void deleteToken(String authentication) {
        if (StringUtils.isEmpty(authentication)) {
            return;
        }
        String tokenValue = stringRedisTemplate.opsForValue().get(authentication);
        if (StringUtils.isBlank(tokenValue)) {
            return;
        }
        stringRedisTemplate.delete(authentication);
    }
}
