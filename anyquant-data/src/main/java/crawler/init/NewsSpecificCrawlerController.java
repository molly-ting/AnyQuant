package crawler.init;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sleepycat.je.rep.monitor.NewMasterEvent;

import crawler.CrawlConfig;
import crawler.CrawlController;
import crawler.fetcher.PageFetcher;
import crawler.robotstxt.RobotstxtConfig;
import crawler.robotstxt.RobotstxtServer;
import dataService.newsService.GetSpecificNewsDataService;
import dto.ImageNews;
import dto.News;

public class NewsSpecificCrawlerController implements GetSpecificNewsDataService{
	private static final Logger logger = LoggerFactory.getLogger(BasicCrawlController.class);
	private static String precode="";
	private static boolean isinite=false;
	
	public void start(String code) throws Exception {
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
		controller.addSeed("http://stockpage.10jqka.com.cn/"+code+"/");
		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(NewsSpecificCrawler.class, numberOfCrawlers);
		System.out.println("...........................res........................");
	}
	
	
	
	@Override
	public ArrayList<News> getNews(String code) {
		try {
			
			if(precode.equals(code)&&isinite==true){
				return CrawlerResolverSpecificNews.getInstance().getNewslist();
			}
			start(code);
			CrawlerResolverSpecificNews.getInstance().goCrawl();
			precode=code;
			isinite=true;
			return CrawlerResolverSpecificNews.getInstance().getNewslist();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<News> getAnnounce(String code) {
		if(code.equals(precode)&&isinite==true){
			return CrawlerResolverSpecificNews.getInstance().getAnnouncelist();
		}
		try {
			start(code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CrawlerResolverSpecificNews.getInstance().goCrawl();
		precode=code;
		isinite=true;
		return CrawlerResolverSpecificNews.getInstance().getAnnouncelist();
	}

}
