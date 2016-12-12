package inteji;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import accuracy.NormalDistribution;
import blSerivce.predictSerivce.NormalDistributionService;
import dto.ShareDTO;
import sql.AdvancedUtil;
import vo.NormalDistributionVO;

public class NormalTest {

	@Test
	public void test() {
		AdvancedUtil ad = new AdvancedUtil();
		ArrayList<ShareDTO> list = ad.getAllShareInOneDay();

		for (ShareDTO share : list) {
			NormalDistributionService normal = new NormalDistribution(share.getID());

			System.out.println(share.getID() + " " + normal.getMax() + " " + normal.getMin() + " "
					+ normal.getMinThreeSigma() + " " + normal.getMaxThreeSigma());
			ArrayList<NormalDistributionVO> line = normal.getNormalDistribution();
			for(NormalDistributionVO norm:line){
				System.out.print(norm.getY()+"   ");
			}
			System.out.println();
			System.out.println();
		}
	}

}
