package action;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class Revolve {

    static double Height = 0;
    static double Width = 0;

    public static void Left(ImageView imageView){
        Height = imageView.getFitHeight();
        Width = imageView.getFitWidth();
        //Height = imageView.getImage().getHeight();
        //Width = imageView.getImage().getWidth();
        System.out.println("Before Revolve : Height = " + Height + ", Width = " + Width);

        //720 480
        //480 320
        //720/480 = 480 / x
        //如果是横向转为竖向
        if( (imageView.getRotate() % 180 ) == 0){
            //imageView.setFitWidth(Height);
           // imageView.setFitHeight(Height*Height/Width);
            // imageView.setFitWidth(9/4*Height);

        }
        //如果是竖向转为向
        else{
           // imageView.setFitHeight(Width);
           // imageView.setFitWidth();
           // imageView.setFitWidth();
        }
        imageView.setRotate((imageView.getRotate()-90)%360);
    }

    public static void Right(ImageView imageView){
        imageView.setRotate((imageView.getRotate()+90)%360);
    }
}
