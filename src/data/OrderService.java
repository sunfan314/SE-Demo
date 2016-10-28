package data;

import java.util.List;

import po.OrderPo;

public interface OrderService {
	
	/**
	 * @param orderNo	订单编号
	 * @return	根据订单编号获取订单
	 */
	public OrderPo getOrder(int orderNo);
	
	/**
	 * @param hotelId	酒店编号
	 * @return	获取酒店所有订单
	 */
	public List<OrderPo> getAllOrders(int hotelId);
	
	/**
	 * @param hotelId	酒店编号
	 * @return	获取酒店所有未执行订单
	 */
	public List<OrderPo> getUnFinishedOrders(int hotelId);
	
	/**
	 * @param hotelId	酒店编号
	 * @return	获取酒店所有已执行订单
	 */
	public List<OrderPo> getFinishedOrders(int hotelId);
	
	/**
	 * @param hotelId	酒店编号
	 * @return	获取酒店所有异常订单
	 */
	public List<OrderPo> getAbnormalOrders(int hotelId);
	
	/**
	 * @param orderId	订单编号
	 * @param entryTime	用户入住时间
	 * @return	执行订单，为用户办理入住
	 */
	public boolean finishOrder(int orderId,String entryTime);
	
	/**
	 * @param orderId	订单编号
	 * @param lastTime	延期后的订单最后执行时间
	 * @return	为异常订单办理延期
	 */
	public boolean delayOrder(int orderId,String lastTime);
	


}
