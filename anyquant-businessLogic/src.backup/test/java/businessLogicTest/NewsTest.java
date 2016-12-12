package businessLogicTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import crawler.init.NewsCrawlerController;
import dataService.newsService.GetNewsDataService;
import dto.ImageNews;
import dto.News;

public class NewsTest {

	@Test
	public void test() {
		
		
		//test single news
		GetNewsDataService service=new NewsCrawlerController();
//		ArrayList<News> list=service.getNewsList();
//		for (News news : list) {
//			System.out.println(news.getURL()+" "+news.getContent());
//		}
		
		//test image news
		ArrayList<ImageNews>list=service.getImageNewsList();
		for (ImageNews imageNews : list) {
			System.out.println(imageNews.getPictureURL()+"  "+imageNews.getURL()+"  "+imageNews.getContent());
		}
		
		
		
	}

}
