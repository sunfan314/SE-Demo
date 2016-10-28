package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import presentation.ProcessOrderView;
import businesslogic.ProcessOrderBLService;
import businesslogic.impl.ProcessOrderBLServiceImpl;

public class Main {
	
	public static void main(String[] args){
		JFrame mFrame = new JFrame();
		mFrame.setSize(800, 600);
		mFrame.setLocation(300, 300);
		ProcessOrderBLService bl=new ProcessOrderBLServiceImpl();
		JPanel panel = new ProcessOrderView(bl);
		mFrame.getContentPane().add(panel);
		mFrame.setVisible(true);
	}

}
