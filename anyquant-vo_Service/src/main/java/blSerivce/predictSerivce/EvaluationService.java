package blSerivce.predictSerivce;

public interface EvaluationService {

	/**
	 * 
	 * @return 计算速度
	 */
	public int getSpeed();
	
	/**
	 * 
	 * @return 短期逼近率
	 */
	public int getShortTermAccuracy();
	
	/**
	 * 
	 * @return 长期逼近率
	 */
	public int getLongTermAccuracy();
	
	/**
	 * 
	 * @return 算法复杂度
	 */
	public int getComplexity();
	
	/**
	 * 
	 * @return 学习速度
	 */
	public int getConvergenceSpeed();
	
	/**
	 * 
	 * @return 总分
	 */
	public int getSum();
}
