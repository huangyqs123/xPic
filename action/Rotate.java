package action;

import javafx.scene.image.ImageView;

public class Rotate {

	private ImageView selectedImage;
	public Rotate(ImageView imageView) {
		selectedImage = imageView;
		selectedImage.setRotate((selectedImage.getRotate() + 90) % 360);
	}
}