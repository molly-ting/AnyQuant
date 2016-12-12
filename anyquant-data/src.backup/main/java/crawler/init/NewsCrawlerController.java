package crawler.init;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crawler.CrawlConfig;
import crawler.CrawlController;
import crawler.fetcher.PageFetcher;
import crawler.robotstxt.RobotstxtConfig;
import crawler.robotstxt.RobotstxtServer;
import dataService.newsService.GetNewsDataService;
import dto.ImageNews;
import dto.IndustryInfoTop50;
import dto.News;

public class NewsCrawlerController implements GetNewsDataService{
	private static final Logger logger = LoggerFactory.getLogger(BasicCrawlController.class);
	private static boolean initimage=false;
	private static boolean initnews=false;
	private static ArrayList<News> newlist;
	private static ArrayList<ImageNews> imageNewslist;

	public void start() throws Exception {
		/*
		 * 1 crawlStorageFolder is a folder where intermediate crawl data is
		 * stored.
		 */
		String crawlStorageFolder = "target/";

		/*
		 * numberOfCrawlers shows the number of concurrent threads that should
		 * be initiated for crawling.
		 */
		int numberOfCrawlers = 1;

		CrawlConfig config = new CrawlConfig();

		config.setCrawlStorageFolder(crawlStorageFolder);

		/*
		 * Be polite: Make sure that we don't send more than 1 request per
		 * second (1000 milliseconds between requests).
		 */
		config.setPolitenessDelay(1000);

		/*
		 * You can set the maximum crawl depth here. The default value is -1 for
		 * unlimited depth
		 */
		config.setMaxDepthOfCrawling(0);

		/*
		 * You can set the maximum number of pages to crawl. The default value
		 * is -1 for unlimited number of pages
		 */
		config.setMaxPagesToFetch(1);

		/**
		 * Do you want crawler4j to crawl also binary data ? example: the
		 * contents of pdf, or the metadata of images etc
		 */
		config.setIncludeBinaryContentInCrawling(false);

		config.setMaxOutgoingLinksToFollow(1);

		/*
		 * Do you need to set a proxy? If so, you can use:
		 * config.setProxyHost("proxyserver.example.com");
		 * config.setProxyPort(8080);
		 *
		 * If your proxy also needs authentication:
		 * config.setProxyUsername(username); config.getProxyPassword(password);
		 */

		/*
		 * This config parameter can be used to set your crawl to be resumable
		 * (meaning that you can resume the crawl from a previously
		 * interrupted/crashed crawl). Note: if you enable resuming feature and
		 * want to start a fresh crawl, you need to delete the contents of
		 * rootFolder manually.
		 */
		config.setResumableCrawling(false);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
		controller.addSeed("http://business.sohu.com");
		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(NewsCrawler.class, numberOfCrawlers);
		System.out.println("...........................res........................");
	}


	@Override
	public ArrayList<News> getNewsList() {
		try {
			if(initnews){
				return newlist;
			}
			start();
			initnews=true;
			newlist=CrawlerResloverNews.getInstance().getNewsList();
			return newlist;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public ArrayList<ImageNews> getImageNewsList() {
		try {
			if(initimage){
				return imageNewslist;
			}
			
			
			start();
			initimage=true;
			imageNewslist=CrawlerResloverNews.getInstance().getImageNewsList();
			return imageNewslist;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
}
