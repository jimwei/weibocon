package weibocon.org.actions;

import java.util.List;

import weibocon.org.data.Status;
import weibocon.org.data.UserInfo;

public interface IWeiboFacade {

	public String login();

	/*
	 * 添加user
	 */
	public Boolean addUser(UserInfo user);

	/*
	 * 获取指定uid的user
	 */
	public List<UserInfo> getUserInfo(Boolean local, Integer weiboType,
			Integer uid);

	/*
	 * 更新user
	 */
	public Boolean updateUserInfo(Boolean local, UserInfo user);

	/*
	 * 删除user
	 */
	public Boolean deleteUserInfo(Boolean local, UserInfo user);

	/*
	 * 获取微博
	 */
	public List<Status> getStatus(Boolean local, Integer weiboType,
			Integer page, Integer uid);

	/*
	 * 删除微博
	 */
	public Boolean deleteStatus(Boolean local, Integer id);

	/*
	 * 添加微博
	 */
	public Boolean addStatus(Boolean local, List<Status> statusList);

	// TODO:原文转发，转发，评论,收藏，

}
