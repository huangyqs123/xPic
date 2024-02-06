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
		if(Attention.showAttention("�Ƿ�ɾ��ѡ�е�ͼƬ��", "")) {
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
