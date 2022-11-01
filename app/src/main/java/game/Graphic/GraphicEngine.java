package game.Graphic;

import java.util.ArrayList;
import java.util.List;

import game.GameObject;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphicEngine extends Application {
    
    Group mainGroup = new Group();

    private final int width = 700;
    private final int height = 700;

    List<Displayable> displayables = new ArrayList<>();
    List<Scene> scenes = new ArrayList<>();

    //Initialise les gameObject Initialement présent dans la scène 
    private void createContent() {
        GameObject g = new GameObject(new Displayable(new ImageView("pacman_run.gif"), width/2 - 32, height/2 - 32, 64, 64));
        Button btn = new Button("TestButton");
        btn.setOnAction(this::actionEvent);

        mainGroup.getChildren().add(g.getComponent(Displayable.class).getAsset());
        displayables.add(g.getComponent(Displayable.class));

        mainGroup.getChildren().add(btn);
    }

    @Override
    public void start(Stage stage) throws Exception {
        createContent();
        stage.setScene(new Scene(mainGroup, width, height));
        stage.getScene().setFill(Color.web("#5b60e5"));
        stage.show();
    }

    private void actionEvent(ActionEvent event){
        scale(displayables.get(0), displayables.get(0).getAsset().getScaleX() + 0.2d);
    }

    public void setPosition(Displayable displayable, double x, double y) {
        displayable.setX(x);
        displayable.setY(y);
    }

    public void rotate(Displayable displayable, double angle) {
        displayable.getAsset().setRotate(angle);
    }

    public void scale(Displayable displayable, double percentage) {
        displayable.getAsset().setScaleX(percentage);
        displayable.getAsset().setScaleY(percentage);
    }

    public void setVisibility(Displayable displayable, boolean value) {
        displayable.getAsset().setVisible(value);
    }

    public void addChildren(Displayable displayable){
        mainGroup.getChildren().add(displayable.getAsset());
        displayables.add(displayable);
    }
}
