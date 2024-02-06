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
	private MainUIController mainScene;  //UI������
	private PictureFile pictureFile;   //pictureFile��
	private Image image;   //ͼƬ��
	private ImageView imageView;  //ͼƬ��ʾ��
	private Text pictureName;   //ͼƬ��
	private PictureNode pictureNode = this;  //ͼƬ�ڵ�
	
	public BooleanProperty selected = new SimpleBooleanProperty();  //��ʶ�Ӿ���ʽԪ�صĲ������ԡ�
	
	protected static ArrayList<File> selectedPictureFiles = new  ArrayList<>();  //��ѡ���ļ��б�
	protected static ArrayList<PictureNode> selectedPictures = new ArrayList<>(); //��ѡ���ͼƬ�ڵ��б�
	protected static ArrayList<PictureNode> cutedPictures = new ArrayList<>();  //���е�ͼƬ�ڵ��б�

	//������
	public PictureNode(PictureFile pictureFile,MainUIController mainUIController) throws MalformedURLException {	
		this.pictureFile = pictureFile;
		this.mainScene = mainUIController;
		initData();  //��ʼ������
		addPictureNodeListener(); //����������
		new RightClick(this,mainScene,true);  //�һ�
	}
	private void initData() throws MalformedURLException{
		
		this.setGraphicTextGap(10);   //����ͼƬ���ּ��
		this.setPadding(new Insets(1, 1, 1, 1)); //�����ڱ߾�
		this.setContentDisplay(ContentDisplay.TOP);  //��������չʾ��ʽΪ�ö�
		this.setPrefSize(110,110);  //���ð�ť���Ƽ���ߡ�
		//��ʼ��һ��image�������Ϊ��imageurl����ȣ����ȡ��Ƿ���ͼ��Ŀ�߱ȣ��Ƿ�ƽ������������ͼƬ��ݣ���
		this.image = new Image(pictureFile.getImageFile().toURI().toURL().toString(),100,100,true,true);
		this.imageView = new ImageView(image);  //��image������Ϊ��������imageView����
		this.pictureName = new Text(pictureFile.getImageName());  //����չʾ��ͼƬ��
		this.setText(pictureName.getText());  //�����Ϸ�����
		this.setGraphic(imageView);//ָ��һ��ͼƬ
		pictureNode.setId("pictureNode");  //�������ڼ̳���Label��
	}
	

	//��ȡͼƬ������get����
	public Image getImage() {
		try {
			return image = new Image(pictureFile.getImageFile().toURI().toURL().toString());//���ļ�ת��Ϊurl����Ϊ��������image����

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return image;
		
	}
	public File getImageFile() {
		return this.pictureFile.getImageFile();
	}  //��ȡͼƬ�ļ�·��
	public PictureFile getPictureFile() {
		return pictureFile;
	}  //��ȡͼƬ�ļ�
	public String getURL() {
		try {
			return this.pictureFile.getImageFile().toURI().toURL().toString();   //��ȡ�ļ��õ�url
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ImageView getImageView() {
		return this.imageView;
	} //��ȡͼƬ��ͼ
	
	public static ArrayList<File> getSelectedPictureFiles() {
		return selectedPictureFiles;
	}  //����ѡ����ļ��б�
	public static void SetSelectedPictureFiles(Image image) {
		PictureNode.getSelectedPictureFiles().clear();
		selectedPictureFiles.add(new PictureFile(image.getUrl()).getImageFile());
	} //����ѡ����ļ��б�
	public static ArrayList<PictureNode> getSelectedPictures() {
		return selectedPictures;
	}   //����ѡ���ͼƬ�ڵ�
	public static void setCutedPictures(ArrayList<PictureNode> cutedPictures) {  //set����
		PictureNode.cutedPictures = cutedPictures;
	}
	public static void addCutedPictures(PictureNode pNode){
		PictureNode.cutedPictures.add(pNode);
	}//���Ӽ���ͼƬ������
	public static ArrayList<PictureNode> getCutedPictures() {
		return cutedPictures;
	} //get����
	public static void clearCutedPictures() {
		cutedPictures.removeAll(cutedPictures);
	} //���ǰ���ͼƬ�б�removeAll���list�е�����Ԫ��
	
	
	public void setSelected(boolean value) {
		boolean istrue = selected.get();  //��ȡ��ǰ��boolean������ֵ
		selected.set(value);  //�޸�
		if (selected.get() && !istrue) //����Ƿ�ѡ��
			selectedPictures.add(this); //���ѡ�оͰѼ���ѡ��������
		else if (istrue && !selected.get())
			selectedPictures.remove(this);
		System.out.println(selectedPictures.size()); //���ѡ��ͼƬ�б�ĳ���
		mainScene.getTextTwo().setText("��ѡ�� "+selectedPictures.size()+" ��ͼƬ");  //������Ӧ��text�仯
	}
	
	public static void clearSelected() {
		for (PictureNode pNode : selectedPictures) {
			pNode.selected.set(false);         //�����е�ͼƬ�ڵ��Booleanö����Ϊfalse��ʾδѡ��
		}
		selectedPictures.removeAll(selectedPictures);  //���б��е��������
	}

	//����ѡ�м����¼�
	private void addPictureNodeListener() {
		selected.addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				if(selected.get()==true) {   //�����ѡ��״̬�ͱ�����ɫ
					pictureNode.setStyle("-fx-background-color:#a7a7a7;");
//					mainScene.getText().setText("");
					mainScene.getTextTwo().setText("��ѡ�� 0  ��ͼƬ");
				}else {//��������Ϊȫ͸��
					pictureNode.setStyle("-fx-background-color:transparent;");
//					System.out.println(selectedPictures.size()+"--");
					mainScene.getTextTwo().setText("��ѡ�� 0  ��ͼƬ");
				}
			}
		});
		//��������¼�
		this.setOnMouseEntered((MouseEvent e) -> {
			if (!selected.get())//���ͼƬû�б�ѡ���򽫱�������Ϊ��#3e4147��#a7a7a7�Ľ���
				this.setStyle("-fx-background-color:linear-gradient(to bottom,#3e4147 1%,  #a7a7a7 98%);");
//			    mainScene.getText().setText("");
			    
		});
		//����Ƴ��¼�
		this.setOnMouseExited((MouseEvent e) -> {
			if (!selected.get())
				//����Ƴ�ʱ�����ͼƬû�б�ѡ�о����ñ���ɫΪ͸��
				this.setStyle("-fx-background-color:transparent;");
			    
		});
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseMove(this,pictureFile));//����������¼�
	}
	//�򿪲���
	public void openAction() {
//		mainScene.setList();
		new Openpic();
		ChangeService.place = 1 ;
	}

	
}