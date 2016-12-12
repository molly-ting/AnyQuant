package businessLogicTest;

import java.util.Iterator;
import java.util.Map.Entry;

import data.getShare.ShareGetter;
import junit.framework.TestCase;

public class GetListTest extends TestCase{

	public void test(){
		ShareGetter s = new ShareGetter("sh");
		Iterator map = s.getSHShare();
		while (map.hasNext()) {
			Entry entry = (Entry) map.next();
			String id = (String) entry.getKey();
			id = "sh" + id;
			System.out.println(id+entry.getValue());
		}
	}
}
