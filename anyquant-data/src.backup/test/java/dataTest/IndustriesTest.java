package dataTest;

import static org.junit.Assert.*;

import org.junit.Test;

import crawler.init.BasicCrawlController;

public class IndustriesTest {

	@Test
	public void test() {
		BasicCrawlController ctr=new BasicCrawlController();
		int industrieslen=ctr.geTop50s().size();
		assertTrue(industrieslen>0);
		
	}

}
