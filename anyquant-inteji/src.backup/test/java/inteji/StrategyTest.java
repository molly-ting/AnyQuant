package inteji;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import blSerivce.predictSerivce.StrategyService;
import startegy.Strategy;
import vo.DonutVO;
import vo.ShareScoreVO;

public class StrategyTest {

	@Test
	public void test() {
		StrategyService service = new Strategy();
		ArrayList<ShareScoreVO> list = service.getStartegy();

		for (ShareScoreVO share : list) {
			System.out.println(share.getId() + " " + share.getName() + " " + share.getSum() + " " + share.getBuy() + " "
					+ share.getIncrease() + " " + share.getPayOff() + " " + share.getRisk() + " " + share.getValue());
		}

		ArrayList<DonutVO> donutList = service.getSingleAspect();

		for (DonutVO donut : donutList) {
			System.out.println(donut.getId() + " " + donut.getName() + " " + donut.getMax());
			int[] arr = donut.getArr();
			for (int i = 0; i < arr.length; i++)
				System.out.print(arr[i]+" ");
			System.out.println();
		}
	}

}
