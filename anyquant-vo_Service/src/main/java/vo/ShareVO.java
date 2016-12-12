package vo;

public class ShareVO extends BenchmarkVO {

	private String id;
	private String name;

	public ShareVO() {
		super();
	}

	public ShareVO(String id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 * @param id
	 *            股票代码，沪深300：sh000300
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
	public ShareVO(String id, double open, double close, String date, double high, 
			double low, double volume,double turnover, double adj_price, double pb, 
			double pe) {
		super(open, close, date, high, low, volume, turnover, adj_price, pb, pe);
		this.id = id;
	}

	/**
	 * 
	 * @return 股票代码
	 */
	public String getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	
}
