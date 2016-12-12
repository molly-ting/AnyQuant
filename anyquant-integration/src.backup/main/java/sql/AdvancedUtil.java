package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dataService.sqlService.SQLUtilityService;
import dto.ShareDTO;

public class AdvancedUtil implements SQLUtilityService {

	public double getRate(String id) {
		String sql = "";
		if (id.equals("sh000300")) {
			sql = "select close from benchmark order by date desc limit 2";
		} else {
			String[] idList = { "sz002024", "sh601398", "sh601166", "sh601857", "sz002458", "sz002256", "sz002192",
					"sz300319", "sz002218", "sh600005" };
			boolean hit = false;
			for (int i = 0; i < idList.length; i++) {
				if (id.equals(idList[i])) {
					sql = "select close from share where id='" + id + "' order by date desc limit 2";
					hit = true;
					break;
				}
			}
			if (!hit) {
				sql = "select close from other where id='" + id + "' order by date desc limit 2";
			}
		}
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;

		try {
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			double[] d = { 0, 1 };
			int index = 0;
			while (set.next()) {
				d[index] = set.getDouble(1);
				index++;
			}
			if (d[1] == 0)
				return 0;
			return (d[0] - d[1]) / d[1] * 100;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<ShareDTO> getAllShareInOneDay() {
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;
		String sql = "";

		try {
			sql = "select max(date) from share";
			statement = database.conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			result.next();
			String date1 = result.getString(1);
			sql = "select * from share where date='" + date1 + "'";
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			ArrayList<ShareDTO> list = new ArrayList<ShareDTO>();
			while (set.next()) {
				String id = set.getString(1);
				String sub = id.substring(2);
				sql = "select name from " + id.substring(0, 2) + "code where id='" + sub + "'";
				statement = database.conn.prepareStatement(sql);
				ResultSet nameSet = statement.executeQuery();
				nameSet.next();
				String name = nameSet.getString(1);
				ShareDTO share = new ShareDTO(set.getString(1), set.getDouble(3), set.getDouble(4), set.getString(2),
						set.getDouble(5), set.getDouble(6), set.getDouble(7), set.getDouble(10), set.getDouble(11),
						set.getDouble(8), set.getDouble(9));
				share.setName(name);
				list.add(share);
			}

			sql = "select max(date) from other";
			statement = database.conn.prepareStatement(sql);
			ResultSet result1 = statement.executeQuery();

			result1.next();
			String date2 = result1.getString(1);
			sql = "select * from other where date='" + date2 + "' limit 90";
			statement = database.conn.prepareStatement(sql);
			ResultSet set2 = statement.executeQuery();

			while (set2.next()) {
				String id = set2.getString(1);
				String sub = id.substring(2);
				sql = "select name from " + id.substring(0, 2) + "code where id='" + sub + "'";
				statement = database.conn.prepareStatement(sql);
				ResultSet nameSet = statement.executeQuery();
				nameSet.next();
				String name = nameSet.getString(1);
				ShareDTO share = new ShareDTO(set2.getString(1), set2.getDouble(3), set2.getDouble(4),
						set2.getString(2), set2.getDouble(5), set2.getDouble(6), set2.getDouble(7), set2.getDouble(10),
						set2.getDouble(11), set2.getDouble(8), set2.getDouble(9));
				share.setName(name);
				list.add(share);
			}
			if (list.isEmpty())
				return null;
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ShareDTO getLastDay(String id) {
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;
		String sql = "";

		if (id.equals("sh000300")) {
			sql = "select * from benchmark order by date desc limit 1";
		} else {
			String[] idList = { "sz002024", "sh601398", "sh601166", "sh601857", "sz002458", "sz002256", "sz002192",
					"sz300319", "sz002218", "sh600005" };
			boolean hit = false;
			for (int i = 0; i < idList.length; i++) {
				if (id.equals(idList[i])) {
					sql = "select * from share where id='" + id + "' order by date desc limit 1";
					hit = true;
					break;
				}
			}
			if (!hit) {
				sql = "select * from other where id='" + id + "' order by date desc limit 1";
			}
		}

		try {

			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			set.next();
			ShareDTO share = new ShareDTO();
			if (id.equals("sh000300")) {
				share = new ShareDTO(id, set.getDouble(2), set.getDouble(3), set.getString(1), set.getDouble(4),
						set.getDouble(5), set.getDouble(6), set.getDouble(9), set.getDouble(10), set.getDouble(7),
						set.getDouble(8));
				share.setName("沪深300");

			} else {
				String sub = id.substring(2);
				sql = "select name from " + id.substring(0, 2) + "code where id='" + sub + "'";
				statement = database.conn.prepareStatement(sql);
				ResultSet nameSet = statement.executeQuery();
				nameSet.next();
				String name = nameSet.getString(1);
				share = new ShareDTO(id, set.getDouble(3), set.getDouble(4), set.getString(2), set.getDouble(5),
						set.getDouble(6), set.getDouble(7), set.getDouble(10), set.getDouble(11), set.getDouble(8),
						set.getDouble(9));
				share.setName(name);
			}
			return share;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<ShareDTO> find(String text) {
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;
		String sql = "";

		try {
			sql = "select max(date) from share";
			statement = database.conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			result.next();
			String date1 = result.getString(1);
			sql = "select * from share where date='" + date1 + "' and id like '%" + text + "%'";
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			ArrayList<ShareDTO> list = new ArrayList<ShareDTO>();
			while (set.next()) {
				String id = set.getString(1);
				String sub = id.substring(2);
				sql = "select name from " + id.substring(0, 2) + "code where id='" + sub + "'";
				statement = database.conn.prepareStatement(sql);
				ResultSet nameSet = statement.executeQuery();
				nameSet.next();
				String name = nameSet.getString(1);
				ShareDTO share = new ShareDTO(set.getString(1), set.getDouble(3), set.getDouble(4), set.getString(2),
						set.getDouble(5), set.getDouble(6), set.getDouble(7), set.getDouble(10), set.getDouble(11),
						set.getDouble(8), set.getDouble(9));
				share.setName(name);
				list.add(share);
			}

			sql = "select max(date) from other";
			statement = database.conn.prepareStatement(sql);
			ResultSet result1 = statement.executeQuery();

			result1.next();
			String date2 = result1.getString(1);
			sql = "select * from (select * from other where date='" + date2 + "' limit 90) as a where a.id like '%"
					+ text + "%'";
			statement = database.conn.prepareStatement(sql);
			ResultSet set2 = statement.executeQuery();

			while (set2.next()) {
				String id = set2.getString(1);
				String sub = id.substring(2);
				sql = "select name from " + id.substring(0, 2) + "code where id='" + sub + "'";
				statement = database.conn.prepareStatement(sql);
				ResultSet nameSet = statement.executeQuery();
				nameSet.next();
				String name = nameSet.getString(1);
				ShareDTO share = new ShareDTO(set2.getString(1), set2.getDouble(3), set2.getDouble(4),
						set2.getString(2), set2.getDouble(5), set2.getDouble(6), set2.getDouble(7), set2.getDouble(10),
						set2.getDouble(11), set2.getDouble(8), set2.getDouble(9));
				share.setName(name);
				list.add(share);
			}
			if (list.isEmpty())
				return null;
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
