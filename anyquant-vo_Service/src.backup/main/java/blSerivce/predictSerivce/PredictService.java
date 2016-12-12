package blSerivce.predictSerivce;

import java.util.ArrayList;

import vo.PredicateVO;

public interface PredictService {

	/**
	 * 
	 * @param days
	 * @return 预测未来days天的数据
	 */
	public ArrayList<PredicateVO> predict(int days);
	
	/**
	 * 
	 * @param id
	 * @param start �?始日�?
	 * @return 预测已知数据，主要用于图表中的比对，直观展现预测正确�?
	 */
	public double[] predict(String start);
	
	/**
	 * 
	 * @param days
	 * @param numberOfStock 卖多少股
	 * @return 预测第days天的数据
	 */
	public double predictOne(String buyDate,String sellDate,double min,double max,int numberOfStock);
	
	/**
	 * 
	 * @param numberOfStock 卖多少股
	 * @return 返回收益�?高的日期和金�?
	 */
	public PredicateVO getBest(int numberOfStock);
	
	/**
	 * 
	 * @return 返回当前策略多少�?
	 */
	public int evaluate(double current);
}
