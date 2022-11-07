package game;

import java.util.ArrayList;
import java.util.List;

import game.Graphic.Displayable;
import game.Physic.Physical;

public class GameObject{
        
    private List<Component> components = new ArrayList<>();
    public Class<Displayable> getComponent;
    
    public GameObject(Component ...in_components){
        for (Component component : in_components) {
            components.add(component);
        }
    }
    
    public <T extends Component> T getComponent(Class<T> class1){
        for(Component component : components)    
            if(class1.isAssignableFrom(component.getClass()))
                return (T)component;
        return null;
    }   




}
