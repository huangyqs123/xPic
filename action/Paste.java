package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import controller.MainUIController;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import model.PictureFile;
import model.PictureNode;

public class Paste {
	MainUIController mainUIController;
	public Paste(MainUIController mainUI) {
		this.mainUIController = mainUI;
		Clipboard clipboard = Clipboard.getSystemClipboard();//获取剪贴板内容
		System.out.println("clipboard.getContent(DataFormat.FILES) = " + clipboard.getContent(DataFormat.FILES));
		List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));//存到list里
		System.out.println("List = " + files);

//		if (PictureNode.getCutedPictures().size() > 0) {
//			File first = files.get(0);//返回第0号位置的元素
//			if(first.getParentFile().getAbsolutePath().compareTo(MainUIController.theFilePath) == 0){//如果路径相等
//				System.out.println("Before : SelectedPic = " + PictureNode.getSelectedPictures());
//				PictureNode.clearSelected();
//				System.out.println("After : SelectedPic = " + PictureNode.getSelectedPictures());
//				PictureNode.getCutedPictures().clear();
//				PictureNode.getSelectedPictureFiles().clear();
//				clipboard.clear();
//				return;
//			}
//		}
		for(File oldFile : files) {
			String newName = Pasterename(MainUIController.theFilePath,oldFile.getName());//生成新文件名
			File newFile = new File(MainUIController.theFilePath+File.separator+newName);//File.separater保证所有系统兼容
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(newFile.exists()) {
				try {
					copyFile(oldFile,newFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				System.out.print(mainUI.getPictures());
				mainUI.getPictures().add(new PictureNode(new PictureFile(newFile), mainUIController));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			mainUIController.showPicture();
		}
		clipboard.clear();
		files.clear();
		System.out.println("AFTER CLEAR clipboard.getContent(DataFormat.FILES) = " + clipboard.getContent(DataFormat.FILES));
	}

	private String Pasterename(String theFilePath, String name) {
		String newName = name;
		File fatherPathFile = new File(theFilePath);
		File[] filesInFatherPath = fatherPathFile.listFiles();
		for (File fileInFatherPath : filesInFatherPath) {//遍历目录下的所有文件
			String fileName = fileInFatherPath.getName();
			int cmp = newName.compareTo(fileName);//把新名字和父类的所有名字比较
			if (cmp == 0) {//如果存在相同的
				String str = null;
				int end = newName.lastIndexOf("."), start = newName.lastIndexOf("_副本");
				if (start != -1) {//如果已经存在一个副本（不存在时indexof的值为-1）
					str = newName.substring(start, end);//此时新文件名为newname_副本
					int num = 1;
					try {
						num = Integer.parseInt(str.substring(str.lastIndexOf("_副本") + 3)) + 1;//获取副本后面的数字 比如存在副本3，则num=3+1
						int cnt = 0, d = num - 1;//为了防止2位数的副本数出bug，比如副本10或副本100
						while (d != 0) {
							d /= 10;
							cnt++;
						}
						newName = newName.substring(0, end - cnt) + num + newName.substring(end);//获取2位数以上的副本数名字的除个位数外的数字+num
					} catch (Exception e) {
						newName = newName.substring(0, end) + "_副本1" + newName.substring(end);
					}

				} else {
					newName = newName.substring(0, end) + "_副本1" + newName.substring(end);
				}
			}
		}
		return newName;
	}
	
	
	private void copyFile(File fromFile, File toFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(fromFile);
		FileOutputStream outputStream = new FileOutputStream(toFile);
		byte[] a = new byte[1024];
		int Read;
		while ((Read = inputStream.read(a)) > 0) {//write(byte[] b, int off, int len) 
			outputStream.write(a, 0, Read);
		}
		inputStream.close();
		outputStream.close();
		
	}
	
}
