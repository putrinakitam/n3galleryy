 package projekoop.scenes.samsung;

import java.io.File;
import java.util.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import projekoop.datasource.PhoneDatasource;
import projekoop.models.SamsungModel;
import projekoop.scenes.StartScene;

public class SamsungScene {
    private Stage stage;
    private List<SamsungModel> listSamsung;

    public SamsungScene(Stage stage){
        this.stage = stage;
        this.listSamsung = PhoneDatasource.getSamsung();
    }

    public void show(){
        //label samsung
        Label samsungLabel = new Label();
        samsungLabel.setText("SAMSUNG");
        samsungLabel.setFont(new Font("Cooper Black", 20));

        VBox vBox1 = new VBox();

        //
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefHeight(600-75);

        HBox[] hBox = new HBox[listSamsung.size()/2];
        
        
        //samsung23plus
        for (int i = 0; i < hBox.length; i++) {
            hBox[i] = new HBox();
            hBox[i].setAlignment(Pos.CENTER);
            hBox[i].setSpacing(12);
            for (int j = 0; j < 2; j++) {
                SamsungModel ss = listSamsung.get((i*2)+j);
                File file = new File("src/main/resources/image/"+ss.getImage());    
                Image image = new Image(file.toURI().toString());
                System.out.println(image.getUrl());
                ImageView samsung23PlusIV = new ImageView(image);
                samsung23PlusIV.setFitWidth(80);
                samsung23PlusIV.setFitHeight(110);

                //setonclick
                samsung23PlusIV.setOnMouseClicked(V ->{
                    SamsungDetailScene promax = new SamsungDetailScene(stage, ss);
                    promax.show();
                });

                samsung23PlusIV.setOnMouseEntered(V ->{
                    samsung23PlusIV.setStyle("-fx-cursor: hand;");
                });

                Label nama1 = new Label(ss.getName());
                
                nama1.setFont(new Font("Hey Comic", 12));
                // Button backButton = new Button("BACK");
                VBox hp1 = new VBox();
                hp1.setAlignment(Pos.TOP_CENTER);
                hp1.getChildren().addAll(samsung23PlusIV,nama1);
                hBox[i].getChildren().add(hp1);
            }
            vBox.getChildren().addAll(hBox[i]);    
            
        }
        ImageView back1 = new ImageView("/image/back_samsung.png");
        back1.setOnMouseClicked(V ->{
            StartScene startScene = new StartScene(stage);
            startScene.show();
        });
        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);
        
        Label uang = new Label("Rp. "+StartScene.uang);
        uang.setStyle("-fx-text-fill: white");
        uang.setAlignment(Pos.CENTER_RIGHT);
        uang.setFont(new Font("Arial", 20));

        HBox hBox1 = new HBox(back1, space, uang);
        hBox1.setStyle("-fx-background-color:#000000;");
        hBox1.setMinHeight(75);
        hBox1.setPadding(new Insets(0, 16, 0, 16));
        hBox1.setAlignment(Pos.CENTER_LEFT);


        vBox1.getChildren().addAll(hBox1,vBox);
        
        Scene scene = new Scene(vBox1, 400, 600);
        stage.setScene(scene);
        stage.show();
    }
}
