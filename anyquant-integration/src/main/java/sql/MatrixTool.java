package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MatrixTool {
	private String id;
	private double[][] matrix;
	private int input = 0;

	public MatrixTool(String id) {
		this.id = id;
		this.matrix = getData();
	}

	public double[] getWeight() {
		return matrix[0];
	}

	public double[] getCenter() {
		return matrix[1];
	}

	public double[] getDelta() {
		return matrix[2];
	}

	public int getInput() {
		return input;
	}

	private double[][] getData() {
		String sql = "select input,m from node";

		DBHelper database = DBHelper.getInstance();
		PreparedStatement statement = null;
		try {
			statement = database.conn.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			set.next();
			this.input = set.getInt(1);
			int node = set.getInt(2);

			double[][] x = new double[3][node];

			sql = "select * from matrix where id='" + id + "'";
			statement = database.conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			int index = 0;
			while (result.next()) {
				x[0][index] = result.getDouble(2);
				x[1][index] = result.getDouble(3);
				x[2][index] = result.getDouble(4);
				index++;
			}

			return x;
		} catch (Exception e) {
			e.printStackTrace();
			return new double[3][0];
		}
	}
}
