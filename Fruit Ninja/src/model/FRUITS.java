package model;

import javafx.scene.image.ImageView;

public abstract class FRUITS {
 private ImageView wholeFruit;
 private ImageView slicedFruit;
 private boolean sliced;
 private int dimX,dimY;

 public FRUITS() {
	 sliced = false;
 }
public ImageView getWholeFruit() {
	return wholeFruit;
}
public void setWholeFruit(ImageView wholeFruit) {
	this.wholeFruit = wholeFruit;
}
public ImageView getSlicedFruit() {
	return slicedFruit;
}
public void setSlicedFruit(ImageView slicedFruit) {
	this.slicedFruit = slicedFruit;
}
public boolean isSliced() {
	return sliced;
}
public void setSliced(boolean sliced) {
	this.sliced = sliced;
}
public int getDimX() {
	return dimX;
}
public void setDimX(int dimX) {
	this.dimX = dimX;
}
public int getDimY() {
	return dimY;
}
public void setDimY(int dimY) {
	this.dimY = dimY;
}
 
}
