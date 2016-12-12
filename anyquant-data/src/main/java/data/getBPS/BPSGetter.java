package data.getBPS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dataService.statisticDataService.GetBPSDataService;
import sql.DBHelper;

public class BPSGetter implements GetBPSDataService {

	@Override
	public double getBSP(String code) {
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;

		String sql = "select pb from bps where id='" + code + "'";
		try {
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			set.next();
			double pb = set.getDouble(1);
			return pb;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
