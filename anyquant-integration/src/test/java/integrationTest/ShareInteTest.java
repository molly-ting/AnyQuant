package integrationTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import dto.ShareDTO;
import integration.apiInte.ShareInte;

public class ShareInteTest {

	@Test
	public void test() {
		ShareInte shareInte = new ShareInte();	
		boolean key1 = false;
		boolean key2 = true;
		boolean key3 = false;
		
		if (key1) {
			Iterator idList = shareInte.getShareList("sh");
			if (idList != null) {
				for (; idList.hasNext();) {
					Entry entry = (Entry)idList.next();
					String key = (String) entry.getKey();
					if(key.equals("sh000300"))
						System.out.println(entry);
				}
			}
		}
		if(key2){
			ArrayList<ShareDTO> list = shareInte.getShareDetail("sh000300", 
					"2016-03-02", "2016-03-07", "open+high+pe");
			for (ShareDTO share:list) {
				System.out.println(share.getOpen()+" "+share.getClose()+" "+share.getDate()+" "+share.getPe());
			}
		}
//		if(key3){
//			ListIterator<ShareDTO> list = shareInte.getShareDetail("sh000300", 
//					null, null, "open+close");
//			for (; list.hasNext();) {
//				ShareDTO share = list.next();
//				System.out.println(share.getOpen()+" "+share.getClose()+" "+share.getDate());
//			}
//		}
	}

}
