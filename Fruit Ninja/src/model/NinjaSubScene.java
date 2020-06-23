package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class NinjaSubScene extends SubScene{
	
	private final String FONT_PATH = "src/model/resources/belligerent.ttf";
	private final String BACKGROUND_IMAGE = "model/resources/panel_beige.png";

	public NinjaSubScene() {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,600,400,false,true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));
		setLayoutX(1024);
	    setLayoutY(180);
		
		
	}
	
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		//transition.setToY(500);
		transition.setToX(-930);
		transition.play();
		
	}
	

}
