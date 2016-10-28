package data.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import po.OrderPo;
import data.OrderService;

public class OrderServiceTxtImpl implements OrderService {
	
	public OrderPo getOrder(int orderNo){
		File file = new File("TxtData/order.txt");
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				
				String[] data = str.split(";");
				if (Integer.valueOf(data[0]) == orderNo) {
					int hotelId = Integer.valueOf(data[1]);
					int orderUserId = Integer.valueOf(data[2]);
					int orderStatus = Integer.valueOf(data[3]);
					String orderEntryTime = data[4];
					String orderLastTime = data[5];
					String orderInfo = data[6];
					int orderPrice = Integer.valueOf(data[7]);
					OrderPo order=new OrderPo(orderNo, hotelId, orderUserId, orderStatus, orderEntryTime, orderLastTime,orderInfo,orderPrice);
					return order;
				}
				str = br.readLine();
				
			}

			br.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<OrderPo> getAllOrders(int hotelId) {
		File file = new File("TxtData/order.txt");
		List<OrderPo> list = new ArrayList<OrderPo>();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				
				String[] data = str.split(";");
				if (Integer.valueOf(data[1]) == hotelId) {
					int orderId = Integer.valueOf(data[0]);
					int orderUserId = Integer.valueOf(data[2]);
					int orderStatus = Integer.valueOf(data[3]);
					String orderEntryTime = data[4];
					String orderLastTime = data[5];
					String orderInfo=data[6];
					int orderPrice = Integer.valueOf(data[7]);
					OrderPo order=new OrderPo(orderId, hotelId, orderUserId, orderStatus, orderEntryTime, orderLastTime,orderInfo,orderPrice);
					list.add(order);
				}
				str = br.readLine();
				
			}

			br.close();
			
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<OrderPo> getUnFinishedOrders(int hotelId) {
		List<OrderPo> list = new ArrayList<OrderPo>();
		List<OrderPo> temp = getAllOrders(hotelId);
		for (OrderPo orderPo : temp) {
			//过滤未执行订单
			if(orderPo.getStatus() == 0){
				list.add(orderPo);
			}
		}
		return list;
	}

	public List<OrderPo> getFinishedOrders(int hotelId) {
		List<OrderPo> list = new ArrayList<OrderPo>();
		List<OrderPo> temp = getAllOrders(hotelId);
		for (OrderPo orderPo : temp) {
			//过滤已执行订单
			if(orderPo.getStatus() == 1){
				list.add(orderPo);
			}
		}
		return list;
	}

	public List<OrderPo> getAbnormalOrders(int hotelId) {
		List<OrderPo> list = new ArrayList<OrderPo>();
		List<OrderPo> temp = getAllOrders(hotelId);
		for (OrderPo orderPo : temp) {
			//过滤异常订单
			if(orderPo.getStatus() == 2){
				list.add(orderPo);
			}
		}
		return list;
	}

	public boolean finishOrder(int orderId, String entryTime) {
		//读取数据
		List<OrderPo> list = readFromFile();
		
		boolean result = false;
		for (OrderPo orderPo : list) {
			if(orderPo.getId() == orderId){
				result = true;
				//设置订单状态为已执行
				orderPo.setStatus(1);
				//设置用户入住时间
				orderPo.setEntryTime(entryTime);
				//写入修改后的数据
				writeIntoFile(list);
				break;
			}
		}
		
		//查找不到对应编号的订单时返回false
		return result;
	}

	public boolean delayOrder(int orderId, String lastTime) {
		//从数据文件读取数据
		List<OrderPo> list = readFromFile();
		
		boolean result = false;
		for (OrderPo orderPo : list) {
			if(orderPo.getId() == orderId){
				result = true;
				//设置订单状态为未执行
				orderPo.setStatus(0);
				//修改订单最后执行时间
				orderPo.setLastTime(lastTime);
				//写入修改后的数据
				writeIntoFile(list);
				break;
			}
		}
		
		
		return false;
	}
	
	/**
	 * @return	从数据文件读取订单数据
	 */
	private List<OrderPo> readFromFile(){
		//读取数据
		File file = new File("TxtData/order.txt");
		List<OrderPo> list = new ArrayList<OrderPo>();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				
				String[] data = str.split(";");
				int orderNo = Integer.valueOf(data[0]);
				int hotelId = Integer.valueOf(data[1]);
				int orderUserId = Integer.valueOf(data[2]);
				int orderStatus = Integer.valueOf(data[3]);
				String orderEntryTime = data[4];					
				String orderLastTime = data[5];
				String orderInfo=data[6];
				int orderPrice = Integer.valueOf(data[7]);
				OrderPo order=new OrderPo(orderNo, hotelId, orderUserId, orderStatus, orderEntryTime, orderLastTime,orderInfo,orderPrice);
				list.add(order);
				
				str = br.readLine();
				
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * 向数据文件中写入数据
	 * @param list
	 */
	private void writeIntoFile(List<OrderPo> list){
		//写入数据
		File file = new File("TxtData/order.txt");
		try {		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			
			for (OrderPo orderPo : list) {
				String str = orderPo.getId()+";"+orderPo.getHotelId()+";"+orderPo.getUserId()+";"
			+orderPo.getStatus()+";"+orderPo.getEntryTime()+";"+orderPo.getLastTime()+";"+orderPo.getOrderInfo()+";"+orderPo.getPrice();
				writer.write(str);
				writer.write("\r\n");
			}
			
			writer.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
