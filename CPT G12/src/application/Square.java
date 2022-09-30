package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// extending from parent class imageview
public class Square extends ImageView {
	private Image imgClick, imgIdle;
	private double width, height;
	private int value;
	
	public Square() {
		// calling superclass
		super();
		
		// setting images for clicked and not clicked boards (same dimensions)
		imgClick = new Image("file:clicked.png");
		imgIdle = new Image("file:idle.png");
		super.setImage(imgIdle);
		
		//setting width
		width = imgClick.getWidth();
		height = imgClick.getHeight();
		
		// instantiating the value of the tile
		value = 0;
	}
	
	// returning width and height
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	
	// setting its value to the value of the player who clicked it (user or computer)
	public void clicked(int player) {
		imgClick = new Image("file:clickedP" + player + ".png");
		super.setImage(imgClick);
		value = player;
	}
	
	// returning value
	public int getPlayer() {
		return value;
	}
	
	// clearing the value and resetting image
	public void clear() {
		value = 0;
		super.setImage(imgIdle);
	}
}
