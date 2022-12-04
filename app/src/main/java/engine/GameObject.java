package engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe correspond à un élément central de notre architecture puisque chaque élément qu'il est possible
 * de créer est un GameObject. Par ailleurs, un GameObject peut contenir un ou plusieurs Components qui
 * correspondent à des types de fonctionnement d'un GameObject. Par exemple, si un GameObject est créé avec les
 * Component Physic et Displayable, il sera possible pour ce même GameObject d'être affiché par le moteur graphique
 * et d'être représenté physiquement (par des coordonnées, collisions, etc.) par le moteur physique.
 */
public class GameObject{

    private final List<Component> components = new ArrayList<>();

    /**
     * Créé un GameObject
     * @param in_components Les différents Components souhaités pour le GameObject
     */
    public GameObject(Component ...in_components) {
        for (Component component : in_components) {
            components.add(component);
            component.setGameObject(this);
        }
    }

    /**
     * Permet de récupérer un certain Component du GameObject
     * @param class1 La classe du Component souhaité
     * @return Le Component s'il est présent dans la liste de Component du GameObject, null sinon
     * @param <T> Le type correspondant à une classe (ici, les différents Components
     *           (Physic, Displayable, Soundable, etc.)
     */
    public <T extends Component> T getComponent(Class<T> class1) {
        for(Component component : components)    
            if(class1.isAssignableFrom(component.getClass()))
                return (T) component;
        return null;
    }

    /**
     * Permet de récupérer la liste de Component d'un GameObject
     * @return La liste de Component du GameObject
     */
    public List<Component> getComponents() {
        return components;
    }
}
