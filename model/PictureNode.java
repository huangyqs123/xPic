package model;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import action.Openpic;
import action.RightClick;
import controller.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import mouse.MouseMove;
import service.ChangeService;


public class PictureNode extends Label{
	private MainUIController mainScene;  //UI控制类
	private PictureFile pictureFile;   //pictureFile类
	private Image image;   //图片类
	private ImageView imageView;  //图片显示类
	private Text pictureName;   //图片名
	private PictureNode pictureNode = this;  //图片节点
	
	public BooleanProperty selected = new SimpleBooleanProperty();  //标识视觉样式元素的布尔属性。
	
	protected static ArrayList<File> selectedPictureFiles = new  ArrayList<>();  //所选择文件列表
	protected static ArrayList<PictureNode> selectedPictures = new ArrayList<>(); //所选择的图片节点列表
	protected static ArrayList<PictureNode> cutedPictures = new ArrayList<>();  //剪切的图片节点列表

	//构造类
	public PictureNode(PictureFile pictureFile,MainUIController mainUIController) throws MalformedURLException {	
		this.pictureFile = pictureFile;
		this.mainScene = mainUIController;
		initData();  //初始化数据
		addPictureNodeListener(); //开启监听器
		new RightClick(this,mainScene,true);  //右击
	}
	private void initData() throws MalformedURLException{
		
		this.setGraphicTextGap(10);   //设置图片文字间距
		this.setPadding(new Insets(1, 1, 1, 1)); //设置内边距
		this.setContentDisplay(ContentDisplay.TOP);  //设置内容展示方式为置顶
		this.setPrefSize(110,110);  //设置按钮的推荐宽高。
		//初始化一个image对象参数为（imageurl，宽度，长度。是否保留图像的宽高比，是否平滑（就是有无图片锯齿））
		this.image = new Image(pictureFile.getImageFile().toURI().toURL().toString(),100,100,true,true);
		this.imageView = new ImageView(image);  //把image对象作为参数生成imageView对象
		this.pictureName = new Text(pictureFile.getImageName());  //设置展示的图片名
		this.setText(pictureName.getText());  //设置上方标题
		this.setGraphic(imageView);//指定一个图片
		pictureNode.setId("pictureNode");  //这是由于继承了Label类
	}
	

	//获取图片常见的get函数
	public Image getImage() {
		try {
			return image = new Image(pictureFile.getImageFile().toURI().toURL().toString());//将文件转化为url并作为参数创建image对象

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return image;
		
	}
	public File getImageFile() {
		return this.pictureFile.getImageFile();
	}  //获取图片文件路径
	public PictureFile getPictureFile() {
		return pictureFile;
	}  //获取图片文件
	public String getURL() {
		try {
			return this.pictureFile.getImageFile().toURI().toURL().toString();   //获取文件得到url
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ImageView getImageView() {
		return this.imageView;
	} //获取图片视图
	
	public static ArrayList<File> getSelectedPictureFiles() {
		return selectedPictureFiles;
	}  //返回选择的文件列表
	public static void SetSelectedPictureFiles(Image image) {
		PictureNode.getSelectedPictureFiles().clear();
		selectedPictureFiles.add(new PictureFile(image.getUrl()).getImageFile());
	} //设置选择的文件列表
	public static ArrayList<PictureNode> getSelectedPictures() {
		return selectedPictures;
	}   //返回选择的图片节点
	public static void setCutedPictures(ArrayList<PictureNode> cutedPictures) {  //set函数
		PictureNode.cutedPictures = cutedPictures;
	}
	public static void addCutedPictures(PictureNode pNode){
		PictureNode.cutedPictures.add(pNode);
	}//增加剪切图片的链表
	public static ArrayList<PictureNode> getCutedPictures() {
		return cutedPictures;
	} //get函数
	public static void clearCutedPictures() {
		cutedPictures.removeAll(cutedPictures);
	} //清空前企鹅图片列表removeAll清空list中的所有元素
	
	
	public void setSelected(boolean value) {
		boolean istrue = selected.get();  //获取当前的boolean参数的值
		selected.set(value);  //修改
		if (selected.get() && !istrue) //检查是否选中
			selectedPictures.add(this); //如果选中就把加入选中链表中
		else if (istrue && !selected.get())
			selectedPictures.remove(this);
		System.out.println(selectedPictures.size()); //输出选中图片列表的长度
		mainScene.getTextTwo().setText("已选中 "+selectedPictures.size()+" 张图片");  //设置相应的text变化
	}
	
	public static void clearSelected() {
		for (PictureNode pNode : selectedPictures) {
			pNode.selected.set(false);         //将所有的图片节点的Boolean枚举置为false表示未选中
		}
		selectedPictures.removeAll(selectedPictures);  //将列表中的数据清空
	}

	//增加选中监听事件
	private void addPictureNodeListener() {
		selected.addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				if(selected.get()==true) {   //如果是选中状态就变成深灰色
					pictureNode.setStyle("-fx-background-color:#a7a7a7;");
//					mainScene.getText().setText("");
					mainScene.getTextTwo().setText("已选中 0  张图片");
				}else {//否则设置为全透明
					pictureNode.setStyle("-fx-background-color:transparent;");
//					System.out.println(selectedPictures.size()+"--");
					mainScene.getTextTwo().setText("已选中 0  张图片");
				}
			}
		});
		//鼠标移入事件
		this.setOnMouseEntered((MouseEvent e) -> {
			if (!selected.get())//如果图片没有被选中则将背景设置为从#3e4147到#a7a7a7的渐变
				this.setStyle("-fx-background-color:linear-gradient(to bottom,#3e4147 1%,  #a7a7a7 98%);");
//			    mainScene.getText().setText("");
			    
		});
		//鼠标移出事件
		this.setOnMouseExited((MouseEvent e) -> {
			if (!selected.get())
				//鼠标移出时，如果图片没有被选中就设置背景色为透明
				this.setStyle("-fx-background-color:transparent;");
			    
		});
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseMove(this,pictureFile));//设置鼠标点击事件
	}
	//打开操作
	public void openAction() {
//		mainScene.setList();
		new Openpic();
		ChangeService.place = 1 ;
	}

	
}