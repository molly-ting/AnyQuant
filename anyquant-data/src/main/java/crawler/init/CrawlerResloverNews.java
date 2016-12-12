package crawler.init;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.ImageNews;
import dto.News;

public class CrawlerResloverNews {
	String rawhtml;
	static CrawlerResloverNews instance=null;
	private CrawlerResloverNews() {
		// TODO Auto-generated constructor stub
	}
	public static CrawlerResloverNews getInstance() {
		if(instance==null){
			instance=new CrawlerResloverNews();
		}
		return instance;
	}
	
	public void setHtml(String rawhtml) {
		this.rawhtml=rawhtml;
	}
	
	
	
	public ArrayList<News> getNewsList(){
		ArrayList<News> arr=new ArrayList<>();
		String regex1="<div class=\"news\">(.+?)</h3>";
		String regex2="href=\"(.+?)\"";
		String regex3="\">(.+?)</a>";
		
		Matcher matcher=Pattern.compile(regex1, Pattern.DOTALL).matcher(rawhtml);
		while (matcher.find()) {
			String line=matcher.group(1);
			line=line.substring(16);
			
			Matcher matcher2=Pattern.compile(regex2,Pattern.DOTALL).matcher(line);
			matcher2.find();
			String url=matcher2.group(1);
			
			Matcher matcher3=Pattern.compile(regex3,Pattern.DOTALL).matcher(line);
			matcher3.find();
			String content=matcher3.group(1);
			
			News news=new News(content, url);
			
			arr.add(news);
		}
		
		return arr;
	}
	
	
	public ArrayList<ImageNews> getImageNewsList() {
		ArrayList<ImageNews> arr=new ArrayList<>();
		String strhttp="http://";
		String regex1="<div id=\"contentA\" class=\"area\">(.+?)</script>";
		String regex2="http(.+?)}";
		String regex3="://(.+?)\",";
		String regex4="l:\"(.+?)\"";
		String regex5="t:\"(.+?)\"";
		
		Matcher matcher=Pattern.compile(regex1,Pattern.DOTALL).matcher(rawhtml);
		matcher.find();
		String line=matcher.group(1);
		
		Matcher matcher2=Pattern.compile(regex2,Pattern.DOTALL).matcher(line);
		while (matcher2.find()) {
			String block=matcher2.group(1);
			Matcher matcher3=Pattern.compile(regex3, Pattern.DOTALL).matcher(block);
			matcher3.find();
			String picurl=strhttp;
			picurl+=matcher3.group(1);
			
			Matcher matcher4=Pattern.compile(regex4,Pattern.DOTALL).matcher(block);
			matcher4.find();
			String url=matcher4.group(1);
		
			
			Matcher matcher5=Pattern.compile(regex5,Pattern.DOTALL).matcher(block);
			matcher5.find();
			String content=matcher5.group(1);

			ImageNews news=new ImageNews(content, url);
			news.setPictureURL(picurl);
			
			arr.add(news);
			
		}

		return arr;
	}
	
	
	
	
	
}
