package businesslogic;

import java.util.List;

import vo.OrderVo;

public interface ProcessOrderBLService {
	
	/**
	 * @return	返回酒店所有订单
	 */
	public List<OrderVo> getAllOrders();
	
	/**
	 * @return	返回酒店已执行订单
	 */
	public List<OrderVo> getFinishedOrders();
	
	/**
	 * @return	返回酒店未执行订单
	 */
	public List<OrderVo> getUnfinishedOrders();
	
	/**
	 * @return	返回酒店异常订单
	 */
	public List<OrderVo> getAbnormalOrders();
	
	/**
	 * 酒店工作人员办理用户入住
	 * @param orderNo	订单编号
	 * @return	返回对未执行订单处理结果
	 */
	public boolean processUnfinishedOrder(int orderNo);
	
	/**
	 * 酒店工作人员为异常订单办理延迟入住
	 * @param orderNo
	 * @return	返回对异常订单处理结果
	 */
	public boolean processAbnormalOrder(int orderNo,String delayTime);
	
	

}
