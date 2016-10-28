package data.impl;

import data.DataFactory;
import data.OrderService;
import data.UserService;

public class DataFactoryImpl implements DataFactory{

	public UserService getUserData() {
		// TODO Auto-generated method stub
		UserService userService = new UserServiceTxtImpl();
		return userService;
	}

	public OrderService getOrderData() {
		// TODO Auto-generated method stub
		OrderService orderService = new OrderServiceTxtImpl();
		return orderService;
	}

}
