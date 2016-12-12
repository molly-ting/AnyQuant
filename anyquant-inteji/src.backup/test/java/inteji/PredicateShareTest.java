package inteji;

import java.util.ArrayList;

import junit.framework.TestCase;
import rbf.RBFNet;
import vo.PredicateVO;

public class PredicateShareTest  extends TestCase{

	public void test(){
		RBFNet forcast = new RBFNet("sh600000");
		ArrayList<PredicateVO> list = forcast.predict(100);
		for(PredicateVO predicate:list){
			System.out.println(predicate.getDate()+" "+predicate.getValue());
		}
	}
}
