package dataService.sqlService;

import java.util.ArrayList;

import dto.ShareDTO;

public interface SQLUtilityService {

	public double getRate(String id);

	public ArrayList<ShareDTO> getAllShareInOneDay();

	public ShareDTO getLastDay(String id);
	
	public ArrayList<ShareDTO> find(String text);
}
