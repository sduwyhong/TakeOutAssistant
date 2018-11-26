package org.sdu.takeout.optimizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sdu.takeout.query.Order;

/**
 * @author wyhong
 * @date 2018-11-26
 */
public class ElemeOptimizer extends AbstractOptimizer {

	@Override
	protected double computeReduction(double sum, double[] triggerPrice,
			double[] reduction) {
		double result = 0;
		int i;
		if(sum < triggerPrice[0]) return 0;
		for(i = triggerPrice.length - 1; i > 0 ; --i) {
			if(triggerPrice[i] < sum) {
				result = reduction[i];
				break;
			}
		}
		return result;
	}

	@Override
	protected boolean match(List<Integer> reductionList, double[] item, double[][] result) {
		//对0~reductionList.size() - 1，求item一个最小的子序列，使得该子序列的和大于等于reductionList.get(i)
		return match(reductionList, item, 0, result);
	}

	private boolean match(List<Integer> reductionList, double[] item, int i, double[][] result) {
		int j,k;
		boolean hasSolution = false;
		double sum = 0;
		List<Double> tmpList = new ArrayList<Double>();
		for(j = 0; j < item.length; ++j) {
			sum += item[j];
			tmpList.add(item[j]);
			if(sum > reductionList.get(i)) {
				hasSolution = true;
				result[i] = new double[tmpList.size()];
				for(k = 0; k < tmpList.size(); ++k) {
					result[i][k] = tmpList.get(k);
				}
				return i + 1 == reductionList.size() ? true : match(reductionList, newArray(item, j + 1), i + 1, result);
			}
		}
		if(!hasSolution) {
			result = null;
		}
		return hasSolution;
	}

	/**
	 * 返回i~item.length - 1的新子数组
	 * @param item
	 * @param i
	 * @return
	 */
	private double[] newArray(double[] item, int i) {
		int j;
		double[] newArray = new double[item.length - i];
		for(j = i; j < item.length; ++j) {
			newArray[j - i] = item[j];
		}
		return newArray;
	}

	@Override
	protected List<Integer> traceBack(double[] triggerPrice, double[] reduction, double[] maxReduction, int index) {
		List<Integer> combination = new ArrayList<Integer>();
		int i,j;
		for(i = index; i > 0; --i) {
			if(maxReduction[i] == maxReduction[i - 1]) continue;
			for(j = 0; j < triggerPrice.length; ++j) {
				if(maxReduction[(int) (i - triggerPrice[j])] + reduction[j] == maxReduction[i]) {
					combination.add((int)triggerPrice[j]);
					//break之后，外层for循环还是会做一次--i，试一下+1再-1
					i = (int) (i - triggerPrice[j]) + 1;
					break;
				}
			}
		}
		return combination;
	}

	@Override
	protected double[] refinedReduction(double[] reduction, double deliveryPrice) {
		int i;
		double[] copy = Arrays.copyOf(reduction, reduction.length);
		for(i = 0; i < copy.length; ++i) {
			copy[i] -= deliveryPrice;
		}
		return copy;
	}

	@Override
	protected double[] fullBag(double[] weight, double[] value, double volume) {
		//递推关系式：M[i] = max(M[i-1], max(M[i-w[j]] + v[j])), M[i]代表菜品总额为i时，最大的满减总额
		//向下取整试一哈
		int i, j;
		double tmp;
		double[] maxReduction = new double[(int)volume + 1];
		maxReduction[0] = 0;
		for(i = 1; i <= volume; ++i) {
			for(j = 0; j < weight.length; ++j) {
				if(weight[j] > i) continue;
				tmp = maxReduction[(int) (i - weight[j])] + value[j];
				maxReduction[i] = maxReduction[i - 1] > tmp ? maxReduction[i - 1] : tmp;
			}
		}
		return maxReduction;
	}

	@Override
	protected double sumOfItems(double[] item) {
		double sum = 0;
		for(double b : item) {
			sum += b;
		}
		return sum;
	}

	@Override
	protected OptimizedResult fillProperties(double[][] result, Order order, double maxReduction, double sumOfOriginal) {
		OptimizedResult or = new OptimizedResult();
		or.setConditionEachPhone(result);
		or.setReduction(maxReduction);
		or.setMoreThanOriginal(maxReduction - computeReduction(sumOfOriginal, 
								order.getTriggerPrice(), 
								order.getReduction()) - order.getDeliveryPrice());
		return or;
	}

}
