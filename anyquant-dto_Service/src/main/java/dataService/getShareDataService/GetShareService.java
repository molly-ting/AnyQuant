package dataService.getShareDataService;

import java.util.Iterator;

public interface GetShareService {
	public String getShareName(String code);

	@SuppressWarnings("rawtypes")
	public Iterator getSHShare();

	@SuppressWarnings("rawtypes")
	public Iterator getSZShare();
}
