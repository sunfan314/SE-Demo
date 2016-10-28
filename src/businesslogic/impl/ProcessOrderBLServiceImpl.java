package businesslogic.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ListModel;

import po.OrderPo;
import po.UserPo;
import data.DataFactory;
import data.OrderService;
import data.UserService;
import data.impl.DataFactoryImpl;
import vo.OrderVo;
import businesslogic.ProcessOrderBLService;

public class ProcessOrderBLServiceImpl implements ProcessOrderBLService{
	
	//酒店编号
	private int hotelId;
	
	private DataFactory dataFactory;
	
	public ProcessOrderBLServiceImpl(){
		this.hotelId=1;
		this.dataFactory=new DataFactoryImpl();	
	}


	public List<OrderVo> getAllOrders() {
		List<OrderPo> list = dataFactory.getOrderData().getAllOrders(hotelId);
		return transfer(list);
	}

	public List<OrderVo> getFinishedOrders() {
		List<OrderPo> list = dataFactory.getOrderData().getFinishedOrders(hotelId);
		return transfer(list);
	}

	public List<OrderVo> getUnfinishedOrders() {
		List<OrderPo> list = dataFactory.getOrderData().getUnFinishedOrders(hotelId);
		return transfer(list);
	}

	public List<OrderVo> getAbnormalOrders() {
		List<OrderPo> list = dataFactory.getOrderData().getAbnormalOrders(hotelId);
		return transfer(list);
	}

	public boolean processUnfinishedOrder(int orderNo) {
		OrderPo order = dataFactory.getOrderData().getOrder(orderNo);
		//未执行订单
		if(order.getStatus() == 0){
			//修改订单信息
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String time=format.format(date);
			dataFactory.getOrderData().finishOrder(orderNo,time);
			//增加用户信用值
			dataFactory.getUserData().addUserCredit(order.getUserId(), order.getPrice());
			return true;
		}
		return false;
	}

	public boolean processAbnormalOrder(int orderNo,String delayTime) {
		OrderPo order = dataFactory.getOrderData().getOrder(orderNo);
		//异常订单
		if(order.getStatus() == 2){
			//修改订单信息
			dataFactory.getOrderData().delayOrder(orderNo, delayTime);
			//恢复用户信用值
			dataFactory.getUserData().addUserCredit(order.getUserId(), order.getPrice());
			return true;
		}
		return false;
	}
	
	private List<OrderVo> transfer(List<OrderPo> orderPoList){
		List<OrderVo> list=new ArrayList<OrderVo>();
		for (OrderPo orderPo : orderPoList) {
			int orderNo = orderPo.getId();
			String orderInfo = orderPo.getOrderInfo();
			//获取用户信息
			int userId = orderPo.getUserId();
			UserPo userPo=dataFactory.getUserData().getUserPo(userId);
			String username = userPo.getUsername();
			int userCredit = userPo.getCredit();
			String entryTime = orderPo.getEntryTime();
			if(entryTime.equals("")){
				entryTime = "尚未入住";
			}
			String lastTime = orderPo.getLastTime();
			String orderStatus = "";
			switch (orderPo.getStatus()) {
			case 0://订单未执行
				orderStatus = "订单未执行";
				break;
			case 1://订单已执行
				orderStatus = "订单已执行";
				break;
			case 2://订单异常
				orderStatus = "订单异常";
				break;
			default:
				orderStatus = "异常订单状态";
				break;
			}
			int orderPrice = orderPo.getPrice();
			
			OrderVo orderVo = new OrderVo(orderNo,orderInfo, username+"("+userCredit+")", entryTime, lastTime, orderStatus,orderPrice);
			list.add(orderVo);
		}
		return list;
	}

}
