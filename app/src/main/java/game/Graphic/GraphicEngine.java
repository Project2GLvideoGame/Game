package game.Graphic;

import java.util.ArrayList;
import java.util.List;

import game.GameObject;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GraphicEngine extends Application {

    private final int width = 700;
    private final int height = 700;

    List<Displayable> displayables = new ArrayList<>();
    List<Scene> scenes = new ArrayList<>();

    private Parent createContent() {
        GameObject g = new GameObject(new Displayable(new ImageView("shrek.jpeg"), 150, 150, 100, 100));
        Group group = new Group();
        group.getChildren().add(g.getDisplayable().getAsset());
        setVisibility(g.getDisplayable(), false);
        return group;
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), width, height));
        stage.show();
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
}
