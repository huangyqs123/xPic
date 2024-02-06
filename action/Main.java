package action;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import service.ChangeService;
public class Main {
	public Main() {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("/ui/MainUI.fxml"));
	Parent root = null;
	try {
		root = (Parent)loader.load();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Scene scene = new Scene(root);
	ChangeService.stage.setScene(scene);
	ChangeService.stage.setTitle("xPic");
	ChangeService.stage.setResizable(false);
	ChangeService.stage.show();
}
}
