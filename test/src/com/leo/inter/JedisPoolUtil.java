package com.leo.inter;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * JedisPool
 * Description: 
 * @author lianliang
 * @date 2017年11月23日 上午11:14:46
 */
public class JedisPoolUtil {
	private String host;
	private Integer port;
	private JedisPool jedisPool;
	
	/**
	 * @param host
	 * @param port
	 */
	public JedisPoolUtil(String host, Integer port) {
		this.host = host;
		this.port = port;
	}
	
	private JedisPool getPool() {
		if(null == jedisPool){
			synchronized (JedisPoolUtil.class){
				if(null==jedisPool){
					//连接池配置信息
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(10);
					config.setMinIdle(5);
					config.setMaxWaitMillis(10001);
					jedisPool = new JedisPool(config, host, port);
				}
			}
		}
		return jedisPool;
	}
	
	/**
	 * 一个客户端
	 * @return
	 */
	public Jedis getJedis() {
		JedisPool pool = getPool();
		return pool.getResource();
	}
	
	/**
	 * 释放资源
	 * @param jedis
	 */
	public void release(Jedis jedis) {
		jedis.close();
	}
	
}
