package blSerivce.predictSerivce;

import java.util.ArrayList;

import vo.NormalDistributionVO;

public interface NormalDistributionService {

	public ArrayList<NormalDistributionVO> getNormalDistribution();

	public double getMax();

	public double getMin();

	public double getMaxThreeSigma();

	public double getMinThreeSigma();

	public double getMaxOneSigma();

	public double getMinOneSigma();

	public double adjust(double x);
}
