package com.softserveinc.dokazovi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

	private ApplicationContext applicationContext;

	@Value("${redis.host:127.0.0.1}")
	private String redisHost;

	@Value("${redis.port:6379}")
	private int redisPort;

	@Value("${redis.enabled:false}")
	private boolean redisEnabled;

	public boolean isRedisEnabled() {
		return redisEnabled;
	}

	// Did this to avoid autowiring
	static class Companion {
		private static JedisConnectionFactory redisConnectionFactory;
	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		if (Companion.redisConnectionFactory == null) {
			RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
			Companion.redisConnectionFactory = new JedisConnectionFactory(config);
		}
		return Companion.redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		if (redisEnabled) {
			RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
			template.setConnectionFactory(Companion.redisConnectionFactory);
			return template;
		}
		return null;
	}

	@Bean
	public CacheManager cacheManager() {
		String[] cacheNames = { "viewCount" };
		if (redisEnabled) {
			RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
					.disableCachingNullValues()
					.entryTtl(Duration.ofMinutes(15))
					.serializeValuesWith(
							RedisSerializationContext.SerializationPair.fromSerializer(
									RedisSerializer.json()));
			return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(Companion.redisConnectionFactory)
					.cacheDefaults(cacheConfiguration).build();
		} else {
			return new ConcurrentMapCacheManager(cacheNames);
		}
	}
}
