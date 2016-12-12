package crawler.init;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.*;

import org.apache.poi.ss.formula.functions.Match;

import dto.IndustryInfoCode;
import dto.IndustryInfoTop50;
import dto.NameAndCode;

public class CrawlerResloverIndustries {
	
	
	
	String rawhtml;
	static CrawlerResloverIndustries instance=null;
	private CrawlerResloverIndustries() {
		// TODO Auto-generated constructor stub
	}
	public static CrawlerResloverIndustries getInstance() {
		if(instance==null){
			instance=new CrawlerResloverIndustries();
		}
		return instance;
	}
	
	public void setHtml(String rawhtml) {
		this.rawhtml=rawhtml;
	}
	
	public ArrayList<IndustryInfoTop50> getTopIndustriesList(){
	
		//rawhtml="<!-- 挂旗广告 2014-09-16 end -->";
		ArrayList<IndustryInfoTop50> result=new ArrayList<>();
//		String regex="<td class=\"first tc\">1</td>(.+?)</div>";
		String regex="<tbody>(.+?)</div>";
		Matcher matcher;
		try{
		 matcher=Pattern.compile(regex,Pattern.DOTALL).matcher(rawhtml);
		}catch(Exception e){
			return null;
		}
		String part="";
		while(matcher.find()){
			  part=matcher.group(1);
		}
	
		System.out.println(part);
		
		
		String regex1="<tr (.+?)</tr>";
		Matcher matcher1=Pattern.compile(regex1,Pattern.DOTALL).matcher(part);
		
		String regex2="/\">(.+?)</a></td>";
//		String regex2="class=(.+?)>";
		String regex3="<td class=\"\">(.+?)</td>";
		String regex4="\">(.+?)</td>";

		while (matcher1.find()) {
			String str=matcher1.group(1);
			
			
			String industryname,avprice,sharenum;
			Matcher matcher2=Pattern.compile(regex2,Pattern.DOTALL).matcher(str);
			
			Matcher matcher3=Pattern.compile(regex3,Pattern.DOTALL).matcher(str);
			
					
			matcher2.find();
			matcher3.find();

			industryname=matcher2.group(1);
			sharenum=matcher3.group(1);
			
			String lines[]=str.split("\n");
			
			Matcher matcher4=Pattern.compile(regex4, Pattern.DOTALL).matcher(lines[4]);
			matcher4.find();
			avprice=matcher4.group(1);
			
			Matcher matcher5=Pattern.compile(regex4, Pattern.DOTALL).matcher(lines[5]);
			matcher5.find();
			String percent=matcher5.group(1);
			
			Matcher matcher6=Pattern.compile(regex4, Pattern.DOTALL).matcher(lines[6]);
			matcher6.find();
			String totalvolume=matcher6.group(1);
			
			Matcher matcher7=Pattern.compile(regex4, Pattern.DOTALL).matcher(lines[7]);
			matcher7.find();
			String totalmoney=matcher7.group(1);
			
			IndustryInfoTop50 share=new IndustryInfoTop50(industryname, avprice, percent, totalvolume, totalmoney);
			result.add(share);
		}
		return result;
	}

	
	public ArrayList<String> getEveryIndustriesCode(){
		ArrayList<NameAndCode> arr=new ArrayList<>();
		String regex="<tbody>(.+?)</tbody>";
		Matcher matcher1=Pattern.compile(regex,Pattern.DOTALL).matcher(rawhtml);
		matcher1.find();
	//	System.out.println(matcher1.group(1));
		String str=matcher1.group(1);
		String regex1="target=\"_blank\">(.+?)</a></td>";
		Matcher matcher2=Pattern.compile(regex1,Pattern.DOTALL).matcher(str);
		while (matcher2.find()) {
			String code=matcher2.group(1);
			matcher2.find();
			String name=matcher2.group(1);
			NameAndCode x=new NameAndCode(name, code);
//			System.out.println(x.getName()+x.getCode());
			arr.add(x);
		}
		
		
		
		
		String fpath="SerializableData/industrylist.ser";
		ArrayList<IndustryInfoCode> savelist =null;
//		FileInputStream fis;
//		try {
//			fis = new FileInputStream(fpath);
//			ObjectInputStream ois =new ObjectInputStream(fis);
//			savelist=(ArrayList<IndustryInfoCode>)ois.readObject();
//			ois.close();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//
//		
//		String iname="石油矿业开采";
//		IndustryInfoCode in=new IndustryInfoCode(iname, arr);
//	
//		
//		savelist.add(in);
//		FileOutputStream fos;
//		try {
//			fos = new FileOutputStream(fpath,false);
//			ObjectOutputStream oos =new ObjectOutputStream(fos);
//			oos.writeObject(savelist);
//			oos.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
		
		
		
//		
		FileInputStream fis;
		int n=0;
		try {
			fis = new FileInputStream(fpath);
			ObjectInputStream ois =new ObjectInputStream(fis);
			ArrayList<IndustryInfoCode> obj=(ArrayList<IndustryInfoCode>)ois.readObject();
			ois.close();
			for (IndustryInfoCode industryInfoCode : obj) {
				System.out.println(industryInfoCode.getIndustryname());
				
				ArrayList<NameAndCode> nameandcode=industryInfoCode.getCodelist();
				n++;
				for (NameAndCode nameAndCode2 : nameandcode) {
					System.out.println(nameAndCode2.getName()+nameAndCode2.getCode());
					
				}
				System.out.println();
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(n);
		
		
		
		
		
		return null;
		
		
	}
	
	
}
