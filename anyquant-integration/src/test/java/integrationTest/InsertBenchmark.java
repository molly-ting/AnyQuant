package integrationTest;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import dto.ShareDTO;
import integration.apiInte.BenchmarkInte;
import junit.framework.TestCase;
import sql.DBHelper;

public class InsertBenchmark extends TestCase{

	public void test(){
		BenchmarkInte benchmark = new BenchmarkInte();
		ArrayList<ShareDTO> list = benchmark.getBenchmark("1990-01-01", "2016-05-17");
		
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;

		String sql = "insert into benchmark(date,open,close,high,low,volume,pb,pe,turnover,adjprice)"
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		try {
			statement = database.conn.prepareStatement(sql);

			int count = 0;
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
				if (count % 1000 == 0) {
					statement.executeBatch();
					statement.clearBatch();
				}
			}
			statement.executeBatch();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
