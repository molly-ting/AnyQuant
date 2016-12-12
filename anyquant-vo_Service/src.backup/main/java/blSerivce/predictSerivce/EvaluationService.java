package blSerivce.predictSerivce;

public interface EvaluationService {

	/**
	 * 
	 * @return 计算速度
	 */
	public int getSpeed();
	
	/**
	 * 
	 * @return 短期逼近�?
	 */
	public int getShortTermAccuracy();
	
	/**
	 * 
	 * @return 长期逼近�?
	 */
	public int getLongTermAccuracy();
	
	/**
	 * 
	 * @return 算法复杂�?
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
