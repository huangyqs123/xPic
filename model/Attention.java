package model;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
public class Attention {
	 public static boolean showAttention(String words,String message){
		 ButtonType no =new ButtonType("取消", ButtonBar.ButtonData.NO);
		 ButtonType yes =new ButtonType("确定", ButtonBar.ButtonData.YES);
		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION,message,no,yes);
         alert.setTitle("注意");
         alert.setHeaderText(words);
       Optional<ButtonType> buttonType = alert.showAndWait();//showAndWait() 将在对话框消失以前不会执行之后的代码
       if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){//根据点击结果返回
           return true;
       }
       else {
           return false;
       }
   }
}
