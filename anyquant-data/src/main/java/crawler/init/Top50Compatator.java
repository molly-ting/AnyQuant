package crawler.init;

import java.util.Comparator;

import dto.IndustryInfoTop50;

public class Top50Compatator implements Comparator<IndustryInfoTop50>{

	@Override
	public int compare(IndustryInfoTop50 o1, IndustryInfoTop50 o2) {
		double a=Double.parseDouble(o1.getTotalvolume());
		double b=Double.parseDouble(o2.getTotalvolume());
        return (int)(b-a);
	}

}
