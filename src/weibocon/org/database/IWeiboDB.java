package weibocon.org.database;

import java.util.List;

import weibocon.org.data.Status;
import weibocon.org.data.UserInfo;
import android.database.Cursor;

/*
 * 微博数据访问接口
 */
public interface IWeiboDB {

	/*
	 * 获取user
	 */
	public Cursor GetUserInfo1();

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
	 * 获取微博信息
	 */
	public List<Status> GetStatus(Integer weiboType);

	/*
	 * 删除指定微博
	 */
	public Boolean DeleteStatus(List<Integer> ids);

	/*
	 * 添加微博
	 */
	public Boolean AddStatus(List<Status> statusList);
	/*
	 * 豺ｻ蜉�ag
	 */
	// public Boolean AddTag(List<Tag> tagList);
	// /*
	// * 闔ｷ蜿釦ag
	// */
	// public List<Tag> GetTag(Integer page,Integer uid);
	// /*
	// * 豺ｻ蜉��豕ｨ蟇ｹ雎｡
	// */
	// public Boolean AddFriends(List<Friends> friendsList);
	// /*
	// * 闔ｷ蜿冶ｴｧ荳ｻ蟇ｹ雎｡
	// */
	// public List<Friends> GetFriends(Integer uid);
	// /*
	// * 蛻�勁謖�ｮ夂噪蜈ｳ豕ｨ蟇ｹ雎｡
	// */
	// public Boolean DeleteFriends(Integer friendsID);
	// /*
	// * 闔ｷ蜿匁欠螳壼ｾｮ蜊夂噪隸�ｮｺ
	// */
	// public List<Comments> GetComments(Integer status_id);
	// /*
	// * 蛻�勁謖�ｮ喨d逧�ｯ�ｮｺ
	// */
	// public Boolean DeleteComments(Integer commentID);
	// /*
	// * 闔ｷ蜿冶｡ｨ諠�
	// */
	// public List<Emotion> GetEmoton();
	// /*
	// * 豺ｻ蜉�｡ｨ諠�
	// */
	// public Boolean AddEmotion(List<Emotion> emotionList);
	// /*
	// * 豺ｻ蜉�蕗閧ｲ扈丞紙
	// */
	// public Boolean AdEducation(List<Education> educationList);
	// /*
	// * 闔ｷ蜿匁欠螳嗽ser逧�蕗閧ｲ扈丞紙
	// */
	// public List<Education> GetEducation(Integer uid);
	// /*
	// * 譖ｴ譁ｰ謨呵ご扈丞紙
	// */
	// public Boolean UpdateEducation(List<Education> educationList);
	// /*
	// * 豺ｻ蜉�慍蝮�ｼ也�遐匁ｱ芽｡ｨ
	// */
	// public Boolean AddCode_Location(List<Code_Location> code_LocationList);
	// /*
	// * 闔ｷ蜿門慍蝮��霓ｬ謐｢陦ｨ
	// */
	// public List<Code_Location> GetCode_Location();
	// /*
	// * 豺ｻ蜉�ｲ我ｸ�
	// */
	// public Boolean AddFollowers(List<Followers> followersList);
	// /*
	// * 闔ｷ蜿匁欠螳嗽ser逧�ｲ我ｸ�
	// */
	// public List<Followers> GetFollowers(Integer uid);
	// /*
	// * 蛻�勁謖�ｮ喨d逧�ｲ我ｸ�
	// */
	// public Boolean DeleteFollowers(Integer followersID);
	// /*
	// * 豺ｻ蜉�ｧ∽ｿ｡
	// */
	// public Boolean AddMessages(List<Messages> messageList);
	// /*
	// * 闔ｷ蜿匁欠螳嗽ser逧�ｧ∽ｿ｡
	// */
	// public List<Messages> GetMessages(Integer uid,Integer page);
	//

}
