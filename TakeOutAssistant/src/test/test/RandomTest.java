package test;

import org.junit.Test;
import org.sdu.takeout.optimizer.AbstractOptimizer;
import org.sdu.takeout.optimizer.ElemeOptimizer;
import org.sdu.takeout.optimizer.OptimizedResult;
import org.sdu.takeout.query.Order;

/**
 * @author wyhong
 * @date 2018-11-26
 */
public class RandomTest {

	public static void main(String[] args) {
		test();
	}
	
	
//	@Test
	public static void test() {
		//测试用例：四季粥铺
		Order order = new Order();
		double[] triggerPrice = {25, 55, 80};
		double[] reduction = {16, 23, 40};
		double deliveryPrice = 1;
		int phoneNum = 3;
		double[] item = {8, 16, 7, 5, 3.5, 5, 13, 6.2, 6, 4, 8.9, 4, 8, 4, 13, 11, 3, 5, 7, 9};
		order.setTriggerPrice(triggerPrice);
		order.setReduction(reduction);
		order.setDeliveryPrice(deliveryPrice);
		order.setPhoneNum(phoneNum);
		order.setItem(item);
		
		AbstractOptimizer optimizer = new ElemeOptimizer();
		OptimizedResult optimize = optimizer.optimize(order);
		System.out.println(optimize);
	}
	
}
