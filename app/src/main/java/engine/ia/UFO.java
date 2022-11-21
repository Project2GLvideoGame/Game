package engine.ia;

import java.util.Random;

public class UFO extends Enemies{

    @Override
    public int getPoint() {
        Random random = new Random();
        return 50 * (random.nextInt(6 - 1) + 1);
    }
    @Override
    public String getImagePath() {
        // TODO Auto-generated method stub
        return "";
    }
    @Override
    public void fire() {
        // TODO Auto-generated method stub
        
    }
}
