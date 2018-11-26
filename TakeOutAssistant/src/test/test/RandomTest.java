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

	@Test
	public void test() {
		//测试用例：四季粥铺
		Order order = new Order();
		double[] triggerPrice = {25, 55};
		double[] reduction = {16, 23};
		double deliveryPrice = 1;
		int phoneNum = 2;
		double[] item = {8, 16, 7, 5, 3.5, 5, 13};
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
