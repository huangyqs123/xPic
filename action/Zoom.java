package action;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Zoom {
    private static int multiple = 1;

    public static void enlarge(ImageView imageView) {
        multiple +=1;
        Image image = imageView.getImage();
        imageView.setFitWidth(720*(multiple*0.1+1));
        imageView.setFitHeight(480*(multiple*0.1+1));
        imageView.setPreserveRatio(true);
    }
    public static void small(ImageView imageView) {
        multiple -=1;
        Image image = imageView.getImage();
        imageView.setFitWidth(720*(multiple*0.1+1));
        imageView.setFitHeight(480*(multiple*0.1+1));
        imageView.setPreserveRatio(true);
    }

}
