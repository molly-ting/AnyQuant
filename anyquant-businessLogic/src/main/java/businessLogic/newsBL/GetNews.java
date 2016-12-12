package businessLogic.newsBL;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import blService.newsBLService.GetNewsBLService;
import crawler.init.NewsCrawlerController;
import dataService.newsService.GetNewsDataService;
import dto.ImageNews;
import dto.News;
import vo.ImageNewsVO;
import vo.NewsVO;

public class GetNews implements GetNewsBLService{

	@Override
	public ArrayList<NewsVO> getNewsList() {
		ArrayList<NewsVO> voList=new ArrayList<>();
		GetNewsDataService service=new NewsCrawlerController();
		ArrayList<News> dto=service.getNewsList();
		for (News news : dto) {
			NewsVO vo=new NewsVO(news.getContent(), news.getURL());
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public ArrayList<ImageNewsVO> getImageNewsList() {
		ArrayList<ImageNewsVO> voList =new ArrayList<>();
		GetNewsDataService service=new NewsCrawlerController();
		ArrayList<ImageNews> dtolist=service.getImageNewsList();
		if(dtolist == null)
			return null;
		for (ImageNews imageNews : dtolist) {
			ImageNewsVO vo=new ImageNewsVO(imageNews.getContent(), imageNews.getURL());
			URL imageurl;
			try {
				imageurl = new URL(imageNews.getPictureURL());
				ImageIcon icon =new ImageIcon(imageurl);
				vo.setImageicon(icon);
				vo.setPicurl(imageNews.getPictureURL());
				voList.add(vo);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return voList;
	}

}
