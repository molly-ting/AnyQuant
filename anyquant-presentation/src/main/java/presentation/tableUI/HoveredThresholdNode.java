package presentation.tableUI;

import java.text.DecimalFormat;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class HoveredThresholdNode extends StackPane {
	public HoveredThresholdNode(double priorValue, double value) {
		setPrefSize(15, 15);
		// this.setMinSize(15, 15);
		DecimalFormat df = new DecimalFormat("0.00");
		String a = df.format(priorValue);
		String b = df.format(value);
		priorValue = Double.parseDouble(a);
		value = Double.parseDouble(b);

		final Label label = createDataThresholdLabel(priorValue, value);

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				getChildren().setAll(label);
				setCursor(Cursor.NONE);
				toFront();
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				getChildren().clear();
				setCursor(Cursor.CROSSHAIR);
			}
		});
	}

	private Label createDataThresholdLabel(double priorValue, double value) {
		final Label label = new Label(value + "");
		label.getStyleClass().addAll("default-color0", "chart-line-symbol-node", "chart-series-line");
		// label.setStyle("lineNode.css");
		label.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

		if (priorValue == 0) {
			label.setTextFill(Color.DARKGRAY);
		} else if (value < priorValue) {
			label.setTextFill(Color.FORESTGREEN);
		} else {
			label.setTextFill(Color.FIREBRICK);
		}

		label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		return label;
	}
}