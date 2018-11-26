package org.sdu.takeout.optimizer;

import java.util.Arrays;

/**
 * @author wyhong
 * @date 2018-11-26
 */
public class OptimizedResult {

	/**
	 * 每行代表一个手机的点餐组合，列代表一个商品价格
	 */
	private double[][] conditionEachPhone;
	/**
	 * 最大满减总额
	 */
	private double reduction;
	/**
	 * 比原先用一部手机点餐优惠多多少
	 */
	private double moreThanOriginal;
	
	public double[][] getConditionEachPhone() {
		return conditionEachPhone;
	}
	public void setConditionEachPhone(double[][] conditionEachPhone) {
		this.conditionEachPhone = conditionEachPhone;
	}
	public double getReduction() {
		return reduction;
	}
	public void setReduction(double reduction) {
		this.reduction = reduction;
	}
	public double getMoreThanOriginal() {
		return moreThanOriginal;
	}
	public void setMoreThanOriginal(double moreThanOriginal) {
		this.moreThanOriginal = moreThanOriginal;
	}
	@Override
	public String toString() {
		return "OptimizedResult [conditionEachPhone="
				+ printTwoDimensionArray(conditionEachPhone) + ", reduction="
				+ reduction + ", moreThanOriginal=" + moreThanOriginal + "]";
	}
	private String printTwoDimensionArray(double[][] array) {
		int i,j;
		for(i = 0; i < array.length; ++i) {
			System.out.print("第"+(i + 1)+"部手机点：");
			for(j = 0; j < array[i].length; ++j) {
				System.out.print(array[i][j] + "元 ");
			}
			System.out.println("的菜品");
		}
		return null;
	}
	
	
}
