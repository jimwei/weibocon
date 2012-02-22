/**
 * 
 */
package weibocon.org.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import weibocon.org.data.Status;
import weibocon.org.data.UserInfo;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author JimWei
 * 
 */
public class WeiboDB implements IWeiboDB {
	private static final String _dbname = "WeiboCon.db";
	private static final Integer _dbversion = 1;
	private SQLiteDatabase _db = null;
	private WeiBoSqliteOpenHelper _helper = null;

	public WeiboDB(Context context) {
		Init(context);
	}

	private void Init(Context context) {
		_helper = new WeiBoSqliteOpenHelper(context,
				WeiBoSqliteOpenHelper.dbName, null,
				WeiBoSqliteOpenHelper.version);
		_db = _helper.getWritableDatabase();
	}

	/*
	 * 关闭接口
	 */
	public void Close() {
		_db.close();
		_helper.close();

	}

	public Cursor GetUserInfo1() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see weibocon.org.database.IWeiBoDB#AddUser(weibocon.org.data.UserInfo)
	 */
	public Boolean AddUser(UserInfo user) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("Insert Into ");
		strSql.append(WeiBoSqliteOpenHelper.tbl_UserInfo);
		strSql.append("(uid,name,nick,sex,head,accessToken,secretToken,weibotype)");
		strSql.append(" Values(\r\n");
		strSql.append(user.getUid().toString());
		strSql.append(",'" + user.getName() + "'");
		strSql.append(",'" + user.getNick() + "'");
		strSql.append("," + user.getSex().toString());
		strSql.append(",'" + user.getHead() + "'");
		strSql.append(",'" + user.getAccessToken() + "'");
		strSql.append(",'" + user.getSecretToken() + "'");
		strSql.append("," + user.getWeiboType().toString());
		strSql.append(")");

		_db.execSQL(strSql.toString());
		return true;
	}

	public List<UserInfo> GetUserInfo() {
		List<UserInfo> list = new ArrayList<UserInfo>();
		StringBuilder strSql = new StringBuilder();
		strSql.append("Select * From " + WeiBoSqliteOpenHelper.tbl_UserInfo);

		Cursor _cursor = _db.rawQuery(strSql.toString(), null);

		UserInfo item = null;
		while (_cursor.moveToNext()) {
			item = new UserInfo();
			item.setId(_cursor.getInt(0));
			item.setUid(_cursor.getInt(1));
			item.setName(_cursor.getString(2));
			item.setNick(_cursor.getString(3));
			item.setSex(_cursor.getInt(4));
			item.setHead(_cursor.getString(5));
			item.setAccessToken(_cursor.getString(6));
			item.setSecretToken(_cursor.getString(7));
			item.setWeiboType(_cursor.getInt(8));

			list.add(item);
		}
		return list;
	}

	public Boolean UpdateUserInfo(UserInfo user) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("Update " + WeiBoSqliteOpenHelper.tbl_UserInfo);
		strSql.append(" Set uid=" + user.getUid().toString());
		strSql.append(",name='" + user.getName() + "'");
		strSql.append(",nick='" + user.getNick() + "'");
		strSql.append(",sex=" + user.getSex().toString());

		_db.execSQL(strSql.toString());

		return true;
	}

	public Boolean DeleteUserInfo(UserInfo user) {
		String sql = "Delete From " + WeiBoSqliteOpenHelper.tbl_UserInfo
				+ "  Where id=" + user.getId().toString();

		_db.execSQL(sql);
		return true;
	}

	public Boolean DeleteStatus(List<Integer> ids) {
		// TODO Auto-generated method stub
		StringBuilder idStr = new StringBuilder();
		for (Integer item : ids) {
			idStr.append(item.toString() + ",");
		}
		// remove the last comma
		idStr.deleteCharAt(idStr.length() - 1);

		String sql = "Delete From " + WeiBoSqliteOpenHelper.tbl_Status
				+ " Where id IN(" + idStr.toString() + ")";

		_db.execSQL(sql);
		return true;
	}

	public Boolean AddStatus(List<Status> statusList) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();

		for (Status item : statusList) {
			str.append("Insert Into " + WeiBoSqliteOpenHelper.tbl_Status);
			str.append(" Values(");
			str.append("null,");
			str.append("'" + item.getText() + "',");
			str.append("'" + item.getOriginText() + "',");
			str.append((item.getIsTrucated() ? 1 : 0) + ",");
			str.append("'" + convertDateToString(item.getCreate_at()) + "',");
			str.append("'" + item.getThumbnail_Pic() + "',");
			str.append(item.getRtweeted_Status() + ",");
			str.append(item.getComments_count() + ",");
			str.append(item.getUid() + ",");
			str.append((item.getSelf() ? 1 : 0) + ",");
			str.append(item.getType() + ",");
			str.append(item.getWeiboType());
			str.append(")");

			_db.execSQL(str.toString());
			str.delete(0, str.length());
		}
		return true;
	}

	public List<Status> GetStatus(Integer weiboType) {
		// TODO Auto-generated method stub
		List<Status> list = new ArrayList<Status>();
		Status item = null;
		String sql = "Select * From " + WeiBoSqliteOpenHelper.tbl_Status
				+ " Where WeiboType = " + weiboType;
		Cursor cursor = _db.rawQuery(sql, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				item = new Status();
				item.setId(cursor.getInt(0));
				item.setText(cursor.getString(1));
				item.setOriginText(cursor.getString(2));
				item.setIsTrucated(cursor.getInt(3) > 0 ? true : false);

				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				try {
					Date d1 = formatter.parse(cursor.getString(4));
					item.setCreate_at(d1);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				item.setThumbnail_Pic(cursor.getString(5));
				item.setRtweeted_Status(cursor.getLong(6));
				item.setComments_count(cursor.getInt(7));
				item.setUid(cursor.getInt(8));
				item.setSelf(cursor.getInt(9) > 0 ? true : false);
				item.setType(cursor.getInt(10));
				item.setWeiboType(cursor.getInt(11));

				list.add(item);

			}
		}
		return list;
	}

	// public Boolean AddTag(List<Tag> tagList) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	// public List<Tag> GetTag(Integer page, Integer uid) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public Boolean AddFriends(List<Friends> friendsList) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public List<Friends> GetFriends(Integer uid) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public Boolean DeleteFriends(Integer friendsID) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	// public List<Comments> GetComments(Integer status_id) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public Boolean DeleteComments(Integer commentID) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public List<Emotion> GetEmoton() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public Boolean AddEmotion(List<Emotion> emotionList) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public Boolean AdEducation(List<Education> educationList) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public List<Education> GetEducation(Integer uid) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	// public Boolean UpdateEducation(List<Education> educationList) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public Boolean AddCode_Location(List<Code_Location> code_LocationList) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public List<Code_Location> GetCode_Location() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public Boolean AddFollowers(List<Followers> followersList) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public List<Followers> GetFollowers(Integer uid) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	// public Boolean DeleteFollowers(Integer followersID) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public Boolean AddMessages(List<Messages> messageList) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public List<Messages> GetMessages(Integer uid, Integer page) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	public static String convertDateToString(Date d) {

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.US);
		return f.format(d);
	}
}
