package vo;

/**
 * 处理数字过滤条件
 * 父类为StrategyVO
 * @author 云奎
 *
 */
public class StrategyNumVO extends StrategyVO{

	// 区间的左�?
	private double left;
	// 区间的右�?
	private double right;
	
	/**
	 * 
	 * @param strategy 传入策略类型，open�?盘价，close收盘价，high�?高价，low�?低价，adj_price后复权价
	 *                 volume成交量，turnover换手率，pe市净率，pb市盈�?
	 * @param left 区间的左值，传入后统�?变为double
	 * @param right 区间的右值，传入后统�?变为double
	 * 
	 * 传入left和right时，int，double等类型请先使用check类检查数据是否正�?
	 * @see Check.checkNumber
	 */
	public StrategyNumVO(String strategy, double left, double right) {
		super(strategy);
		this.left = left;
		this.right = right;
	}
	
	/**
	 * 
	 * @return 返回左�??
	 */
	public double getLeft(){
		return left;
	}
	
	/**
	 * 
	 * @return 返回右�??
	 */
	public double getRight(){
		return right;
	}
}
