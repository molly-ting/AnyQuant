package blService.newsBLService;

import java.util.ArrayList;

import vo.NewsVO;

public interface GetSpecificNewsBLService {
	public ArrayList<NewsVO> getNewsList(String code);
	public ArrayList<NewsVO> getAnnounceList(String code);
}
