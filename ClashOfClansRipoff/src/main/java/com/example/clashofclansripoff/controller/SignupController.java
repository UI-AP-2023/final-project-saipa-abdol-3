package com.example.clashofclansripoff.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class SignupController {
    @FXML
    private Text age;

    @FXML
    private Slider age_slider;

    @FXML
    private Button back_btn1;

    @FXML
    private TextField email_field;

    @FXML
    private TextField pass_field;
    @FXML
    private TextField username_field;

    @FXML
    void age_changed(MouseEvent event) {
        age.setText(String.valueOf((int)age_slider.getValue()));
    }

    @FXML
    void exit(MouseEvent event) {

    }

    @FXML
    void submit(MouseEvent event) {

    }
}
