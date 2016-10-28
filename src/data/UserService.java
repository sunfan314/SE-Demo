package data;

import po.UserPo;

public interface UserService {

	/**
	 * @param userId	用户编号
	 * @return	获取用户信息
	 */
	public UserPo getUserPo(int userId);
	
	/**
	 * @param userId	用户编号
	 * @param creditToAdd	增加的信用值
	 * @return	为用户增加信用值
	 */
	public boolean addUserCredit(int userId,int creditToAdd);
	
}
