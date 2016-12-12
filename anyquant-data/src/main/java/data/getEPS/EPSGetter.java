package data.getEPS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dataService.statisticDataService.GetEPSDataService;
import sql.DBHelper;

public class EPSGetter implements GetEPSDataService{

	@Override
	public double getESP(String code) {
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;

		String sql = "select pe from eps where id='" + code + "'";
		try {
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			set.next();
			double pe = set.getDouble(1);
			return pe;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
