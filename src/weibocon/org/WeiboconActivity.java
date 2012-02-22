package weibocon.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import weibocon.org.data.Status;
import weibocon.org.data.UserInfo;
import weibocon.org.database.WeiboDB;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WeiboconActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testdb);
		Button btn = (Button) findViewById(R.id.dbbutton1);
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				testDB();
			}
		});
	}

	void testDB() {
		WeiboDB _db = new WeiboDB(this);

		// write user
		UserInfo user = new UserInfo();
		user.setUid(1);
		user.setName("aaa");
		user.setNick("a");
		user.setHead("http://www.baidu.com");
		user.setSex(1);
		user.setAccessToken("12-34-56-78");
		user.setSecretToken("111-222-333");
		user.setWeiboType(1);
		_db.AddUser(user);

		Log.i("dbTest", "write user");

		// 读出user
		List<UserInfo> users = _db.GetUserInfo();

		for (UserInfo item : users) {
			Log.i("dbTest", item.getId().toString() + "-" + item.getName());
		}

		// write status
		List<Status> statuses = new ArrayList<Status>();
		Status item = new Status();

		item.setText("Hello Android!"
				+ Integer.toString(new Random().nextInt()));
		item.setOriginText("Hello Android world!"
				+ Integer.toString(new Random().nextInt()));
		item.setIsTrucated(false);
		item.setCreate_at(new Date());
		item.setThumbnail_Pic("face");
		item.setRtweeted_Status(1L);
		item.setComments_count(12);
		item.setUid(1);
		item.setSelf(true);
		item.setType(1);
		item.setWeiboType(0);

		statuses.add(item);

		item = new Status();
		item.setText("Hello Android!"
				+ Integer.toString(new Random().nextInt()));
		item.setOriginText("Hello Android world!"
				+ Integer.toString(new Random().nextInt()));
		item.setIsTrucated(false);
		item.setCreate_at(new Date());
		item.setThumbnail_Pic("cry");
		item.setRtweeted_Status(1L);
		item.setComments_count(13);
		item.setUid(1);
		item.setSelf(true);
		item.setType(1);
		item.setWeiboType(0);

		statuses.add(item);

		_db.AddStatus(statuses);

		Log.i("dbTest", "write status");

		// read status

		statuses = _db.GetStatus(0);
		for (Status ent : statuses) {
			Log.i("dbTest", ent.getOriginText());
		}
	}
}