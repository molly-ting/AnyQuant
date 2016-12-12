package blSerivce.predictSerivce;

import java.util.ArrayList;

import vo.DonutVO;
import vo.ShareScoreVO;

public interface StrategyService {

	/**
	 * 
	 * @return 前10股票得分
	 */
	public ArrayList<ShareScoreVO> getStartegy();
	
	/**
	 * 
	 * @param id
	 * @return 某只股票得分
	 */
	public ShareScoreVO getStrategy(String id);
	
	/**
	 * 
	 * @return 回报率+风险+适宜度+价值+潜力
	 */
	public ArrayList<DonutVO> getSingleAspect();
}
