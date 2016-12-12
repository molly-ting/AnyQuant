package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dataService.industryService.GetAllIndustriesService;
import dto.IndustryInfoCode;
import dto.NameAndCode;
import sql.DBHelper;

public class GetAllIndustries implements GetAllIndustriesService {
	ArrayList<IndustryInfoCode> arr = new ArrayList<IndustryInfoCode>();

	public GetAllIndustries() {
		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;

		String sql = "select distinct industry from industry";
		try {
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				ArrayList<NameAndCode> list = new ArrayList<NameAndCode>();
				String industry = set.getString(1);
				sql = "select id,name from industry where industry='" + industry + "'";
				statement = database.conn.prepareStatement(sql);
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					NameAndCode nameAndCode = new NameAndCode(result.getString(2), result.getString(1));
					list.add(nameAndCode);
				}
				IndustryInfoCode industryInfoCode = new IndustryInfoCode(industry, list);
				arr.add(industryInfoCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<IndustryInfoCode> getAllIndustryList() {
		return arr;
	}

	public ArrayList<NameAndCode> getIndustryCode(String name) {
		for (IndustryInfoCode industryInfoCode : arr) {
			if (name.equals(industryInfoCode.getIndustryname())) {
				return industryInfoCode.getCodelist();
			}
		}
		return null;
	}

	@Override
	public ArrayList<String> getIndustryNames() {
		ArrayList<String> names = new ArrayList<>();
		for (IndustryInfoCode industryInfoCode : arr) {
			String n = industryInfoCode.getIndustryname();
			names.add(n);
		}
		return names;
	}

}
