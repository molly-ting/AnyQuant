package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IntejiTool {

	public double[] getData(String id, int num) {
		String sql = "";
		if (id.equals("sh000300")) {
			sql = "select close from benchmark order by date desc limit " + num;
		} else {
			String[] idList = { "sz002024", "sh601398", "sh601166", "sh601857", "sz002458", "sz002256", "sz002192",
					"sz300319", "sz002218", "sh600005" };
			boolean hit = false;
			for (int i = 0; i < idList.length; i++) {
				if (id.equals(idList[i])) {
					sql = "select close from share where id='" + id + "' order by date desc limit " + num;
					hit = true;
					break;
				}
			}
			if (!hit) {
				sql = "select close from other where id='" + id + "' order by date desc limit " + num;
			}
		}

		return getData(sql, num, 0);
	}

	public double[] getData(String id, String end, int num) {
		String sql = "";
		if (id.equals("sh000300")) {
			sql = "select close from benchmark where date<='" + end + "' order by date desc limit " + num;
		} else {
			String[] idList = { "sz002024", "sh601398", "sh601166", "sh601857", "sz002458", "sz002256", "sz002192",
					"sz300319", "sz002218", "sh600005" };
			boolean hit = false;
			for (int i = 0; i < idList.length; i++) {
				if (id.equals(idList[i])) {
					sql = "select close from share where id='" + id + "' and  date<='" + end
							+ "' order by date desc limit " + num;
					hit = true;
					break;
				}
			}
			if (!hit) {
				sql = "select close from other where id='" + id + "' and  date<='" + end + "' order by date desc limit "
						+ num;
			}
		}

		return getData(sql, num, 0);
	}

	public int getInterval(String id, String start) {
		String sql = "";
		if (id.equals("sh000300")) {
			sql = "select count(date) from benchmark where date>='" + start + "' order by date desc";
		} else {
			String[] idList = { "sz002024", "sh601398", "sh601166", "sh601857", "sz002458", "sz002256", "sz002192",
					"sz300319", "sz002218", "sh600005" };
			boolean hit = false;
			for (int i = 0; i < idList.length; i++) {
				if (id.equals(idList[i])) {
					sql = "select count(date) from share where id='" + id + "' and  date>='" + start
							+ "' order by date desc";
					hit = true;
					break;
				}
			}
			if (!hit) {
				sql = "select count(date) from other where id='" + id + "' and  date>='" + start
						+ "' order by date desc";
			}
		}
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;
		try {
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			set.next();
			return set.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private double[] getData(String sql, int num, int s) {
		double[] x = new double[num];

		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;
		try {
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			int index = 0;

			while (set.next()) {
				x[index] = set.getDouble(1);
				index++;
			}
			if (num != index) {
				double[] y = new double[index];
				for (int i = 0; i < index; i++) {
					y[i] = x[i];
				}
				return y;
			}
			return x;
		} catch (Exception e) {
			e.printStackTrace();
			return new double[0];
		}
	}
}
