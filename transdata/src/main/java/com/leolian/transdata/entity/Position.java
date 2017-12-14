/**
 * 
 */
package com.leolian.transdata.entity;

/**
 * Description: 
 * @author lianliang
 * @date 2017年11月23日 上午10:57:34
 */
public class Position {
	private String id;
	private String lac;
	private String sac;
	private String x;
	private String y;
	private String address;
	private String province;
	private String city;
	private String operator;
	private String scope;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the lac
	 */
	public String getLac() {
		return lac;
	}
	/**
	 * @param lac the lac to set
	 */
	public void setLac(String lac) {
		this.lac = lac;
	}
	/**
	 * @return the sac
	 */
	public String getSac() {
		return sac;
	}
	/**
	 * @param sac the sac to set
	 */
	public void setSac(String sac) {
		this.sac = sac;
	}
	/**
	 * @return the x
	 */
	public String getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(String x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public String getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(String y) {
		this.y = y;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Position [id=" + id + ", lac=" + lac + ", sac=" + sac + ", x=" + x + ", y=" + y + ", address=" + address
				+ ", province=" + province + ", city=" + city + ", operator=" + operator + ", scope=" + scope + "]";
	}
}
