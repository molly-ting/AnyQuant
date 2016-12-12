package dataTest;

import static org.junit.Assert.*;

import org.junit.Test;

import crawler.init.NewsSpecificCrawlerController;

public class SpecificNewsTest {

	@Test
	public void test() {
		NewsSpecificCrawlerController ctr=new NewsSpecificCrawlerController();
		int announcelen=ctr.getAnnounce("600112").size();
		assertTrue(announcelen>0);
		
		
		int newslen=ctr.getNews("600112").size();
		assertTrue(newslen>0);
		
		
	}

}
