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
			if(event.isControlDown() == false) {//Control没有按下
				if(event.getButton()!=MouseButton.SECONDARY || !((PictureNode)node).selected.getValue())//没有选中图片且右击鼠标
					PictureNode.clearSelected();
				((PictureNode)node).setSelected(true);
				if(event.getClickCount()>=2 && event.getButton() == MouseButton.PRIMARY){
					//双击打开事件
					((PictureNode)node).setSelected(true);
					ChangeService.place = 1 ;
					((PictureNode)node).openAction();
				}				
			}
			if(event.isControlDown() && event.getButton() == MouseButton.PRIMARY) {//Control按下且左键按下
				((PictureNode) node).setSelected( !((PictureNode)node).selected.get() );//所有操作与原来反向
			}
		}
	}
}