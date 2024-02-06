package action;
import main.Main;
import controller.MainUIController;
import model.Attention;
import model.Attention;
import model.PictureNode;

public class Delete {
	MainUIController mainUIController;
	public Delete(MainUIController mainUI) {
		mainUIController = mainUI;	
		if(Attention.showAttention("是否删除选中的图片？", "")) {
			for(PictureNode p : PictureNode.getSelectedPictures()) {
				mainUIController.getFlowPane().getChildren().remove(p);
				mainUI.removePictures(p);
				p.getImageFile().delete();
				
			}			
		}else {
			PictureNode.getSelectedPictureFiles().clear();
		}
		PictureNode.clearSelected();
	}
}
