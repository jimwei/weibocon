package weibocon.org.actions;

import java.util.List;

import weibocon.org.data.Status;
import weibocon.org.data.UserInfo;
import weibocon.org.database.IWeiboDB;
import weibocon.org.database.WeiboDB;
import weibocon.org.protocols.IWeiboAPI;
import android.content.Context;

public class WeiboFacade {// implements IWeiboFacade {

	// TODO:
	static final String SinaAppKey = "";
	static final String TencentAppKey = "";
	private IWeiboDB _db = null;
	private IWeiboAPI _weiboAPI = null;
	private String _sinaAccessToken;
	private String _tencentAccessToken;
	private Context _context = null;

	public WeiboFacade(Context context) {
		_context = context;
		_db = new WeiboDB(context);
	}

	/*
	 * 返回access token
	 */

	public Boolean addUser(UserInfo user) {
		// TODO Auto-generated method stub
		return _db.AddUser(user);
	}

	public List<UserInfo> getUserInfo(Boolean local, Integer weiboType,
			Integer uid) {
		// TODO Auto-generated method stub
		List<UserInfo> list = null;
		if (local) {
			list = _db.GetUserInfo();
		} else {
			UserInfo user = _weiboAPI.getUser(weiboType,
					getAccessToken(weiboType), Long.parseLong(uid.toString()));

			if (user != null) {
				list.add(user);
			}
		}

		return list;
	}

	public Boolean updateUserInfo(Boolean local, UserInfo user) {
		// TODO Auto-generated method stub

		return null;
	}

	public Boolean deleteUserInfo(Boolean local, UserInfo user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Status> getStatus(Boolean local, Integer weiboType,
			Integer page, Integer uid) {
		// TODO Auto-generated method stub
		List<Status> list = null;
		if (local) {
			list = _db.GetStatus(weiboType);
		} else {

			list = _weiboAPI.getHomeStatus(weiboType,
					this.getAccessToken(weiboType), 0L, 0L, 0, page, 0, 0);
		}
		return list;
	}

	public Boolean deleteStatus(Boolean local, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean addStatus(Boolean local, List<Status> statusList) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 获取访问令牌
	 */
	public String getAccessToken(Integer weiboType) {

		return weiboType == 0 ? this._sinaAccessToken
				: this._tencentAccessToken;

	}

	/*
	 * 设置toekn
	 */
	public void setAccessToken(Integer weiboType, String accessToken) {
		if (weiboType == 0) {
			this._sinaAccessToken = accessToken;
		} else {
			this._tencentAccessToken = accessToken;
		}
	}

	public String login() {
		// TODO Auto-generated method stub
		return null;
	}

}
