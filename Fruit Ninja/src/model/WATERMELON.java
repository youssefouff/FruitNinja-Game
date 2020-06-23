package model;

import javafx.scene.image.ImageView;

public class WATERMELON extends FRUITS {
	ImageView whole = new ImageView("view/resources/fruits/watermelon-whole-.png");
	ImageView slice = new ImageView("view/resources/fruits/cut_watermelon_2.png");
	public WATERMELON() {
		super();
		whole.setFitHeight(100);
		whole.setFitWidth(100);
		slice.setFitHeight(100);
		slice.setFitWidth(100);
		setWholeFruit(whole);
		setSlicedFruit(slice);
		
	}

}
