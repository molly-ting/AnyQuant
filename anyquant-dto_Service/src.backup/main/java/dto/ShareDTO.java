package dto;

public class ShareDTO extends BenchmarkDTO {

	private String id;
	private String name;

	public ShareDTO() {
		super();
	}
	
	public ShareDTO(String id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 * @param open
	 *            �?盘价
	 * @param close
	 *            收盘�?
	 * @param date
	 *            日期
	 * @param high
	 *            �?高价
	 * @param low
	 *            �?低价
	 * @param volume
	 *            成交�?
	 * @param turnover
	 *            换手�?
	 * @param adj_price
	 *            后复权价
	 * @param pb
	 *            市净�?
	 * @param pe
	 *            市盈�?
	 */
	public ShareDTO(String id, double open, double close, String date, double high, 
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
	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		name = n;
	}

}
