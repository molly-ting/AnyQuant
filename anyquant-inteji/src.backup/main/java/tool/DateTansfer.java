package tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTansfer {

	public String getDate(String date, int delay) {
		// 结束日期推后�?�?
		Calendar c = Calendar.getInstance();
		String[] sp = date.split("-");
		c.set(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]) - 1, Integer.parseInt(sp[2]));
		c.add(Calendar.DAY_OF_MONTH, delay);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(c.getTime());
	}

	/**
	 * 计算两个日期的间�?
	 * @param start
	 * @param end
	 * @return
	 */
	public int getInterval(String start, String end) {
		Calendar c = Calendar.getInstance();
		String[] sp = start.split("-");
		c.set(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]) - 1, Integer.parseInt(sp[2]));
		long time1 = c.getTimeInMillis();
		
		String[] sp1 = end.split("-");
		c.set(Integer.parseInt(sp1[0]), Integer.parseInt(sp1[1]) - 1, Integer.parseInt(sp1[2]));
		long time2 = c.getTimeInMillis();
		//日期间隔
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		
		return Integer.parseInt(String.valueOf(between_days));
	}
}
