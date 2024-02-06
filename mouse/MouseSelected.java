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
		double imageNodeCenterPointX = pictureNode.getLayoutX() + pictureNode.getWidth()/2.0;//图片的中心x坐标
		double imageNodeCenterPointY = pictureNode.getLayoutY() + pictureNode.getHeight()/2.0;//图片中心的y坐标
		double selectRectangleCenterPointX = selectRectangle.getX() + selectRectangle.getWidth()/2.0;//框选中心的x坐标
		double selectRectangleCenterPointY = selectRectangle.getY() + selectRectangle.getHeight()/2.0;//框选中心的y坐标
		return Math.abs(imageNodeCenterPointX - selectRectangleCenterPointX) <= (pictureNode.getWidth()/2.0 + selectRectangle.getWidth()/2.0) &&
			   Math.abs(imageNodeCenterPointY - selectRectangleCenterPointY) <= (pictureNode.getHeight()/2.0 + selectRectangle.getHeight()/2.0);
	}//极端情况：选中区域中心的X坐标-图片的X坐标=两个矩形的宽度的一半的和，Y同理
	private void addListener() {
		//鼠标按下，初始化选择矩阵的左上角点
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
		
		//鼠标放开，更新选择矩阵的左上角点以及边长
		node.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent e) -> {
			double nowX = e.getX();
			double nowY = e.getY();
			double baseX = selectRectangle.getX();
			double baseY = selectRectangle.getY();
			
			selectRectangle.setX(Math.min(baseX, nowX));
			selectRectangle.setY(Math.min(baseY, nowY));			
			selectRectangle.setWidth(Math.abs(baseX - nowX));
			selectRectangle.setHeight(Math.abs(baseY - nowY));
					
			//图片和选择矩阵的判断
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
