package blSerivce.predictSerivce;

import java.util.ArrayList;

import vo.SimpilifiedShareVO;

public interface CombinationService {

	ArrayList<SimpilifiedShareVO> selectShare(double[] num);
	
}
