package mouse;
import controller.MainUIController;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import model.PictureNode;


public class MouseSelected {
	Node node;
	MainUIController mainUIController;
	private Rectangle selectRectangle;
	private boolean isDragged;	
	public MouseSelected(Node node,MainUIController mainUIController) {
		this.node = node;
		this.mainUIController = mainUIController;
		selectRectangle = new Rectangle();
		addListener();
	}
	private boolean isRectOverlap(PictureNode  pictureNode) {
		double imageNodeCenterPointX = pictureNode.getLayoutX() + pictureNode.getWidth()/2.0;//ͼƬ������x����
		double imageNodeCenterPointY = pictureNode.getLayoutY() + pictureNode.getHeight()/2.0;//ͼƬ���ĵ�y����
		double selectRectangleCenterPointX = selectRectangle.getX() + selectRectangle.getWidth()/2.0;//��ѡ���ĵ�x����
		double selectRectangleCenterPointY = selectRectangle.getY() + selectRectangle.getHeight()/2.0;//��ѡ���ĵ�y����
		return Math.abs(imageNodeCenterPointX - selectRectangleCenterPointX) <= (pictureNode.getWidth()/2.0 + selectRectangle.getWidth()/2.0) &&
			   Math.abs(imageNodeCenterPointY - selectRectangleCenterPointY) <= (pictureNode.getHeight()/2.0 + selectRectangle.getHeight()/2.0);
	}//���������ѡ���������ĵ�X����-ͼƬ��X����=�������εĿ�ȵ�һ��ĺͣ�Yͬ��
	private void addListener() {
		//��갴�£���ʼ��ѡ���������Ͻǵ�
		node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			isDragged = false;
			selectRectangle.setX(e.getX());
			selectRectangle.setY(e.getY());
			selectRectangle.setHeight(0);
			selectRectangle.setWidth(0);
		});
		
		node.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
			this.isDragged = true;
		});
		
		//���ſ�������ѡ���������Ͻǵ��Լ��߳�
		node.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent e) -> {
			double nowX = e.getX();
			double nowY = e.getY();
			double baseX = selectRectangle.getX();
			double baseY = selectRectangle.getY();
			
			selectRectangle.setX(Math.min(baseX, nowX));
			selectRectangle.setY(Math.min(baseY, nowY));			
			selectRectangle.setWidth(Math.abs(baseX - nowX));
			selectRectangle.setHeight(Math.abs(baseY - nowY));
					
			//ͼƬ��ѡ�������ж�
			if(this.isDragged) {				
				PictureNode.clearSelected();
				for(Node childrenNode:  mainUIController.getFlowPaneChildren()) {
					if(childrenNode instanceof PictureNode) {
						if(isRectOverlap((PictureNode)childrenNode))
							((PictureNode)childrenNode).setSelected(true);
					}
				}
			}
			
		});
	}

}
