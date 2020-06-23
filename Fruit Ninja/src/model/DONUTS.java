package model;

import javafx.scene.image.ImageView;

public class DONUTS extends FRUITS{
ImageView donut = new ImageView("view/resources/donuts.png");
	
	public DONUTS() {
		super();
		donut.setFitHeight(100);
		donut.setFitWidth(100);
		setWholeFruit(donut);
		}
}
