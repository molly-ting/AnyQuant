package dataTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import org.junit.Test;

import data.IOHelper;

public class ReadHashMap {
	@Test
	public void test() {
		IOHelper io=new IOHelper();
		try {
			HashMap<String, String> map=(HashMap<String, String>)io.readFromDisk("SerializableData/BPS.ser");
			System.out.println(map.get("002624"));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
