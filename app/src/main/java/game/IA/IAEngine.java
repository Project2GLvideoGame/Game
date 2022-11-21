package game.IA;

import java.util.ArrayList;

import game.GameObject;
import game.Physics.Physic;

/**
 * IAEngine
 */
public class IAEngine {

    Enemies ufo = new UFO();
    ArrayList<Squid> squids = new ArrayList<Squid>();
    ArrayList<Crab> crabs1 = new ArrayList<Crab>();
    ArrayList<Crab> crabs2 = new ArrayList<Crab>();
    ArrayList<Octopus> octopuses1 = new ArrayList<Octopus>();
    ArrayList<Octopus> octopuses2 = new ArrayList<Octopus>();
    ArrayList<Enemies> basicsEnemis = new ArrayList<>();
    int widthWindow , HeightWindow;
    private boolean isCollisionRigth = true;
    
    public IAEngine( ArrayList<Octopus> octopuses1 , ArrayList<Octopus> octopuses2 ,
    ArrayList<Squid> squids, ArrayList<Crab> crabs1, ArrayList<Crab> crabs2, UFO ufo, int widthWindow ,int HeightWindow) {
    this.squids = squids;
    this.crabs1 = crabs1;
    this.crabs2 = crabs2;
    this.octopuses1 = octopuses1;
    this.octopuses2 = octopuses2;
    this.ufo = ufo;
    this.basicsEnemis.addAll(octopuses2);
    this.basicsEnemis.addAll(octopuses1);
    this.basicsEnemis.addAll(crabs2);
    this.basicsEnemis.addAll(crabs1);
    this.basicsEnemis.addAll(squids);
}

    public void Compute(){
        CheckBorder();
    }

     public boolean CheckBorder(){

        return true;
     }
     private  void ChangeDirection(boolean isCollisionRigth){}
     public void fire(Enemies ennemis){

     }

     public int checkFarstestOnRight(){
        int currentFarstest = 0;

        if(squids.get(squids.size()-1).getComponent(Physic.class).getX() > currentFarstest)    currentFarstest = (int) octopuses2.get(octopuses2.size()-1).getComponent(Physic.class).getX();
        if(crabs1.get(crabs1.size()-1).getComponent(Physic.class).getX() > currentFarstest)   currentFarstest = (int) octopuses2.get(octopuses2.size()-1).getComponent(Physic.class).getX(); 
        if(crabs2.get(crabs2.size()-1).getComponent(Physic.class).getX() > currentFarstest)  currentFarstest = (int) octopuses2.get(octopuses2.size()-1).getComponent(Physic.class).getX();  
        if(octopuses1.get(octopuses1.size()-1).getComponent(Physic.class).getX() > currentFarstest)    currentFarstest = (int) octopuses2.get(octopuses2.size()-1).getComponent(Physic.class).getX();
        if(octopuses2.get(octopuses2.size()-1).getComponent(Physic.class).getX() > currentFarstest)    currentFarstest = (int) octopuses2.get(octopuses2.size()-1).getComponent(Physic.class).getX();
    
        return currentFarstest;

     }

     public int checkFarstestOnLeft(){
        int currentFarstest = 0;

        return currentFarstest;

     }
     public void removeEnnemis(Enemies ennemis){

     }
 
}