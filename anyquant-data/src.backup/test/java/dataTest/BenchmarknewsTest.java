package dataTest;

import static org.junit.Assert.*;

import org.junit.Test;

import crawler.init.NewsCrawlerController;

public class BenchmarknewsTest {

	@Test
	public void test() {
		NewsCrawlerController ctr=new NewsCrawlerController();
		int newslen=ctr.getNewsList().size();
		assertTrue(newslen>0);
		
		int imagenewslen=ctr.getImageNewsList().size();
		assertTrue(imagenewslen>0);
		
		
	}

}
