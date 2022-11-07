package game.Graphic;

import java.util.ArrayList;
import java.util.List;

import game.GameObject;
import game.Physic.Physical;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphicEngine {
    
    private Group mainGroup = new Group();

    private final int width = 700;
    private final int height = 700;

    private List<Displayable> displayables = new ArrayList<>();
    private Scene scene;

    

    //Initialise les gameObject Initialement présent dans la scène 
    private void createContent() {
        // GameObject g = new GameObject(new Displayable(new ImageView("pacman_run.gif"), width/2 - 32, height/2 - 32, 64, 64));
        
        // mainGroup.getChildren().add(g.getComponent(Displayable.class).getAsset());
        // displayables.add(g.getComponent(Displayable.class));
        
        Button btn = new Button("TestButton");
        btn.setOnAction(this::actionEvent);
        mainGroup.getChildren().add(btn);

    }

    public void init(Stage stage) throws Exception {
        createContent();

        stage.setScene(new Scene(mainGroup, width, height));
        stage.getScene().setFill(Color.web("#5b60e5"));

        this.scene = stage.getScene();
        stage.show();
    }

    public Scene getScene(){return scene;}

    private void actionEvent(ActionEvent event){
        scale(displayables.get(0), displayables.get(0).getAsset().getScaleX() + 0.2d);
    }

    public void setPosition(Displayable displayable, double x, double y) {
        displayable.setX(x);
        displayable.setY(y);
    }
    public void relocate(Displayable displayable, double x, double y){
        displayable.getAsset().relocate(x, y);
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
