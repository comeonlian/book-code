/**
 * 
 */
package com.leolian.transdata.shuyumem;

/**
 * Description: 
 * @author lianliang
 * @date 2017年12月11日 下午7:56:21
 */
public class Member {
	private String addTime; //业务时间
	private String name; //姓名
	private String cardId; //身份证号
	private String cardType; //证件类型
	private String phone; //联系电话
	private String shop; //办理会员门店
	
	/**
	 * @return the addTime
	 */
	public String getAddTime() {
		return addTime;
	}
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the cardId
	 */
	public String getCardId() {
		return cardId;
	}
	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}
	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the shop
	 */
	public String getShop() {
		return shop;
	}
	/**
	 * @param shop the shop to set
	 */
	public void setShop(String shop) {
		this.shop = shop;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Member [addTime=" + addTime + ", name=" + name + ", cardId=" + cardId + ", cardType=" + cardType
				+ ", phone=" + phone + ", shop=" + shop + "]";
	}
	
}
