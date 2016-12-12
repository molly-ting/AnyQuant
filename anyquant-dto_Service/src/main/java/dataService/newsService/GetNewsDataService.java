package dataService.newsService;

import java.util.ArrayList;

import dto.ImageNews;
import dto.News;

public interface GetNewsDataService {
	public ArrayList<News> getNewsList();
	public ArrayList<ImageNews> getImageNewsList();
}
