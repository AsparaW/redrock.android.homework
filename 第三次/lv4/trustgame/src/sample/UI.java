package sample;

import game.GameManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UI extends Application {
    private static final String P = "file:./res/Players/unknown.png";
    private static final int START_COIN = 300;
    private static final int MAX_PLAYER = 25;
    // // //

    public static Text scoreText[] = new Text[MAX_PLAYER];
    public static Text showText[] = new Text[Main.players.size()];
    public static ImageView picBox[] = new ImageView[MAX_PLAYER];

    private static Slider show[] = new Slider[Main.players.size()];


    void startUI() {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Group g = new Group();
        final Scene scene = new Scene(root, 800, 630);
        scene.setFill(null);
        primaryStage.setTitle("Hello World");
        //primaryStage.setResizable(false);

        Image testImage = new Image(P);
        double boxX[] = new double[MAX_PLAYER];
        double boxY[] = new double[MAX_PLAYER];
        double lineBoxX[] = new double[MAX_PLAYER];
        double lineBoxY[] = new double[MAX_PLAYER];


        //change players
        TextField setField[] = new TextField[8];
        {
            Text txt = new Text(0, 565, "刷人回合数");
            TextField textField = new TextField();
            setField[5] = textField;
            double nowX = 100;
            double nowY = 550;
            textField.setTranslateX(nowX);
            textField.setTranslateY(nowY);
            textField.setText("10");
            root.getChildren().add(textField);
            root.getChildren().add(txt);
        }
        {
            Text txt = new Text(0, 585, "双方信任得分");
            TextField textField = new TextField();
            setField[0] = textField;
            double nowX = 100;
            double nowY = 570;
            textField.setTranslateX(nowX);
            textField.setTranslateY(nowY);
            textField.setText("2");
            root.getChildren().add(textField);
            root.getChildren().add(txt);
        }
        {
            Text txt = new Text(0, 605, "双方欺骗得分");
            TextField textField = new TextField();
            setField[1] = textField;
            double nowX = 100;
            double nowY = 590;
            textField.setTranslateX(nowX);
            textField.setTranslateY(nowY);
            textField.setText("0");
            root.getChildren().add(textField);
            root.getChildren().add(txt);
        }
        {
            Text txt = new Text(280, 565, "一方欺骗得分");
            TextField textField = new TextField();
            setField[2] = textField;
            double nowX = 400;
            double nowY = 550;
            textField.setTranslateX(nowX);
            textField.setTranslateY(nowY);
            textField.setText("3");
            root.getChildren().add(textField);
            root.getChildren().add(txt);
        }
        {
            Text txt = new Text(280, 585, "一方信任得分");
            TextField textField = new TextField();
            setField[3] = textField;
            double nowX = 400;
            double nowY = 570;
            textField.setTranslateX(nowX);
            textField.setTranslateY(nowY);
            textField.setText("-1");
            root.getChildren().add(textField);
            root.getChildren().add(txt);
        }
        {
            Text txt = new Text(280, 605, "错误概率");
            TextField textField = new TextField();
            setField[6] = textField;
            double nowX = 400;
            double nowY = 590;
            textField.setTranslateX(nowX);
            textField.setTranslateY(nowY);
            textField.setText("0.05");
            root.getChildren().add(textField);
            root.getChildren().add(txt);
        }
        {
            Text txt = new Text(580, 550, "繁殖人数");
            TextField textField = new TextField();
            setField[4] = textField;
            double nowX = 580;
            double nowY = 550;
            textField.setTranslateX(nowX);
            textField.setTranslateY(nowY);
            textField.setText("5");
            root.getChildren().add(textField);
            root.getChildren().add(txt);
        }
        {
            Text txt = new Text(580, 590, "重复次数");
            TextField textField = new TextField();
            setField[7] = textField;
            double nowX = 580;
            double nowY = 590;
            textField.setTranslateX(nowX);
            textField.setTranslateY(nowY);
            textField.setText("5");
            root.getChildren().add(textField);
            root.getChildren().add(txt);
        }
        /* g.getChildren().add(imageView);*/

        //change over


        for (int i = 0; i < MAX_PLAYER; i++) {

            ImageView imageView = new ImageView();
            imageView.setImage(testImage);
            imageView.setScaleX(0.8);
            imageView.setScaleY(0.8);
            imageView.setId("@Image" + Integer.toString(i));
            picBox[i] = imageView;
            double centerX = 300.0;
            double centerY = 300.0;
            double r = 220.0;
            double realR = 200.0;
            double imageX = centerX + Math.sin(Math.PI * 2 / MAX_PLAYER * i) * r - testImage.getWidth() / 2;
            double imageY = centerY + Math.cos(Math.PI * 2 / MAX_PLAYER * i) * r - testImage.getHeight() / 2;
            boxX[i] = imageX;
            boxY[i] = imageY;
            lineBoxX[i] = centerX + Math.sin(Math.PI * 2 / MAX_PLAYER * i) * realR;
            lineBoxY[i] = centerY + Math.cos(Math.PI * 2 / MAX_PLAYER * i) * realR;
            imageView.setTranslateX(imageX);
            imageView.setTranslateY(imageY);
            root.getChildren().add(imageView);

            /* g.getChildren().add(imageView);*/
        }

        int minPlayers = MAX_PLAYER % Main.players.size();
        int minValue = MAX_PLAYER / Main.players.size();
        //int maxValue = MAX_PLAYER % players.size() + minValue;


        /*for (int i = 0; i < Main.players.size(); i++) {
            Slider slider = new Slider();
            double nowX = 600;
            double nowY = 100 + 30 * i;
            show[i] = slider;
            slider.setId("@SliderAmount" + Integer.toString(i));
            slider.setMax(MAX_PLAYER);
            slider.setMin(0);
            slider.setValue(minValue);
            slider.setBlockIncrement(1);
            slider.setMinorTickCount(5);
            slider.setMajorTickUnit(5);
            slider.setTranslateX(nowX);
            slider.setTranslateY(nowY);
            slider.setSnapToTicks(true);
            //slider.setShowTickLabels(true);
            if (i >= Main.players.size()-minPlayers) slider.setValue(minValue+1);
            root.getChildren().add(slider);
            System.out.println(i);
            *//* g.getChildren().add(imageView);*//*

        }


        for (int i = 0; i < Main.players.size(); i++) {

            Text txt = new Text();
            showText[i] = txt;
            double nowX = 750;
            double nowY = 100 + 30 * i + txt.getFont().getSize();
            int temp = (int) show[i].getValue();
            txt.setText(String.valueOf(temp));
            txt.setId("@show" + Integer.toString(i));
            txt.setX(nowX);
            txt.setY(nowY);
            root.getChildren().add(txt);

            *//* g.getChildren().add(imageView);*//*
        }*/
        TextField setNum[] = new TextField[MAX_PLAYER];
        for (int i = 0; i < Main.players.size(); i++) {
            TextField textField = new TextField();
            setNum[i] = textField;
            double nowX = 600;
            double nowY = 100 + 30 * i;
            textField.setTranslateX(nowX);
            textField.setTranslateY(nowY);
            textField.setText(Integer.toString(minValue));
            if (i >= Main.players.size() - minPlayers)
                textField.setText(Integer.toString(minValue + 1));
            root.getChildren().add(textField);
        }


        for (int i = 0; i < Main.players.size(); i++) {
            Text txt = new Text();
            double nowX = 540;
            double nowY = 100 + 30 * i + txt.getFont().getSize();
            txt.setId("@text" + Integer.toString(i));
            txt.setText(Main.players.get(i).showType());
            txt.setX(nowX);
            txt.setY(nowY);
            root.getChildren().add(txt);

            /* g.getChildren().add(imageView);*/
        }


        Button btn = new Button("开始");
        btn.setTranslateX(500);
        btn.setTranslateY(500);
        btn.setOnAction((ActionEvent e) -> {
            System.out.println("clicked");
            int tot = 0;
            for (int i = 0; i < Main.players.size(); i++) {
                Main.gameData.put(Main.players.get(i), Integer.parseInt(setNum[i].getText()));
                tot += Integer.parseInt(setNum[i].getText());
            }
            if (tot <= 25) {
                //
                int nowSet = 0;
                for (int i = 0; i < Main.players.size(); i++) {

                    for (int j = 0; j < Integer.parseInt(setNum[i].getText()); j++) {
                        picBox[nowSet].setVisible(true);
                        scoreText[nowSet].setVisible(true);
                        Image tmpImage = new Image(Main.players.get(i).getPATH());
                        picBox[nowSet].setImage(tmpImage);
                        nowSet++;
                    }

                }
                for (; nowSet < MAX_PLAYER; nowSet++) {
                    picBox[nowSet].setVisible(false);
                    scoreText[nowSet].setVisible(false);
                }
                //
                int arguA = Integer.parseInt(setField[0].getText());
                int arguB = Integer.parseInt(setField[1].getText());
                int arguC = Integer.parseInt(setField[2].getText());
                int arguD = Integer.parseInt(setField[3].getText());
                int arguE = Integer.parseInt(setField[4].getText());
                int arguF = Integer.parseInt(setField[5].getText());
                int arguG = Integer.parseInt(setField[7].getText());
                double arguH = Double.valueOf(setField[6].getText());
                //
                //
                GameManager myGM = new GameManager(arguA, arguB, arguC, arguD, arguE, arguF, arguG, arguH);
                myGM.init(Main.gameData);
                Main.gameData.clear();//no re-do
            } else {
                System.out.println("总玩家超过25，无法运行！");
            }

        });
        root.getChildren().add(btn);

        ////// label

        for (int i = 0; i < MAX_PLAYER; i++) {

            Text txt = new Text();
            scoreText[i] = txt;
            double nowX = boxX[i] + 20;
            double nowY = boxY[i] + 40;
            int temp = START_COIN;
            txt.setText(String.valueOf(temp));
            txt.setId("@score" + Integer.toString(i));
            txt.setX(nowX);
            txt.setY(nowY);
            root.getChildren().add(txt);

            /* g.getChildren().add(imageView);*/
        }

        //////label lover
        ///// line
        for (int i = 0; i < MAX_PLAYER; i++) {
            for (int j = i; j < MAX_PLAYER; j++) {
                Line line = new Line();
                double nowX = lineBoxX[i];
                double nowY = lineBoxY[i];
                double targetX = lineBoxX[j];
                double targetY = lineBoxY[j];
                line.setStartX(nowX);
                line.setStartY(nowY);
                line.setEndX(targetX);
                line.setEndY(targetY);
                line.setStroke(Color.LIGHTGRAY);
                root.getChildren().add(line);
            }
        }

        //// line over
       /* for (int i = 0; i < Main.players.size(); i++) {
            int p = i;
            show[i].valueChangingProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    int nowValue = (int) show[p].getValue();
                    int targetValue = MAX_PLAYER;
                    int tot = 0;
                    boolean flag = false;
                    boolean okFlag = false;
                    for (Slider tempS : show) {
                        tot += (int) tempS.getValue();
                    }
                    int exceed = tot - targetValue;
                    while (!okFlag ) {
                        for (int i = p; i < Main.players.size() && !flag; i++) {
                            int temp = (int) show[i].getValue();

                            if (i != p) {
                                if (exceed > 0) {
                                    if (temp > 0) temp--;
                                    else temp = 0;
                                } else {
                                    if (temp < MAX_PLAYER) temp++;
                                    else temp = MAX_PLAYER;
                                }
                                show[i].setValue(temp);
                                showText[i].setText(String.valueOf(temp));
                                tot = 0;
                                for (Slider tempS : show) {
                                    tot += (int) tempS.getValue();
                                }
                                if (tot == MAX_PLAYER) flag = true;
                                exceed = tot - targetValue;
                            } else {
                                show[i].setValue(temp);
                                showText[i].setText(String.valueOf(temp));
                            }

                        }
                        for (int i = 0; i < p && !flag; i++) {
                            int temp = (int) show[i].getValue();
                            if (exceed > 0) {
                                if (temp > 0) temp--;
                                else temp = 0;
                            } else {
                                if (temp < MAX_PLAYER) temp++;
                                else temp = MAX_PLAYER;
                            }
                            show[i].setValue(temp);
                            showText[i].setText(String.valueOf(temp));
                            tot = 0;
                            for (Slider tempS : show) {
                                tot += (int) tempS.getValue();
                            }
                            if (tot == MAX_PLAYER) flag = true;
                            exceed = tot - targetValue;
                        }
                        tot = 0;
                        for (Slider tempS : show) {
                            tot += (int) tempS.getValue();
                        }
                        if (tot == MAX_PLAYER) okFlag = true;
                    }
                    //change picture
*//*                    for (int i=0;i<players.size();i++){
                        gameData.put(players.get(i),(int)show[i].getValue());
                    }
                    gameData.clear();*//*
                    int nowSet = 0;
                    for (int i=0;i<Main.players.size();i++){
                        Main.gameData.put(Main.players.get(i),(int)show[i].getValue());
                    }
                    for (int i=0;i<Main.players.size();i++){

                        for (int j=0 ;j<(int)show[i].getValue();j++){

                            Image tmpImage = new Image(Main.players.get(i).getPATH());
                            picBox[nowSet].setImage(tmpImage);
                            nowSet ++;
                        }

                    }
                    Main.gameData.clear();
                }
            });
        }
        //*/


        //Circle

        Circle c = new Circle();
        c.setCenterY(300.0);
        c.setCenterX(300.0);
        c.setRadius(200.0);
        c.setFill(Color.TRANSPARENT);
        c.setStroke(Color.BLACK);
        c.setCache(true);
        g.getChildren().add(c);


        ///pic show init
        int nowSet = 0;
        for (int i = 0; i < Main.players.size(); i++) {

            for (int j = 0; j < Integer.parseInt(setNum[i].getText()); j++) {

                Image tmpImage = new Image(Main.players.get(i).getPATH());
                picBox[nowSet].setImage(tmpImage);
                nowSet++;
            }

        }


        ///
        root.getChildren().add(g);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
