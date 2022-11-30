package engine.physics;

import java.util.ArrayList;
import java.util.List;

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
