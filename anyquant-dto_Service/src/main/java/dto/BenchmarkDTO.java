package dto;

public class BenchmarkDTO {

	private double open;// 开盘价
	private double close;// 收盘价
	private String date;// 日期

	private double high;// 最高价
	private double low;// 最低价
	private double volume;// 成交量
	private double sum;// 交易金额

	private double turnover;// 换手率
	private double adj_price;// 后复权价
	private double pb;// 市净率
	private double pe;// 市盈率

	public BenchmarkDTO() {

	}

	/**
	 * 
	 * @param open
	 *            开盘价
	 * @param close
	 *            收盘价
	 * @param date
	 *            日期
	 * @param high
	 *            最高价
	 * @param low
	 *            最低价
	 * @param volume
	 *            成交量
	 * @param turnover
	 *            换手率
	 * @param adj_price
	 *            后复权价
	 * @param pb
	 *            市净率
	 * @param pe
	 *            市盈率
	 */
	public BenchmarkDTO(double open, double close, String date, double high, double low, double volume, double turnover,
			double adj_price, double pb, double pe) {
		this.open = open;
		this.close = close;
		this.date = date;
		this.high = high;
		this.low = low;
		this.volume = volume;
		this.turnover = turnover;
		this.adj_price = adj_price;
		this.pb = pb;
		this.pe = pe;
	}

	/**
	 * 
	 * @return 开盘价
	 */
	public double getOpen() {
		return open;
	}

	/**
	 * 
	 * @return 收盘价
	 */
	public double getClose() {
		return close;
	}

	/**
	 * 
	 * @return 日期
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 
	 * @return 最高价
	 */
	public double getHigh() {
		return high;
	}

	/**
	 * 
	 * @return 最低价
	 */
	public double getLow() {
		return low;
	}

	/**
	 * 
	 * @return 成交量
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * 
	 * @return 交易金额
	 */
	public double getSum() {
		return sum;
	}

	/**
	 * 
	 * @return 换手率
	 */
	public double getTurnover() {
		return turnover;
	}

	/**
	 * 
	 * @return 后复权价
	 */
	public double getAdj_price() {
		return adj_price;
	}

	/**
	 * 
	 * @return 市净率
	 */
	public double getPb() {
		return pb;
	}

	/**
	 * 
	 * @return 市盈率
	 */
	public double getPe() {
		return pe;
	}
	
	public void setOpen(double open) {
		this.open = open;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public void setTurnover(double truenover) {
		this.turnover = truenover;
	}

	public void setAdj_price(double adj_price) {
		this.adj_price = adj_price;
	}

	public void setPb(double pb) {
		this.pb = pb;
	}

	public void setPe(double pe) {
		this.pe = pe;
	}


}
