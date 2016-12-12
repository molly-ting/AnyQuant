package businessLogic.sortBL;

import vo.BenchmarkVO;

public class Compare {

	/**
	 * 该方法只用于过滤，不用于排序
	 * 
	 * @param strategy
	 *            要获取的属�??
	 * @param b
	 *            过滤对象
	 * @param edge
	 *            给定的数值边�?
	 * @return b1<edge,return<0 
	 *         b1=edge,return 0;
	 *         b1>edge,return>0
	 */
	public int compare(String strategy, BenchmarkVO b, double edge) {
		double left = getValue(strategy, b);
		if (left > edge)
			return 1;
		else if (left == edge)
			return 0;
		else
			return -1;
	}

	/**
	 * 该方法只用于排序，不用于过滤
	 * 
	 * @param strategy
	 *            要获取的属�??
	 * @param b1
	 *            排序对象
	 * @param b2
	 *            排序对象
	 * @return  b1>b2,return>0
	 *          b1=b2,return 0 
	 *          b1<b2,return<0 
	 */
	public int compare(String strategy, BenchmarkVO b1, BenchmarkVO b2) {
		if (strategy.equals("date")) {
			String date1 = b1.getDate();
			String date2 = b2.getDate();
			return date1.compareTo(date2);
		}

		double left = getValue(strategy, b1);
		double right = getValue(strategy, b2);
		if (left > right)
			return 1;
		else if (left == right)
			return 0;
		else
			return -1;
	}

	/**
	 * 
	 * @param strategy
	 *            b的属性，不包括date
	 * @param b
	 *            要过滤的对象
	 * @return b的属性�??
	 */
	private double getValue(String strategy, BenchmarkVO b) {
		switch (strategy) {
		case "open":
			return b.getOpen();
		case "close":
			return b.getClose();
		case "high":
			return b.getHigh();
		case "low":
			return b.getLow();
		case "volume":
			return b.getVolume();
		case "turnover":
			return b.getTurnover();
		case "adj_price":
			return b.getAdj_price();
		case "pb":
			return b.getPb();
		case "pe":
			return b.getPe();
		default:
			return 0;
		}
	}
}
