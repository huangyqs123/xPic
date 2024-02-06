package action;

import java.io.File;
import java.util.List;
import action.Copy;
import action.Delete;
import action.Openpic;
import action.Paste;
import action.Rename;
import controller.MainUIController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import model.PictureNode;
import mouse.MouseMove;

public class RightClick {
	MainUIController mainUI;
	public RightClick(Node node, MainUIController mainUI, boolean choice) {
		this.mainUI = mainUI;
		if (choice == true) {
			PictureMenu(node);

		}

		nullMenu(node);
	}

	public void PictureMenu(Node node) {//�һ�ͼƬ��menu
		ContextMenu PicRightMenu = new ContextMenu();
		MenuItem open = new MenuItem("��");
		MenuItem copy = new MenuItem("����");
		MenuItem rename = new MenuItem("������");
		MenuItem delete = new MenuItem("ɾ��");
		PicRightMenu.getItems().addAll(open, copy, delete, rename);
		open.setOnAction(e -> {new Openpic();});
		copy.setOnAction(e -> {new Copy();});
		rename.setOnAction(e -> {new Rename(mainUI);});
		delete.setOnAction(e -> {new Delete(mainUI);});
		node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			if (e.getButton() == MouseButton.SECONDARY)
				PicRightMenu.show(node, e.getScreenX(), e.getScreenY());
			else {
				if (PicRightMenu.isShowing())
					PicRightMenu.hide();
			}
		});
	}
	public void nullMenu(Node node) {//�һ��հ״���menu
		ContextMenu NullRightMenu = new ContextMenu();
		MenuItem paste = new MenuItem("ճ��");
		NullRightMenu.getItems().add(paste);
		paste.setOnAction(e -> {new Paste(mainUI);});
		node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			Node clickNode = e.getPickResult().getIntersectedNode();
			// System.out.println(clickNode.toString());
			if (clickNode instanceof FlowPane && !(clickNode instanceof PictureNode) && !(clickNode instanceof Text)) {// �������ͼƬ�ڵ�
				PictureNode.clearSelected();// �����ѡ

				if (e.getButton() == MouseButton.SECONDARY) {// ����Ҽ�ִ��ճ������
					Clipboard clipboard = Clipboard.getSystemClipboard();
					List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));
					if (files.size() <= 0) {
						paste.setDisable(true);
					} else {
						paste.setDisable(false);
					}
					NullRightMenu.show(node, e.getScreenX(), e.getScreenY());
				} else {// �Ҽ����������hide
					if (NullRightMenu.isShowing()) {
						NullRightMenu.hide();
					}
				}
			} // �������ͼƬ�ڵ�
			else {
				if (NullRightMenu.isShowing()) {
					NullRightMenu.hide();
				}
			} // �����ͼƬ�ڵ㣬���һ��˵���Ȼ��ʾ����hide
		});
	}

}
