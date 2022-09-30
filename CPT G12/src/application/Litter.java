package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// extending from parent class imageview
public class Litter extends ImageView {
	private Image img;
	private double width, height;
	
	public Litter() {
		// calling superclass
		super();
		
		// setting image
		img = new Image("file:litter.png");
		super.setImage(img);
		
		// setting width and height
		width = img.getWidth();
		height = img.getHeight();
	}
	
	// returning width and height
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	
	// setting location on screen
	public void setLocation(double x, double y) {
		super.setX(x);
		super.setY(y);
	}
	
	// moving down the screen
	public void move() {
		super.setY(super.getY() + 5);
	}
}
