package businessLogic.newsBL;

import java.util.ArrayList;

import blService.newsBLService.GetSpecificNewsBLService;
import crawler.init.NewsSpecificCrawlerController;
import dataService.newsService.GetSpecificNewsDataService;
import dto.News;
import vo.NewsVO;

public class GetSpecificNews implements GetSpecificNewsBLService{

	@Override
	public ArrayList<NewsVO> getNewsList(String code) {
		ArrayList<NewsVO> voList=new ArrayList<>();
		GetSpecificNewsDataService service=new NewsSpecificCrawlerController();
		ArrayList<News> list=service.getNews(code);
		for (News news : list) {
			NewsVO vo=new NewsVO(news.getContent(), news.getURL());
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public ArrayList<NewsVO> getAnnounceList(String code) {
		ArrayList<NewsVO> voList=new ArrayList<>();
		GetSpecificNewsDataService service=new NewsSpecificCrawlerController();
		ArrayList<News> list=service.getAnnounce(code);
		for (News news : list) {
			NewsVO vo=new NewsVO(news.getContent(), news.getURL());
			voList.add(vo);
		}
		return voList;
	}

}
