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
		Clipboard clipboard = Clipboard.getSystemClipboard();//��ȡ����������
		System.out.println("clipboard.getContent(DataFormat.FILES) = " + clipboard.getContent(DataFormat.FILES));
		List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));//�浽list��
		System.out.println("List = " + files);

//		if (PictureNode.getCutedPictures().size() > 0) {
//			File first = files.get(0);//���ص�0��λ�õ�Ԫ��
//			if(first.getParentFile().getAbsolutePath().compareTo(MainUIController.theFilePath) == 0){//���·�����
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
			String newName = Pasterename(MainUIController.theFilePath,oldFile.getName());//�������ļ���
			File newFile = new File(MainUIController.theFilePath+File.separator+newName);//File.separater��֤����ϵͳ����
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
		for (File fileInFatherPath : filesInFatherPath) {//����Ŀ¼�µ������ļ�
			String fileName = fileInFatherPath.getName();
			int cmp = newName.compareTo(fileName);//�������ֺ͸�����������ֱȽ�
			if (cmp == 0) {//���������ͬ��
				String str = null;
				int end = newName.lastIndexOf("."), start = newName.lastIndexOf("_����");
				if (start != -1) {//����Ѿ�����һ��������������ʱindexof��ֵΪ-1��
					str = newName.substring(start, end);//��ʱ���ļ���Ϊnewname_����
					int num = 1;
					try {
						num = Integer.parseInt(str.substring(str.lastIndexOf("_����") + 3)) + 1;//��ȡ������������� ������ڸ���3����num=3+1
						int cnt = 0, d = num - 1;//Ϊ�˷�ֹ2λ���ĸ�������bug�����縱��10�򸱱�100
						while (d != 0) {
							d /= 10;
							cnt++;
						}
						newName = newName.substring(0, end - cnt) + num + newName.substring(end);//��ȡ2λ�����ϵĸ��������ֵĳ���λ���������+num
					} catch (Exception e) {
						newName = newName.substring(0, end) + "_����1" + newName.substring(end);
					}

				} else {
					newName = newName.substring(0, end) + "_����1" + newName.substring(end);
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
