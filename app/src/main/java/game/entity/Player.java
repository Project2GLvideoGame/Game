package game.entity;

import engine.Component;
import engine.GameObject;
import engine.physics.Physic;

public class Player extends GameObject{

    private int speed;

    public Player(int speed, Component... components){
        super(components);
        getComponent(Physic.class).setSpeed(0);
        this.speed = speed;
    }

    public void setPlayerDirection(int degree){
        getComponent(Physic.class).setDirection(degree);
    }

    public void setPlayerSpeed(int speed){
        getComponent(Physic.class).setSpeed(speed);
    }

    public void setSpeed(int speed){
        getComponent(Physic.class).setSpeed(speed);
    }

    public int getSpeed(){
        return this.speed;
    }
}
