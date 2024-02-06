package action;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import model.PictureNode;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.List;


public class Copy {
	public Copy() {
		Clipboard clipboard = Clipboard.getSystemClipboard();

		List<File> files = clipboard.getFiles();
	//	System.out.println("files = " + files);

		ClipboardContent clipboardContent = new ClipboardContent();

		PictureNode.getSelectedPictureFiles().clear();
		for(PictureNode p : PictureNode.getSelectedPictures()) {
			PictureNode.getSelectedPictureFiles().add(p.getImageFile());
		}
		clipboardContent.putFiles(PictureNode.getSelectedPictureFiles());
		clipboard.clear();
		files = clipboard.getFiles();
		//System.out.println("files = " + files);

		clipboard.setContent(clipboardContent);
		files = clipboard.getFiles();
		//System.out.println("files = " + files);

		clipboard = null;
		clipboardContent = null;//清空上次选择的内容
	}
}
