package action;
import javafx.scene.text.Font;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.ChangeService;
public class PPTAction {
	public PPTAction() {
		if (ChangeService.files == null) {
            Label label = new Label("没有选中图片!");
            label.setFont(new Font("黑体",20));
            label.setTranslateX(85);
            label.setTranslateY(50);
			Pane pan = new Pane(label);
			Scene scene = new Scene(pan,300,150);
			Stage Stage = new Stage();
			Stage.setTitle("提示");
			Stage.setScene(scene);
			Stage.show();
			return;}
		else {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/ui/SlideUI.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				ChangeService.stage.setScene(scene);
				ChangeService.stage.setTitle("幻灯片模式");
				ChangeService.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
