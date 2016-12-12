package combination;

import java.util.ArrayList;

import accuracy.NormalDistribution;
import blSerivce.predictSerivce.EstimateService;
import blSerivce.predictSerivce.NormalDistributionService;
import blService.getInfoBLService.GetShareBLService;
import businessLogic.getInfoBL.Share;
import vo.EstimateVO;
import vo.FullShareVO;
import vo.MoneyVO;
import vo.PartitionVO;

public class Estimate implements EstimateService {

	public EstimateVO estimate(ArrayList<PartitionVO> list) {
		return getEstimation(list);
	}

	public ArrayList<MoneyVO> shareEstimate(ArrayList<PartitionVO> list) {
		return getShareEstimation(list);
	}

	private EstimateVO getEstimation(ArrayList<PartitionVO> list) {
		GetShareBLService service = new Share();

		double possibleLow = 0;
		double possibleHigh = 0;
		double highest = 0;
		double lowest = 0;
		for (PartitionVO partition : list) {
			String id = partition.getId();
			FullShareVO share = service.getOneShareToday(id);
			double cost = share.getClose();
			int num = partition.getNumber();
			NormalDistributionService nm = new NormalDistribution(id);
			possibleHigh += num * (nm.getMaxOneSigma() - cost);
			possibleLow += num * (nm.getMinOneSigma() - cost);
			highest += num * (nm.getMaxThreeSigma() - cost);
			lowest += num * (nm.getMinThreeSigma() - cost);
			nm = null;
		}

		return new EstimateVO(possibleLow, possibleHigh, highest, lowest);
	}

	private ArrayList<MoneyVO> getShareEstimation(ArrayList<PartitionVO> list) {
		GetShareBLService service = new Share();
		ArrayList<MoneyVO> moneyList = new ArrayList<MoneyVO>();

		for (PartitionVO partition : list) {
			String id = partition.getId();
			FullShareVO share = service.getOneShareToday(id);
			double cost = share.getClose();
			int num = partition.getNumber();
			NormalDistributionService nm = new NormalDistribution(id);
			double p = (nm.getMaxOneSigma() + nm.getMinOneSigma()) / 2;
			double income = num * (p - cost);
			MoneyVO money = new MoneyVO(id, income);
			moneyList.add(money);
			nm = null;
		}

		return moneyList;

	}
}
