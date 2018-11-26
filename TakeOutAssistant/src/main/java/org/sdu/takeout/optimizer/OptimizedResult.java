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
		printTwoDimensionArray(conditionEachPhone);
		return "OptimizedResult [最优的满减金额(已除去配送费)为="
				+ reduction + "元, 比用一台手机点多优惠=" + moreThanOriginal + "元]";
	}
	private String printTwoDimensionArray(double[][] array) {
		int i,j;
		double sum = 0;
		for(i = 0; i < array.length; ++i) {
			System.out.print("第"+(i + 1)+"部手机点：");
			for(j = 0; j < array[i].length; ++j) {
				sum += array[i][j];
				System.out.print(array[i][j] + "元 ");
			}
			System.out.println("共"+sum+"元的菜品");
			sum = 0;
		}
		return null;
	}
	
	
}
