package businessLogicTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import crawler.init.CodeTransfer;
import data.GetAllIndustries;
import dataService.industryService.GetAllIndustriesService;
import dto.NameAndCode;

public class IndustryCodeTest {

	@Test
	public void test() {
		GetAllIndustriesService service=new GetAllIndustries();
		ArrayList<String> names=service.getIndustryNames();
		for (String string : names) {
			System.out.println(string);
		}
		
		ArrayList<NameAndCode> codes=service.getIndustryCode("证券");
		
		for (NameAndCode nameAndCode : codes) {
			String code= new CodeTransfer().transfer(nameAndCode.getCode());
			System.out.println(nameAndCode.getName()+" "+code);
		}
		
	}

}
