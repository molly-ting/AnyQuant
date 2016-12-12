package businessLogic.sortBL;

import java.util.ArrayList;

import blService.sortBLService.SortBLService;
import vo.ShareVO;
import vo.StrategyVO;

public class Sort implements SortBLService {

	@Override
	public ArrayList<ShareVO> sort(StrategyVO strategy, 
			ArrayList<ShareVO> list) {
		if(list == null)
			return null;
		if(list.isEmpty())
			return null;
		else
			quicksort(strategy.getStrategy(),list, 0, list.size()-1);
		return list;
	}

	private void quicksort(String strategy,ArrayList<ShareVO> list, int left, int right) {
		int dp;
		if (left < right) {
			dp = partition(strategy,list, left, right);
			quicksort(strategy,list, left, dp - 1);
			quicksort(strategy,list, dp + 1, right);
		}

	}

	private int partition(String strategy,ArrayList<ShareVO> list, int left, int right) {
		Compare c = new Compare();
		ShareVO pivot = list.get(left);//n[left];
		
		while (left < right) {
			//n[right] >= pivot
			while (left < right && c.compare(strategy, list.get(right), pivot)<=0)
				right--;
			if (left < right){
				list.set(left++, list.get(right));//n[left++] = n[right];
				
			}
			//n[left] <= pivot
			while (left < right && c.compare(strategy, list.get(left), pivot)>=0)
				left++;
			if (left < right){
				list.set(right--, list.get(left));//n[right--] = n[left];
			}
		}
		list.set(left, pivot);//n[left] = pivot;
		return left;
	}
}
