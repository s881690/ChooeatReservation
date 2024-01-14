package chooeat.reservation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	@Value("${redis.host}")
	private String redisHost;

	@Value("${redis.port}")
	private int redisPort;
	
//	@Bean
//	public Jedis jedis() {return new Jedis(redisHost, redisPort);}
   
	    @Bean
	    public JedisPool jedisPool() {
	        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	        // 配置 jedisPoolConfig

			// 使用變量而不是直接的字串
			JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisHost, redisPort);
			return jedisPool;
	    }

}
