/**
 * 
 */
package com.leolian.transdata.subscribe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.python.apache.commons.compress.utils.Lists;

import com.leolian.transdata.mysql.TransferDao;

/**
 * Description: 
 * @author lianliang
 * @date 2018年2月2日 下午3:11:56
 */
public class SubscribeData {
	private String username = "root";
	private String password = "surfilter1218";
	private String url = "jdbc:mysql://localhost:10021/gacenter";
	
	private String filepath = "D:/Temp/JiGuang/subscribe/subscribe_total.txt";
	
	private int pageSize = 10000;
	private Connection connection = null;
	
	/**
	 * 
	 */
	public void subscribeData() {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			TransferDao dao = new TransferDao(url, username, password);
			connection = dao.getConnection();
			int total = queryTotal();
			if(total==0)
				return ;
			int page = total%pageSize==0? total/pageSize:(total/pageSize)+1;
			List<String> result = null;
			String sql = "SELECT imei_bc AS imei, mac_bc AS mac FROM zd_person LIMIT ?,?";
			for(int i=0; i<page; i++) {
				result = Lists.newArrayList();
				int index = i * pageSize;
				statement = connection.prepareStatement(sql);
				statement.setInt(1, index);
				statement.setInt(2, pageSize);
				rs = statement.executeQuery();
				String imei = null, mac = null;
				String[] imeis = null, macs = null;
				while(rs.next()) {
					imei = rs.getString("imei");
					mac = rs.getString("mac");
					if(StringUtils.isBlank(imei) && StringUtils.isBlank(mac)) {
						continue ;
					}
					/*if(StringUtils.isBlank(imei)) {
						imei = "NULL";
					}
					if(StringUtils.isBlank(mac)) {
						mac = "NULL";
					}
					result.add(imei+"\t"+mac);*/
					if(StringUtils.isBlank(imei)) {
						imei = "NULL";
						if(StringUtils.isBlank(mac)) {
							
						} else if(mac.contains(",")) {
							macs = mac.split(",");
							for (String s : macs) {
								result.add(imei+"\t"+s);
							}
						} else {
							result.add(imei+"\t"+mac);
						}
					} else if(imei.contains(",")) {
						imeis = imei.split(",");
						if(StringUtils.isBlank(mac)) {
							mac = "NULL";
							for (String s : imeis) {
								result.add(s+"\t"+mac);
							}
						} else if(mac.contains(",")) {
							macs = mac.split(",");
							int max = Math.max(imeis.length, macs.length);
							String tempImei = null, tempMac = null;
							for(int j=0; j<max; j++) {
								if(j>=imeis.length) {
									tempImei = "NULL";
								} else {
									tempImei = imeis[j];
								}
								if(j>=macs.length) {
									tempMac = "NULL";
								} else {
									tempMac = macs[j];
								}
								result.add(tempImei+"\t"+tempMac);
							}
						} else {
							for (String s : imeis) {
								result.add(s+"\t"+mac);
							}
						}
					} else {
						if(StringUtils.isBlank(mac)) {
							mac = "NULL";
							result.add(imei+"\t"+mac);
						} else if(mac.contains(",")) {
							macs = mac.split(",");
							for (String s : macs) {
								result.add(imei+"\t"+s);
							}
						} else {
							result.add(imei+"\t"+mac);
						}
					}
				}
				write2File(result);
				System.out.println("handle page "+i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection!=null)
					connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param result
	 */
	private void write2File(List<String> result) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filepath), true)));
			String s = null;
			for (int i=0; i<result.size(); i++) {
				s = result.get(i);
				writer.write(s);
				writer.newLine();
			}
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(writer!=null)
					writer.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	private int queryTotal() {
		String sql = "SELECT COUNT(1) AS total FROM zd_person";
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			int total = 0;
			while(rs.next()) {
				total = rs.getInt("total");
			}
			return total;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null!=statement)
					statement.close();
				if(null!=rs)
					rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
}
