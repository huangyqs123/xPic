package controller;

import action.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Main;
import model.PictureNode;
import service.ChangeService;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewUIController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button previous;

    @FXML
    private Button next;

    @FXML
    private Button zoomBig;

    @FXML
    private Button zoomSmall;

    @FXML
    private Button PPT;

    @FXML
    private Button leftRevolve;

    @FXML
    private Button rightRevolve;

    @FXML
    private ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    	System.out.println("In view,ChangeService.place = " + ChangeService.place);
    	if(ChangeService.place == 1) {
      	System.out.println("From 1111");
            image.setImage( new Image(PictureNode.getSelectedPictures().get(0).getURL().toString()));
    	}else {
			System.out.println("From 3333");
            image.setImage( new Image(ChangeService.files.get(ChangePic.page).toURI().toString()) );
    	}
    	
    	
//    	System.out.println("before ChangeService.count = " + ChangeService.count);
//        //if(ChangeService.count == 0)
        ChangeService.file = PictureNode.getSelectedPictures().get(0).getImageFile();
//        System.out.println("files="+ChangeService.file);
////        //ChangeService.count++;
////    	System.out.println("after ChangeService.count = " + ChangeService.count);
//////        System.out.println("11111");
//////        System.out.println("ChangeService.files = " + ChangeService.files);
//////        System.out.println("ChangePic.page = " + ChangePic.page);
////    	
////        image.setImage( new Image(ChangeService.files.get(ChangePic.page).toURI().toString()) );
//        System.out.println( "Selected Pic = " + PictureNode.getSelectedPictures());
        ChangeService.change = new ImageView(new Image(PictureNode.getSelectedPictures().get(0).getURL()));

        ChangeService.originHight = image.getFitHeight();
        ChangeService.originWidth = image.getFitWidth();
    }


    public void ZoomEnlarge(ActionEvent event) {
        Zoom.enlarge(image);
    }

    public void ZoomSmall(ActionEvent event){
        Zoom.small(image);
    }

    public void LeftRevolve(ActionEvent event){
        Revolve.Left(image);
    }

    public void RightRevolve(ActionEvent event){
        Revolve.Right(image);
    }

   // public void Back(ActionEvent event) {}

    public void PPT(ActionEvent event) { 
    	ChangePic.InitPage(image);
    	new PPTAction();
    }
    
    public void ChangePic_Previous(ActionEvent event) { ChangePic.change(image,false); }
    
    public void ChangePic_Next(ActionEvent event) { ChangePic.change(image,true); }

    public void MainUI(ActionEvent event) { new MainAction(); }
}
