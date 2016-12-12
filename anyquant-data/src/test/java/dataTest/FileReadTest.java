package dataTest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.IOHelper;



public class FileReadTest {

	@Test
	public void test() {
		
		HashMap<String, String> map=new HashMap<>();
		
		File file=new File("/Users/peiyulin/Desktop/meigujingzichan.txt");
		try {
			FileReader reader=new FileReader(file);
			BufferedReader br=new BufferedReader(reader);
			
			String line=null;
			int k=0;
			while ((line=br.readLine())!=null) {
				String code=line.substring(0, 6);
			
				String eps=line.substring(10);
				if(eps.charAt(1)=='-'){
					eps="0";
				}
				
				
				map.put(code, eps);
				
				k++;
			}
			System.out.println(k);
			IOHelper io=new IOHelper();
			
			if(io.writeToDisk("SerializableData/BPS.ser", map)){
				System.out.println("Success");
			}
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
