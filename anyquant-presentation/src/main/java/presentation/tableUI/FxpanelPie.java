package presentation.tableUI;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.util.Duration;

public class FxpanelPie extends JFXPanel {

	private static final long serialVersionUID = 2174840580535960720L;

	public FxpanelPie() {

		System.out.println("显示了“图表”");
		Scene scene = new Scene(new Group());
		this.setScene(scene);

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Grapefruit", 0), new PieChart.Data("Oranges", 0), new PieChart.Data("Plums", 0),
				new PieChart.Data("Pears", 0), new PieChart.Data("Apples", 0));
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Imported Fruits");

		((Group) scene.getRoot()).getChildren().add(chart);

		Timeline tl = new Timeline();
		ArrayList<Integer> lis = new ArrayList<>();
		lis.add(13);
		lis.add(25);
		lis.add(10);
		lis.add(22);
		lis.add(30);

		EventHandler<ActionEvent> eve = new EventHandler<ActionEvent>() {
			int i = 0;

			@Override
			public void handle(ActionEvent event) {
				PieChart.Data a = (PieChart.Data) pieChartData.get(i);
				switch (i) {
				case 0:
					a.setPieValue(13);
					break;
				case 1:
					a.setPieValue(25);
					break;
				case 2:

					a.setPieValue(10);
					break;
				case 3:

					a.setPieValue(22);
					break;
				case 4:

					a.setPieValue(30);
					tl.stop();
					break;
				default:
					break;
				}
				i++;

			}
		};

		tl.getKeyFrames().add(new KeyFrame(Duration.millis(100), eve));

		tl.setCycleCount(Animation.INDEFINITE);
		tl.setAutoReverse(false);
		tl.play();
	}

}
