package vo;

/**
 * 处理数字过滤条件
 * 父类为StrategyVO
 * @author 云奎
 *
 */
public class StrategyNumVO extends StrategyVO{

	// 区间的左值
	private double left;
	// 区间的右值
	private double right;
	
	/**
	 * 
	 * @param strategy 传入策略类型，open开盘价，close收盘价，high最高价，low最低价，adj_price后复权价
	 *                 volume成交量，turnover换手率，pe市净率，pb市盈率
	 * @param left 区间的左值，传入后统一变为double
	 * @param right 区间的右值，传入后统一变为double
	 * 
	 * 传入left和right时，int，double等类型请先使用check类检查数据是否正确
	 * @see Check.checkNumber
	 */
	public StrategyNumVO(String strategy, double left, double right) {
		super(strategy);
		this.left = left;
		this.right = right;
	}
	
	/**
	 * 
	 * @return 返回左值
	 */
	public double getLeft(){
		return left;
	}
	
	/**
	 * 
	 * @return 返回右值
	 */
	public double getRight(){
		return right;
	}
}
