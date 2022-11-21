package engine;

import java.util.ArrayList;
import java.util.List;


public class GameObject{
        
    private List<Component> components = new ArrayList<>();
    
    public GameObject(Component ...in_components){
        for (Component component : in_components) {
            components.add(component);
            component.setGameObject(this);
        }
    }
    
    public <T extends Component> T getComponent(Class<T> class1){
        for(Component component : components)    
            if(class1.isAssignableFrom(component.getClass()))
                return (T)component;
        return null;
    }   




}
