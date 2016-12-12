package dataService.newsService;

import java.util.ArrayList;

import dto.News;

public interface GetSpecificNewsDataService {
public ArrayList<News> getNews(String code);
public ArrayList<News> getAnnounce(String code);
}
