package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import service.ChangeService;

public class Main extends Application {
	public static Window mainStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
    	    ChangeService.stage = primaryStage;
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ui/MainUI.fxml"));
            Parent root =  loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/Main.css");
            ChangeService.stage.setScene(scene);
            ChangeService.stage.setTitle("xPic");
            ChangeService.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { Application.launch(args);
    }
}
