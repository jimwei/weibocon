package weibocon.org.actions;

import java.util.List;

import weibocon.org.data.Status;
import weibocon.org.data.UserInfo;

public interface IWeiboFacade {

	/*
	 * 添加user
	 */
	public Boolean AddUser(UserInfo user);

	/*
	 * 获取user
	 */
	public List<UserInfo> GetUserInfo();

	/*
	 * 更新user
	 */
	public Boolean UpdateUserInfo(UserInfo user);

	/*
	 * 删除user
	 */
	public Boolean DeleteUserInfo(UserInfo user);

	/*
	 * 获取微博
	 */
	public List<Status> GetStatus(Integer weiboType, Integer page, Integer uid);

	/*
	 * 删除微博
	 */
	public Boolean DeleteStatus(Integer id);

	/*
	 * 添加微博
	 */
	public Boolean AddStatus(List<Status> statusList);

}
