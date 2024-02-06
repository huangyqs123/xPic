package model;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
public class Attention {
	 public static boolean showAttention(String words,String message){
		 ButtonType no =new ButtonType("ȡ��", ButtonBar.ButtonData.NO);
		 ButtonType yes =new ButtonType("ȷ��", ButtonBar.ButtonData.YES);
		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION,message,no,yes);
         alert.setTitle("ע��");
         alert.setHeaderText(words);
       Optional<ButtonType> buttonType = alert.showAndWait();//showAndWait() ���ڶԻ�����ʧ��ǰ����ִ��֮��Ĵ���
       if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){//���ݵ���������
           return true;
       }
       else {
           return false;
       }
   }
}
