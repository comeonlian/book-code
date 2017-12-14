/**
 * 
 */
package com.leolian.transdata.service;

import java.sql.Connection;
import java.util.List;

import com.leolian.transdata.entity.Position;
import com.leolian.transdata.mysql.TransferDao;
import com.leolian.transdata.redis.JedisPoolUtil;

import redis.clients.jedis.Jedis;

/**
 * Description: 
 * @author lianliang
 * @date 2017年11月23日 上午11:27:20
 */
public class MysqlToRedisService {
	private String url;
	private String username;
	private String password;
	
	private String redisHost;
	
	public MysqlToRedisService(String mysqlHost, String mysqlDb, String redisHost) {
		this.url = "jdbc:mysql://"+mysqlHost+":3306/"+mysqlDb;
		this.username = "root";
		this.password = "surfilter1218";
		this.redisHost = redisHost;
	}
	
	public void transfer() {
		Connection connection = null;
		Jedis jedis = null;
		try {
			// 获取mysql连接
			TransferDao dao = new TransferDao(url, username, password);
			connection = dao.getConnection();
			// redis
			jedis = new JedisPoolUtil(redisHost, 6379).getJedis();
			jedis.select(11);
			
			// lac-sac x-y
			String sql = "SELECT id, lac, sac, x, y, address, province, city, operator, scope FROM position LIMIT ?,?";
			Integer len = 20000;
			List<Position> list = null;
			long startTime, endTime;
			for(int i=0; i<10000; i++) {
				list = dao.query(connection, sql, (i*len), len);
				if(list.size()==0) {
					break ;
				}
				startTime = System.currentTimeMillis();
				for (Position pos : list) {
					jedis.set(pos.getLac()+"-"+pos.getSac(), pos.getX()+"-"+pos.getY());
				}
				endTime = System.currentTimeMillis();
				System.out.println("execute result size: "+list.size()+", insert time: "+(endTime-startTime));
			}
		}catch (Exception e) {
			try {
				if(null!=connection)
					connection.close();
				if(null!=jedis)
					jedis.close();
			}catch (Exception ex) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
