package businessLogicTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Test;

import data.IOHelper;
import integration.apiInte.ShareInte;

public class ShareCodeAndUrlTest {
	@Test
	public void test() {
		HashMap<String, String> map=new HashMap<>();
		HashMap<String, String> realmap=new HashMap<>();
		IOHelper io=new IOHelper();
		try {
			map=(HashMap<String, String>)io.readFromDisk("/Users/peiyulin/Desktop/anyquant/anyquant-data/SerializableData/szcodename.ser");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		ShareInte inte=new ShareInte();
		Iterator iter=inte.getShareList("sz");
		int i=0;
		while (iter.hasNext()) {
			Entry entry=(Entry) iter.next();
			String code=(String)entry.getKey();
			String url=(String) entry.getValue();
			
			String uuu=url.substring(0, 35);
			
			
			code=code.substring(2);
//			System.out.println(code+"  "+uuu);
			
			
			if(map.get(code)!=null){//save hashmap with code and name
					
				realmap.put(code, map.get(code));
				i++;
			}
			
		}
		
		IOHelper ioHelper=new IOHelper();
		try {
			ioHelper.writeToDisk("/Users/peiyulin/Desktop/anyquant/anyquant-data/SerializableData/SZcodeAndname.ser", realmap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);		
	}
	
}
