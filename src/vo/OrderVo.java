package vo;

import java.util.Vector;

/**
 * orderNo		订单编号
 * orderInfo 	订单详情
 * username		用户名
 * entryTime	用户入住时间
 * lastTime		订单最晚执行时间
 * orderStatus	订单状态
 * @author sunfan314
 */
public class OrderVo extends Vector<String>{
	
	
	public OrderVo(int orderNo,String orderInfo, String username, String entryTime,
			String lastTime, String orderStatus,int price) {
		this.add(String.valueOf(orderNo));
		this.add(orderInfo);
		this.add(username);
		this.add(entryTime);
		this.add(lastTime);
		this.add(orderStatus);
		this.add(String.valueOf(price));
	}
	
	public int getOrderNo(){
		return Integer.valueOf(this.get(0));
	}
	
	public String getOrderInfo(){
		return this.get(1);
	}
	
	public String getUsername(){
		return this.get(2);
	}
	
	public String getEntryTime(){
		return this.get(3);
	}
	
	public String getLastTime(){
		return this.get(4);
	}
	
	public String getOrderStatus(){
		return this.get(5);
	}

	public int getPrice(){
		return Integer.valueOf(this.get(6));
	}
	
	
}
