package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SmallInfoLabel extends Label {
	
	private final String FONT_PATH_LABEL = "src/model/resources/belligerent.ttf";
	
	public SmallInfoLabel(String text) {
		setPrefWidth(500);
		setPrefHeight(70);
		//BackgroundImage backgroundImage = new BackgroundImage
		//	(new Image("/view/resources/yellow_button04.png",130,50,false,true),
		//		BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
	//	setBackground(new Background(backgroundImage));
		setAlignment(Pos.BOTTOM_CENTER);
		setPadding(new Insets(0,0,0,0));
		//setLayoutY(40);
		//setLayoutX(800);
		setLabelFont();
		setText(text);
		setTextFill(Color.YELLOW);
		
	}
	
	private void setLabelFont() {
		
		try {
			 setFont(Font.loadFont(new FileInputStream(FONT_PATH_LABEL),50));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Arial",15));
		}
	}
	
}
