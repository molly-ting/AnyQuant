/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package crawler.init;

import java.util.ArrayList;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crawler.CrawlConfig;
import crawler.CrawlController;
import crawler.fetcher.PageFetcher;
import crawler.robotstxt.RobotstxtConfig;
import crawler.robotstxt.RobotstxtServer;
import dataService.industryService.GetTopIndustries;
import dto.IndustryInfoTop50;

/**
 * @author Yasser Ganjisaffar
 */
public class BasicCrawlController implements GetTopIndustries{
	private static final Logger logger = LoggerFactory.getLogger(BasicCrawlController.class);
	private static boolean init=false;
	private static ArrayList<IndustryInfoTop50> top50list;

	public void start() throws Exception {
		// if (args.length != 2) {
		// logger.info("Needed parameters: ");
		// logger.info("\t rootFolder (it will contain intermediate crawl
		// data)");
		// logger.info("\t numberOfCralwers (number of concurrent threads)");
		// return;
		// }

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
		controller.addSeed("http://q.10jqka.com.cn/stock/thshy/");
		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(BasicCrawler.class, numberOfCrawlers);
		System.out.println("...........................res........................");
		//CrawlerResloverIndustries.getInstance().getEveryIndustriesCode();

		//
		//ArrayList<IndustryInfoTop50> arr = CrawlerResloverIndustries.getInstance().getTopIndustriesList();

		
//		for (IndustryInfoTop50 industryInfo : arr) {
//			System.out.println(a + "." + industryInfo.getName() + " :" + industryInfo.getPercent() + " "
//					+ industryInfo.getAveragePrice() + " " + industryInfo.getTotalvolume() + " "
//					+ industryInfo.getTotalmoney());
//		}

	}

	@Override
	public ArrayList<IndustryInfoTop50> geTop50s() {
		try {
			if(init){
				return top50list;
			}
			
			start();
			
			top50list=CrawlerResloverIndustries.getInstance().getTopIndustriesList();
			
			if(top50list==null){
				return null;
			}
			
			top50list.sort(new Top50Compatator());
			//Collections.sort(top50list,new Top50Compatator());
			
			
			init=true;
			return top50list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}