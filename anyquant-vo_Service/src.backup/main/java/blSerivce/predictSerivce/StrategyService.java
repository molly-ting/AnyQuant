package blSerivce.predictSerivce;

import java.util.ArrayList;

import vo.DonutVO;
import vo.ShareScoreVO;

public interface StrategyService {

	/**
	 * 
	 * @return �?10股票得分
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
	 * @return 回报�?+风险+适宜�?+价�??+潜力
	 */
	public ArrayList<DonutVO> getSingleAspect();
}
