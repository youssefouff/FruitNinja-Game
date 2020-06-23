package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Clock extends AnchorPane {
	
	private Timeline animation;
	private int temp = 60;
	private String S = "";
	
	Text time = new Text("60");
	
	public Clock() {
		time.setLayoutX(840);
		time.setLayoutY(50);
		try {
			time.setFont(Font.loadFont(new FileInputStream("src/model/resources/belligerent.ttf"),25));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			time.setFont(Font.font("Arial",15));
		}
		time.setFill(Color.YELLOW); 
		time.setStroke(Color.YELLOW);
		getChildren().add(time);
		animation = new Timeline(new KeyFrame(Duration.seconds(1), e->timelabel()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}
	
	private void timelabel() {
		if (temp > 0) {
			temp--;
		}
		S = temp +"";
		time.setText(S);
	}
	
	
	

}
