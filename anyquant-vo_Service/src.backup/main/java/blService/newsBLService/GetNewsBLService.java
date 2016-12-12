package blService.newsBLService;

import java.util.ArrayList;

import vo.ImageNewsVO;
import vo.NewsVO;



public interface GetNewsBLService {
	public ArrayList<NewsVO> getNewsList();
	public ArrayList<ImageNewsVO> getImageNewsList();
}
