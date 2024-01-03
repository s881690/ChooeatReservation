package chooeat.reservation.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

@Component
public class ReservationLock {

	private final JedisPool jedisPool;

	@Autowired
	public ReservationLock(JedisPool jedisPool) {
		this.jedisPool = jedisPool;

	}
	
	public boolean haskey(String key) {
		try (Jedis jedis = jedisPool.getResource()) {
		  jedis.select(3); 
		    boolean exists = jedis.exists(key);
		    if (exists) {
		      return true;
		    } 
		    	 return false;
		    
		}
	}

	public boolean acquireLock(String key, Integer remainSeat) {

		try (Jedis jedis = jedisPool.getResource()) {
			jedis.select(3);
			// 开始事务
			Transaction transaction = jedis.multi();
			// 尝试设置锁的值
			transaction.setnx(key, remainSeat.toString());
			// 设置锁的过期时间
              transaction.expire(key,300);
			// 执行事务
			List<Object> result = transaction.exec();

			// 获取设置锁的结果
			return result != null && !result.isEmpty() && (Long) result.get(0) == 1;
		}
	}

}
