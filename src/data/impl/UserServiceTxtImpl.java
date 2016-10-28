package data.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import po.OrderPo;
import po.UserPo;
import data.UserService;

public class UserServiceTxtImpl implements UserService{

	public UserPo getUserPo(int userId) {
		File file = new File("TxtData/user.txt");
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				
				String[] data = str.split(";");
				if (Integer.valueOf(data[0]) == userId) {
					String username=data[1];
					String phone=data[2];
					int credit=Integer.valueOf(data[3]);
					UserPo userPo=new UserPo(userId, username, phone, credit);
					return userPo;
				}
				str = br.readLine();
				
			}

			br.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addUserCredit(int userId, int creditToAdd) {
		//读取用户数据
		File file = new File("TxtData/user.txt");
		List<UserPo> list = new ArrayList<UserPo>();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				
				String[] data = str.split(";");
				int userNo = Integer.valueOf(data[0]);
				String username = data[1];
				String phone = data[2];
				int credit = Integer.valueOf(data[3]);
				UserPo userPo=new UserPo(userNo, username, phone, credit);
				list.add(userPo);	
				
				str = br.readLine();
				
			}

			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//修改用户信用值
		boolean result = false;
		for (UserPo userPo : list) {
			if(userPo.getId() == userId){
				userPo.setCredit(userPo.getCredit()+creditToAdd);
				break;
			}
		}
		
		//写入用户数据
		try {		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			
			for (UserPo userPo : list) {
				String str = userPo.getId()+";"+userPo.getUsername()+";"+userPo.getPhone()+";"+userPo.getCredit();
				writer.write(str);
				writer.write("\r\n");
			}
			
			writer.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
 
}
