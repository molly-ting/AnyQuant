package data.getShare;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;

import dataService.getShareDataService.GetShareService;
import sql.DBHelper;

public class ShareGetter implements GetShareService {
	HashMap<String, String> map = new HashMap<String, String>();

	public ShareGetter(String shorsz) {
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;

		String sql = "select * from " + shorsz + "code";
		try {
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				map.put(set.getString(1), set.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getShareName(String code) {

		String realcode, title;
		title = code.substring(0, 2);
		realcode = code.substring(2);

		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;

		String sql = "select name from " + title + "code where id='" + realcode + "'";
		try {
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			set.next();
			return set.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Iterator getSHShare() {
		if(map.isEmpty())
			return null;
		return map.entrySet().iterator();
	}

	@Override
	public Iterator getSZShare() {
		if(map.isEmpty())
			return null;
		return map.entrySet().iterator();
	}

}
