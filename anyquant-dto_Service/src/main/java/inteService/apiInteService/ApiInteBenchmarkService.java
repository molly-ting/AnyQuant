package inteService.apiInteService;

import java.util.ArrayList;

import dto.ShareDTO;

public interface ApiInteBenchmarkService {

	/**
	 * 
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 从开始到结束日期内的指定大盘信息
	 */
	public ArrayList<ShareDTO> getBenchmark(String strategy,String start,String end);
}
