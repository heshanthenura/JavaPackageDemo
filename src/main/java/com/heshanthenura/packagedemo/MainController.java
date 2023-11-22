package com.heshanthenura.packagedemo;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainController {
    int number =0;

    @FXML
    private Text count;

    @FXML
    void addCount(MouseEvent event) {
    number+=1;
    count.setText(String.valueOf(number));
    }

}