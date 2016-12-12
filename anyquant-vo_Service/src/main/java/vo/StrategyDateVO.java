package vo;

/**
 * 处理日期过滤条件
 * 父类为StrategyVO
 * @author 云奎
 *
 */
public class StrategyDateVO extends StrategyVO{

	//开始日期
	private String startDate;
	//结束日期
	private String endDate;
	
	/**
	 * 
	 * @param strategy 传入策略类型，open开盘价，close收盘价，high最高价，low最低价，adj_price后复权价
	 *                 volume成交量，turnover换手率，pe市净率，pb市盈率
	 * @param start 开始日期
	 * @param end 结束日期
	 *
	 */
	public StrategyDateVO(String strategy, String start, String end) {
		super(strategy);
		this.startDate = start;
		this.endDate = end;
	}
	
	/**
	 * 
	 * @return 返回开始日期
	 */
	public String getStartDate(){
		return startDate;
	}
	
	/**
	 * 
	 * @return 返回结束日期
	 */
	public String getEndDate(){
		return endDate;
	}
}
