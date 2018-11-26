package org.sdu.takeout.query;

import java.util.Arrays;

/**
 * @author wyhong
 * @date 2018-11-26
 */
public class Order {

	/**
	 * 满减触发价格
	 */
	private double[] triggerPrice;
	/**
	 * 每个满减的优惠金额
	 */
	private double[] reduction;
	/**
	/**
	 * 手机数
	 */
	private int phoneNum;
	/**
	 * 配送费 
	 */
	private double deliveryPrice;
	/**
	 * 点餐的每个菜品价格 
	 */
	private double[] item;
	public double[] getTriggerPrice() {
		return triggerPrice;
	}
	public void setTriggerPrice(double[] triggerPrice) {
		this.triggerPrice = triggerPrice;
	}
	public double[] getReduction() {
		return reduction;
	}
	public void setReduction(double[] reduction) {
		this.reduction = reduction;
	}
	public int getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}
	public double getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public double[] getItem() {
		return item;
	}
	public void setItem(double[] item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "Order [triggerPrice=" + Arrays.toString(triggerPrice)
				+ ", reduction=" + Arrays.toString(reduction) + ", phoneNum="
				+ phoneNum + ", deliveryPrice=" + deliveryPrice + ", item="
				+ Arrays.toString(item) + "]";
	}
	
	
}
