package blSerivce.predictSerivce;

import java.util.ArrayList;

import vo.EstimateVO;
import vo.MoneyVO;
import vo.PartitionVO;

public interface EstimateService {

	public EstimateVO estimate(ArrayList<PartitionVO> list);
	
	public ArrayList<MoneyVO> shareEstimate(ArrayList<PartitionVO> list);
	
}
