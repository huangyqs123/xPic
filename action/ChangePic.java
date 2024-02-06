package action;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import controller.MainUIController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.PictureFile;
import model.PictureNode;
import service.ChangeService;

public class ChangePic {

	public static int page = 0;
	private static Image image;
	private boolean Previous_or_next = true;


	public static void change(ImageView imageView,Boolean Previous_or_next) {
		//Use page to show the number of choosing Pic in Array"ChangeService.files"
		for(int i = 0 ; i < ChangeService.files.size(); i++){
			if( imageView.getImage().getUrl().equals(ChangeService.files.get(i).toURI().toString())){
				page = i;
				break;
			}
		}
		
	    if(Previous_or_next) {
	    	page++;
	    }
	    if(!Previous_or_next) {
	    	page--;
	    }
		if (page < 0) {
			 Label label = new Label("这是第一张图片!");
	            label.setFont(new Font("黑体",20));
	            label.setTranslateX(85);
	            label.setTranslateY(50);
				Pane pan = new Pane(label);
				Scene scene = new Scene(pan,300,150);
				Stage Stage = new Stage();
				Stage.setTitle("提示");
				Stage.setScene(scene);
				Stage.show();
			page++;
			return;
		}
		if (page > ChangeService.files.size() - 1) {
			 Label label = new Label("这是最后一张图片!");
	            label.setFont(new Font("黑体",20));
	            label.setTranslateX(85);
	            label.setTranslateY(50);
				Pane pan = new Pane(label);
				Scene scene = new Scene(pan,300,150);
				Stage Stage = new Stage();
				Stage.setTitle("提示");
				Stage.setScene(scene);
				Stage.show();
			page--;
			return;
		}
		File file = ChangeService.files.get(page);
		try {
			System.out.println("aaa ChangePic.page = " + page);
	        System.out.println( "aaa Selected Pic = " + PictureNode.getSelectedPictures());
			image = new Image(file.toURI().toURL().toString());
			PictureNode.SetSelectedPictureFiles(image);
			System.out.println(image.getUrl().toString());
			imageView.setImage(image);
			System.out.println("ChangePic.page = " + page);
	        System.out.println( "Selected Pic = " + PictureNode.getSelectedPictures());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void InitPage(ImageView imageView) {
		//Use page to show the number of choosing Pic in Array"ChangeService.files"
		for(int i = 0 ; i < ChangeService.files.size(); i++){
			if( imageView.getImage().getUrl().equals(ChangeService.files.get(i).toURI().toString())){
				page = i;
				break;
			}
		}
	}
}
