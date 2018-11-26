package org.sdu.takeout.optimizer;

import java.util.Arrays;
import java.util.List;

import org.sdu.takeout.query.Order;

/**
 * @author wyhong
 * @date 2018-11-26
 */
public abstract class AbstractOptimizer {
	
	/**
	 * 根据订单对象计算最优点餐组合
	 * @param order
	 * @return 一个二维数组，每一行对应一部手机的点餐选择
	 */
	public OptimizedResult optimize(Order order) {
		int i;
		int phoneNum = order.getPhoneNum();
		if(phoneNum < 2) return null;
		List<Integer> reductionList = null;
		double[] item = order.getItem();
		double[] triggerPrice = order.getTriggerPrice();
		double[] reduction = order.getReduction();
		double[][] result = null;
		double sumOfItems;
		//根据满减数据和商品总额，计算一个一维数组M[i]，代表商品总额为i时，最大的优惠金额
		double[] maxReduction = fullBag(triggerPrice, 
										order.getReduction(), 
										sumOfItems = sumOfItems(item));
		//根据M[i]，计算是否存在一种符合条件的点单组合情况
		Arrays.sort(item);
		for(i = maxReduction.length - 1; i > -1; --i) {
			//若组合数 > 手机数则继续循环
			if((i > 0 && maxReduction[i] == maxReduction[i - 1]) || 
					(reductionList = traceBack(triggerPrice, reduction, maxReduction, i)).size() > phoneNum) {
				continue;
			}
			//根据组合情况、商品数据，判断是否存在一个二维数组解
			result = new double[reductionList.size()][];
			System.out.println(reductionList.toString());
			if(match(reductionList, item, result)) {
				break;
			}
		}
		return fillProperties(result, order, maxReduction[i] - reductionList.size() * order.getDeliveryPrice(), sumOfItems);
	}

	/**
	 * 构造最优点餐组合对象
	 * @param result 解
	 * @param order 订单详情
	 * @param maxReduction 最大满减总额
	 * @param sumOfOriginal 原来的商品总价
	 * @return 最优点餐组合对象
	 */
	protected abstract OptimizedResult fillProperties(double[][] result, Order order, double maxReduction, double sumOfOriginal);


	/**
	 * 计算指定金额的满减数额
	 * @param sum 菜品总价
	 * @param triggerPrice 满减触发条件
	 * @param reduction 每个触发条件的满减金额
	 * @return 满减数额
	 */
	protected abstract double computeReduction(double sum, double[] triggerPrice, double[] reduction);

	/**
	 * 计算是否存在一个double类型的二维数组O[i][j]，使得第i部手机点O[i][0~j]菜品时，达到最大优惠
	 * @param reductionList 目前最优满减组合列表
	 * @param item 菜品价格列表
	 * @param result 二维数组解 
	 * @return 是否有解
	 */
	protected abstract boolean match(List<Integer> reductionList, double[] item, double[][] result);

	/**
	 * 对于M[i]，当菜品总额为i时，找到组成最大满减金额的满减触发价格列表
	 * @param maxReduction M[i]本M
	 * @param maxReduction 
	 * @param i 菜品总额
	 * @return 组成最大满减金额的满减触发价格列表
	 */
	protected abstract List<Integer> traceBack(double[] triggerPrice, double[] reduction, double[] maxReduction, int i);

	/**
	 * 修改满减数额，使得对于每一个满减金额r[i], 新的满减金额 = 原满减金额 - 配送费，返回一个新的数组rr[i]，存储新的满减金额，不修改原满减金额数组地址
	 * @param reduction 原满减金额数组
	 * @param deliveryPrice 配送费
	 * @return 一个新的数组rr[i]，存储新的满减金额
	 */
	protected abstract double[] refinedReduction(double[] reduction, double deliveryPrice);

	/**
	 * 完全背包问题，你懂的
	 * @param weight 存储每件物品的重量
	 * @param value 存储每件物品的价值
	 * @param volume 背包大小
	 * @return
	 */
	protected abstract double[] fullBag(double[] weight, double[] value, double volume);

	/**
	 * 计算菜品价格总额
	 * @param item 菜品价格列表
	 * @return 菜品价格总额
	 */
	protected abstract double sumOfItems(double[] item);
}
