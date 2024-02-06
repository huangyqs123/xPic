package mouse;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.PictureFile;
import model.PictureNode;
import service.ChangeService;


public class MouseMove implements EventHandler<MouseEvent>{

	Node node;
	PictureFile pictureFile;
	public MouseMove(Node node,PictureFile pictureFile) {
		this.node = node;
		this.pictureFile = pictureFile;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if(node instanceof PictureNode) {
			if(event.isControlDown() == false) {//Controlû�а���
				if(event.getButton()!=MouseButton.SECONDARY || !((PictureNode)node).selected.getValue())//û��ѡ��ͼƬ���һ����
					PictureNode.clearSelected();
				((PictureNode)node).setSelected(true);
				if(event.getClickCount()>=2 && event.getButton() == MouseButton.PRIMARY){
					//˫�����¼�
					((PictureNode)node).setSelected(true);
					ChangeService.place = 1 ;
					((PictureNode)node).openAction();
				}				
			}
			if(event.isControlDown() && event.getButton() == MouseButton.PRIMARY) {//Control�������������
				((PictureNode) node).setSelected( !((PictureNode)node).selected.get() );//���в�����ԭ������
			}
		}
	}
}