package businessLogicTest;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.getShare.ShareGetter;
import dataService.getShareDataService.GetShareService;

public class GetNewShareTest {

	@Test
	public void test() {
		GetShareService service=new ShareGetter("sz");
		System.out.println(service.getShareName("sz002632"));
		Iterator iter =service.getSZShare();
		int i=0;
		while (iter.hasNext()) {
			Entry e=(Entry) iter.next();
			String code=(String) e.getKey();
			String name=(String) e.getValue();
			System.out.println(code+name);
			
			i++;
		}
		System.out.println(i);
		
		
		
		
		
	}

}
