package vo;

/**
 * StartegyVO负责传�?�决�?
 * @author 云奎
 * @see StrategyNumVO
 * @see StrategyDateVO
 */
public class StrategyVO {

	//策略类型，�?�open”代表开盘价，�?�close”代表收盘价�?
	private String strategy;
	
	
	/**
	 * 
	 * @param strategy 传入策略类型，open�?盘价，close收盘价，high�?高价，low�?低价，adj_price后复权价
	 *                 volume成交量，turnover换手率，pe市净率，pb市盈�?
	 *                 
	 * strategy格式�?"open"（过滤开盘价））
	 */
	public StrategyVO(String strategy){
		this.strategy = strategy;
	}
	
	/**
	 * 
	 * @return 返回策略类型
	 */
	public String getStrategy(){
		return strategy;
	} 
}
