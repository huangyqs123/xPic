package action;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import service.ChangeService;

public class MainAction {
    public MainAction() {
//        if (ChangeService.files == null) {
//            Label lab = new Label("没有选中图片!");
//            lab.setFont(new Font("黑体",20));
//            lab.setTranslateX(85);
//            lab.setTranslateY(50);
//            Pane pan = new Pane(lab);
//            Scene scene = new Scene(pan,300,150);
//            Stage Stage =  new Stage();
//            Stage.setTitle("提示");
//            Stage.setScene(scene);
//            Stage.show();
//            return;
//
//
//        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/ui/MainUI.fxml"));
                Parent root = (Parent) loader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("css/Main.css");
                ChangeService.stage.setScene(scene);
                ChangeService.stage.setTitle("xPic");
                ChangeService.stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//    }
}

