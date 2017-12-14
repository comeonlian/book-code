/**
 * 
 */
package com.leolian.transdata.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.leolian.transdata.entity.Position;

/**
 * Description: 
 * @author lianliang
 * @date 2017年11月23日 上午10:39:39
 */
public class TransferDao {
	
	private static String driver = "com.mysql.jdbc.Driver";
	private String url = "";
	private String username = "";
	private String password = "";
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public TransferDao (String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public Connection getConnection(){
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<Position> query (Connection connection, String sql, Integer idx, Integer len) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Position> list = new ArrayList<>(20000);
		try {
			conn = connection;
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idx);
			statement.setInt(2, len);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Position pos = new Position();
				pos.setId(resultSet.getString("id"));
				pos.setLac(resultSet.getString("lac"));
				pos.setSac(resultSet.getString("sac"));
				pos.setX(resultSet.getString("x"));
				pos.setY(resultSet.getString("y"));
				pos.setAddress(resultSet.getString("address"));
				pos.setProvince(resultSet.getString("province"));
				pos.setCity(resultSet.getString("city"));
				pos.setOperator(resultSet.getString("operator"));
				pos.setScope(resultSet.getString("scope"));
				list.add(pos);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null!=statement)
					statement.close();
				if(null!=resultSet)
					resultSet.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
