package crawler.init;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.ImageNews;
import dto.News;

public class CrawlerResolverSpecificNews {
	String rawhtml;
	private ArrayList<News> newlist=new ArrayList<>();
	private ArrayList<News> announcelist=new ArrayList<>();
	
	
	static CrawlerResolverSpecificNews instance=null;
	private CrawlerResolverSpecificNews() {
		// TODO Auto-generated constructor stub
	}
	public static CrawlerResolverSpecificNews getInstance() {
		if(instance==null){
			instance=new CrawlerResolverSpecificNews();
		}
		return instance;
	}
	
	public void setHtml(String rawhtml) {
		this.rawhtml=rawhtml;
	}
	
	public ArrayList<News> getNewslist(){
		return newlist;
	}
	
	public ArrayList<News> getAnnouncelist(){
		return announcelist;
	}
	
	
	
	
	public void goCrawl(){
		newlist.clear();
		announcelist.clear();
		
		
		String regex1="f10_spqk_gsxw\">(.+?)</ul>";
		Matcher matcher=Pattern.compile(regex1, Pattern.DOTALL).matcher(rawhtml);
		
		String regex2="f10_spqk_gsgg\">(.+?)</ul>";
		Matcher matcher2=Pattern.compile(regex2, Pattern.DOTALL).matcher(rawhtml);
		
		String newsline="";
		String announceline="";
		while (matcher.find()) {
			newsline=matcher.group(1);
		}
		while (matcher2.find()) {
			announceline=matcher2.group(1);
		}
		
		
		String regex3="href=\"(.+?)\" t";
		String regex4="blank\">(.+?)</a>";
		Matcher matcher3=Pattern.compile(regex3, Pattern.DOTALL).matcher(newsline);
		Matcher matcher4=Pattern.compile(regex4, Pattern.DOTALL).matcher(newsline);
		while (matcher3.find()&&matcher4.find()) {
			News n = new News(matcher4.group(1), matcher3.group(1));
			newlist.add(n);	
		}
		Matcher matcher5=Pattern.compile(regex3, Pattern.DOTALL).matcher(announceline);
		Matcher matcher6=Pattern.compile(regex4, Pattern.DOTALL).matcher(announceline);
		while (matcher5.find()&&matcher6.find()) {
			News n = new News(matcher6.group(1), matcher5.group(1));
			announcelist.add(n);	
		}
	}
	
}
