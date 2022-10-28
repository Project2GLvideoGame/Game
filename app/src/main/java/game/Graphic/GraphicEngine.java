package game.Graphic;

import java.util.ArrayList;
import java.util.List;
import javafx.Application.application;


public class GraphicEngine {
    

    List<Displayable> allDisplayables = new ArrayList<>();

    public void setPosition(Displayable displayable, double x, double y) {
        displayable.setX(x);
        displayable.setY(y);
    }

    public void rotate(Displayable displayable, double angle) {
        
    }

    public void scale(Displayable displayable, double percentage) {

    }
    
    public void setVisibility(Displayable displayable, boolean value) {

    }
}
