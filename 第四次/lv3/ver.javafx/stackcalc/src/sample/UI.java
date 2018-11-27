package sample;

import calc.CalcTools;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UI extends Application {
    private static String str = "5.8×2.7+0.58×52+58×0.21   +   1 +607.1－.1";
    private static String result = "";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Group g = new Group();
        final Scene scene = new Scene(root, 450, 60);
        primaryStage.setTitle("Calculator");
        ////////////CONTENT
        TextField textField = new TextField();
        double nowX = 10;
        double nowY = 20;
        textField.setPrefColumnCount(30);
        textField.setTranslateX(nowX);
        textField.setTranslateY(nowY);
        textField.setText(str);
        root.getChildren().add(textField);
        //field over
        Button btn = new Button("=");
        btn.setTranslateX(400);
        btn.setTranslateY(20);
        btn.setOnAction((ActionEvent e) -> {
            str = textField.getText();
            CalcTools myCalc = new CalcTools(str);
            result = myCalc.getResult();
            textField.setText(result);
        });
        root.getChildren().add(btn);
        //btn over

        ////////////
        scene.setFill(null);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    void startUI() {
        launch();
    }

}
