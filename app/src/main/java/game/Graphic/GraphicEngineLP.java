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
    private final int width = 700;
    private final int height = 700;
    List<Displayable> displayables = new ArrayList<>();
    List<Scene> scenes = new ArrayList<>();
    static ImageView i_shrek;
    static ImageView i_mur;
    static PhysicalEngine pe;
    static Physical  shrek_physical = new Physical(100, 0, 150, 150);
    static Physical mur_physical = new Physical(100, 600, 500, 50);

    public static void main(String[] args) {
        shrek_physical.setSpeed(0);
        //p.setAcceleration(0.07);
        //p.setDirection(95);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        var root = new Group();
        i_shrek = new ImageView("shrek.jpeg");
        GameObject shrek = new GameObject(new Displayable(i_shrek, 100, 0, 150, 150));
        i_mur = new ImageView("shrek.jpeg");
        GameObject mur   = new GameObject(new Displayable(i_mur, 100, 600, 50, 500));
        
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
                shrek_physical.setSpeed(10);
                shrek_physical.setDirection(Double.parseDouble(textArea.getText()));
                pe.compute(shrek_physical);
                i_shrek.relocate(shrek_physical.getX(), shrek_physical.getY());

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
                pe.addPhysicalObject(mur_physical);
                pe.addPhysicalObject(shrek_physical);
            }
        });
        
        
        
        root.getChildren().add(shrek.getComponent(Displayable.class).getAsset());
        root.getChildren().add(mur.getComponent(Displayable.class).getAsset());
        root.getChildren().add(btn_update);
        root.getChildren().add(textArea);
        //root.getChildren().add(pe.physicalObjects.get(1).printBoxCollider());
        setVisibility(shrek.getComponent(Displayable.class), true);
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }


    public void setVisibility(Displayable displayable, boolean value) {
        displayable.getAsset().setVisible(value);
    }

}
