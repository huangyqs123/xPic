package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import action.*;
import javafx.scene.Node;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import model.FileTree;
import model.PictureFile;
import model.PictureNode;
import mouse.MouseSelected;
import service.ChangeService;
public class MainUIController implements Initializable{
	private MainUIController mainUI;
	private ArrayList<PictureNode> pictures;
	private ArrayList<File> files;
	public static String theFilePath;
	
	@FXML
	private TreeView<PictureFile> treeview;
	@FXML
	private FlowPane flowPane;
	@FXML
	private Text text;
	@FXML
	private Text textTwo;
	@FXML
	private ToolBar toolBar;
	@FXML
	private Button openBtn;
	@FXML
	private Button copyBtn;
	@FXML
	private Button pasteBtn;
	@FXML
	private Button deleteBtn;


//    public void setList() {
//    	files=new ArrayList<File>();
//    	for(int i=0;i<pictures.size();i++) {
//    		files.add(pictures.get(i).getImageFile());
//    	}
//    	ChangeService.files=files;
//    	System.out.print(ChangeService.files);
//    }
	public MainUIController() {
		mainUI = this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initData();
	}

	public void initData() {
		pictures = new ArrayList<>();
		treeview = new FileTree(mainUI, treeview).gettreeView();
		// showPicture();
		new MouseSelected(flowPane,mainUI);
		new RightClick(flowPane, mainUI,false);
	}

	public FlowPane getFlowPane() {
		return flowPane;
	}

	public  ObservableList<Node> getFlowPaneChildren() {
		return flowPane.getChildren();
	}
	public Text getText() {
		return text;
	}//图片数量
	public Text getTextTwo() {
		return textTwo;
	}
//文件大小
	public ArrayList<PictureNode> getPictures() {
		return pictures;
	}

	public void addPictures(PictureNode pNode) {
		pictures.add(pNode);
	}

	public void showPicture() {
		System.out.println("233");
		flowPane.getChildren().remove(0, flowPane.getChildren().size());
		for (PictureNode pNode : pictures) {
			flowPane.getChildren().add(pNode);
		}
		files=new ArrayList<File>();
    	for(int i=0;i<pictures.size();i++) {
    		files.add(pictures.get(i).getImageFile());
    	}
    	ChangeService.files=files;
		ChangePic.page = 0;
    	//System.out.print(ChangeService.files);
	}



	public void clearPictures() {
		pictures.clear();
	}

	public void removePictures(PictureNode pNode) {
		for (PictureNode pictureNode : pictures) {
			if (pictureNode.equals(pNode)) {
				pictures.remove(pNode);
				break;
			}
		}
	}

	
	@FXML
	public void openBtnAction(ActionEvent event) {
		ChangeService.place = 1 ;
		 new Openpic();
	}

	
	@FXML
	public void copyBtnAction(ActionEvent event) {
		new Copy();
	}

	
	@FXML
	public void pasteBtnAction(ActionEvent event) {
		new Paste(mainUI);
	}

	
	@FXML
	public void deleteBtnAction(ActionEvent event) {
		new Delete(mainUI);
	}
	

}
