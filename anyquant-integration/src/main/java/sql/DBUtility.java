package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.ShareDTO;
import integration.apiInte.BenchmarkInte;
import integration.apiInte.ShareInte;

public class DBUtility {

	public void updateData(String id) {
		check(id);
	}

	public ArrayList<ShareDTO> getData(String id, String start, String end) {
		String sql = "";
		if (id.equals("sh000300")) {
			sql = "select * from benchmark where date>='" + start + "' and date<='" + end + "'";
		} else {
			String[] idList = { "sz002024", "sh601398", "sh601166", "sh601857", "sz002458", "sz002256", "sz002192",
					"sz300319", "sz002218", "sh600005" };
			boolean hit = false;
			for (int i = 0; i < idList.length; i++) {
				if (id.equals(idList[i])) {
					sql = "select * from share where id='" + id + "' and date>='" + start + "' and date<='" + end + "'";
					hit = true;
					break;
				}
			}
			if (!hit) {
				sql = "select * from other where id='" + id + "' and date>='" + start + "' and date<='" + end + "'";
			}
		}
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;

		try {
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			ArrayList<ShareDTO> list = new ArrayList<ShareDTO>();
			if (id.equals("sh000300")) {
				while (set.next()) {
					ShareDTO share = new ShareDTO(id, set.getDouble(2), set.getDouble(3), set.getString(1),
							set.getDouble(4), set.getDouble(5), set.getDouble(6), set.getDouble(9), set.getDouble(10),
							set.getDouble(7), set.getDouble(8));
					list.add(share);
				}
			} else {
				while (set.next()) {
					ShareDTO share = new ShareDTO(id, set.getDouble(3), set.getDouble(4), set.getString(2),
							set.getDouble(5), set.getDouble(6), set.getDouble(7), set.getDouble(10), set.getDouble(11),
							set.getDouble(8), set.getDouble(9));
					list.add(share);
				}
			}
			if (list.isEmpty())
				return null;
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void check(String id) {
		String sql = "";
		if (id.equals("sh000300")) {
			sql = "select max(date) from benchmark";
		} else {
			String[] idList = { "sz002024", "sh601398", "sh601166", "sh601857", "sz002458", "sz002256", "sz002192",
					"sz300319", "sz002218", "sh600005" };
			boolean hit = false;
			for (int i = 0; i < idList.length; i++) {
				if (id.equals(idList[i])) {
					sql = "select max(date) from share where id='" + id + "'";
					hit = true;
					break;
				}
			}
			if (!hit) {
				sql = "select max(date) from other where id='" + id + "'";
			}
		}
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;

		try {
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			set.next();
			String recent = set.getString(1);
			// 如果不存在
			if (recent == null)
				return;

			Calendar c = Calendar.getInstance();
			long currentTime = c.getTimeInMillis();
			int weekday = c.get(Calendar.DAY_OF_WEEK);

			String[] s = recent.split("-");
			c.set(Integer.parseInt(s[0]), Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2]));
			long recentTime = c.getTimeInMillis();

			// 日期间隔
			long between_days = (currentTime - recentTime) / (1000 * 3600 * 24);
			int interval = Integer.parseInt(String.valueOf(between_days));
			if (interval == 0) {
				return;
			} else if ((weekday == 7 && interval <= 1) || (weekday == 1 && interval <= 2)) {
				// 如果是连续的周六周日
				return;
			} else {

				// 定义格式
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				// 取当前日期的后1天
				c.add(Calendar.DAY_OF_MONTH, +1);
				String start = format.format(c.getTime());

				c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, +1);
				String end = format.format(c.getTime());

				int hour = c.get(Calendar.HOUR_OF_DAY);

				if (interval == 1 && hour <= 18 && hour >= 0) {
					return;
				}
				update(id, start, end);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void update(String id, String start, String end) {
		String sql = "";
		ArrayList<ShareDTO> list = null;

		if (id.equals("sh000300")) {
			sql = "insert into benchmark(date,open,close,high,low,volume,pb,pe,turnover,adjprice)"
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			BenchmarkInte benchmark = new BenchmarkInte();
			list = benchmark.getBenchmark(start, end);
		} else {
			String[] idList = { "sz002024", "sh601398", "sh601166", "sh601857", "sz002458", "sz002256", "sz002192",
					"sz300319", "sz002218", "sh600005" };
			boolean hit = false;
			for (int i = 0; i < idList.length; i++) {
				if (id.equals(idList[i])) {
					sql = "insert into share(id,date,open,close,high,low,volume,pb,pe,turnover,adjprice)"
							+ "values(?,?,?,?,?,?,?,?,?,?,?)";
					hit = true;
					break;
				}
			}
			if (!hit) {
				sql = "insert into other(id,date,open,close,high,low,volume,pb,pe,turnover,adjprice)"
						+ "values(?,?,?,?,?,?,?,?,?,?,?)";
			}
			ShareInte share = new ShareInte();
			list = share.getShareDetail(id, start, end);
		}
		if (list == null || list.isEmpty())
			return;

		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;

		try {
			statement = database.conn.prepareStatement(sql);
			int count = 0;
			if (id.equals("sh000300")) {
				for (ShareDTO share : list) {
					statement.setString(1, share.getDate());
					statement.setDouble(2, share.getOpen());
					statement.setDouble(3, share.getClose());
					statement.setDouble(4, share.getHigh());
					statement.setDouble(5, share.getLow());
					statement.setDouble(6, share.getVolume());
					statement.setDouble(7, share.getPb());
					statement.setDouble(8, share.getPe());
					statement.setDouble(9, share.getTurnover());
					statement.setDouble(10, share.getAdj_price());
					statement.addBatch();
					if (count % 500 == 0) {
						statement.executeBatch();
						statement.clearBatch();
					}
				}
				statement.executeBatch();
			} else {
				for (ShareDTO share : list) {
					statement.setString(1, share.getID());
					statement.setString(2, share.getDate());
					statement.setDouble(3, share.getOpen());
					statement.setDouble(4, share.getClose());
					statement.setDouble(5, share.getHigh());
					statement.setDouble(6, share.getLow());
					statement.setDouble(7, share.getVolume());
					statement.setDouble(8, share.getPb());
					statement.setDouble(9, share.getPe());
					statement.setDouble(10, share.getTurnover());
					statement.setDouble(11, share.getAdj_price());
					statement.addBatch();
					if (count % 500 == 0) {
						statement.executeBatch();
						statement.clearBatch();
					}
				}
				statement.executeBatch();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
