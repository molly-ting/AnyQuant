package integrationTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.ListIterator;

import org.junit.Test;

import dto.BenchmarkDTO;
import dto.ShareDTO;
import integration.apiInte.BenchmarkInte;

public class BenchmarkInteTest {

	@Test
	public void test() {
		BenchmarkInte ben = new BenchmarkInte();
		ArrayList<ShareDTO> list = ben.getBenchmark("open+pe", "2016-03-01", "2016-03-05");
		
		for(BenchmarkDTO dto:list){
			System.out.println(dto.getOpen()+" "+dto.getPe()+" "+dto.getPb());
		}
	}

}
