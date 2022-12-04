package engine.physics;

import java.util.ArrayList;
import java.util.List;

/**
 * Permet au moteur physique de connaitre les coordonées passées des objets
 * une fois qu'il les a placés en collision,
 * indispensable pour un RollBack
 */
public class PreviousWorld {

    List<PreviousPhysical> previousPhysicals = new ArrayList<>();
    
    protected void addpreviousPhysical(PreviousPhysical previousPhysical) {
        previousPhysicals.add(previousPhysical);
    }

    protected List<PreviousPhysical> getPreviousPhysicals(){
        return this.previousPhysicals;
    }

    protected PreviousPhysical getPreviousPhysical(int i){
        return previousPhysicals.get(i);
    }



    class PreviousPhysical{
        
        Physic physic;
        Coordinate previousCoordinate;

        public PreviousPhysical(Physic physic, Coordinate previousCoordinate) {
            this.physic = physic;
            this.previousCoordinate = previousCoordinate;
        }
        
        public Physic getPhysic() {
            return physic;
        }

        public Coordinate getPreviousCoordinate() {
            return previousCoordinate;
        }

    }









}
