package game.Graphic;

import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.List;
import game.GameObject;
import game.Physic.Physical;
import game.Physic.PhysicalEngine;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GraphicEngineLP extends Application {
    private final int Windowwidth = 700;
    private final int Windowheight = 700;
    static PhysicalEngine pe;
    
    static ImageView i_shrek = new ImageView("shrek.jpeg");
    static GameObject shrek = new GameObject(
        new Physical(100, 100, 50, 50),
        new Displayable(i_shrek, 100, 100, 50, 50)
        );
    
    static ImageView i_mur_sud = new ImageView("shrek.jpeg");
    static GameObject mur_sud = new GameObject(
        new Physical(100, 600, 500, 50),
        new Displayable(i_mur_sud, 100, 600, 50, 500)
        );

    static ImageView i_mur_nord = new ImageView("shrek.jpeg");
    static GameObject mur_nord = new GameObject(
        new Physical(100, 0, 500, 50),
        new Displayable(i_mur_nord, 100, 0, 50, 500)
        );

    static ImageView i_mur_est = new ImageView("shrek.jpeg");
    static GameObject mur_est = new GameObject(
        new Physical(650, 20, 50, 600),
        new Displayable(i_mur_est, 650, 20, 600, 50)
        );

    static ImageView i_mur_ouest = new ImageView("shrek.jpeg");
    static GameObject mur_ouest = new GameObject(
        new Physical(0, 20, 50, 600),
        new Displayable(i_mur_ouest, 0, 20, 600, 50)
        );



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        var root = new Group();
        TextArea textArea = new TextArea();
        textArea.setText("200");
        textArea.setPrefSize(30, 30);


        var animation = new AnimationTimer() {
            private long lastUpdate = 0 ;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 0) {
                    lastUpdate = now ;
                    do_it();
                }
            }
            private void do_it(){
                shrek.getComponent(Physical.class).setSpeed(4);
                 try{shrek.getComponent(Physical.class).setDirection(Double.parseDouble(textArea.getText()));} catch (Exception e) {shrek.getComponent(Physical.class).setDirection(0);}
                pe.compute(shrek.getComponent(Physical.class));
                i_shrek.relocate(shrek.getComponent(Physical.class).getX(), shrek.getComponent(Physical.class).getY());
            }
        };
        
        
        Button btn_update = new Button();
        btn_update.setPadding(new Insets(50, 0, 0, 50));
        btn_update.setText("start");
        btn_update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                animation.start();
                pe = new PhysicalEngine();
                pe.addPhysicalObject(mur_sud.getComponent(Physical.class));
                pe.addPhysicalObject(mur_nord.getComponent(Physical.class));
                pe.addPhysicalObject(mur_est.getComponent(Physical.class));
                pe.addPhysicalObject(mur_ouest.getComponent(Physical.class));
                pe.addPhysicalObject(shrek.getComponent(Physical.class));
            }
        });
        
        
        
        root.getChildren().add(shrek.getComponent(Displayable.class).getAsset());
        root.getChildren().add(mur_sud.getComponent(Displayable.class).getAsset());
        root.getChildren().add(mur_nord.getComponent(Displayable.class).getAsset());
        root.getChildren().add(mur_est.getComponent(Displayable.class).getAsset());
        root.getChildren().add(mur_ouest.getComponent(Displayable.class).getAsset());

        root.getChildren().add(btn_update);
        root.getChildren().add(textArea);
        stage.setScene(new Scene(root, Windowwidth, Windowheight));
        stage.show();
    }







    public void setVisibility(Displayable displayable, boolean value) {
        displayable.getAsset().setVisible(value);
    }

}
