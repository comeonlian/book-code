package com.leolian.transdata.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisPool {
	//非切片连接池
	private static JedisPool jedisPool;
	//切片连接池
	private static ShardedJedisPool shardedJedisPool;
	//初始化操作
	static {
		initJedisPool();
		initShardedJedisPool();
	}
	/**
	 * 初始化非切片池
	 */
	private static void initJedisPool(){
		try{
			//连接池配置信息
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(10);
			config.setMinIdle(5);
			config.setMaxWaitMillis(10001);
			//初始化非切片池
			jedisPool = new JedisPool(config, "127.0.0.1", 6379);
		}catch(Exception e){
			System.out.println("---------- 初始化非切片池失败 ----------");
		}
	}
	/**
	 * 初始化切片池
	 */
	private static void initShardedJedisPool(){
		try{
			//连接池配置信息
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(20);
			config.setMinIdle(5);
			config.setMaxWaitMillis(10001);
			config.setTestOnBorrow(false);
			//初始化多个切片
			List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
			shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));
			//初始化切片池
			shardedJedisPool = new ShardedJedisPool(config, shards);
		}catch(Exception e){
			System.out.println("---------- 初始化切片池失败 ----------");
		}
	}
	/**
	 * 获取一个非切片客户端链接
	 * @return
	 */
	public static Jedis getJedis(){
		if(null==jedisPool)
			return null;
		return jedisPool.getResource();
	}
	/**
	 * 归还连接
	 * @param jedis
	 */
	public static void returnJedis(Jedis jedis){
		if(null==jedis)
			return;
		jedis.close();
	}
	/**
	 * 获取一个切片客户端链接
	 * @return
	 */
	public static ShardedJedis getShardedJedis(){
		if(null==shardedJedisPool)
			return null;
		return shardedJedisPool.getResource();
	}
	/**
	 * 归还一个切片客户端连接
	 * @param shardedJedis
	 */
	public static void returnShardedJedis(ShardedJedis shardedJedis){
		if(null==shardedJedis)
			return ;
		shardedJedis.close();
	}
	
	public static void main(String[] args) {
		initJedisPool();
		System.out.println(jedisPool.getResource());
	}
}
