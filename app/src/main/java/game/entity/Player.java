package game.entity;

import engine.Component;
import engine.GameObject;
import engine.physics.Physic;

public class Player extends GameObject{

    private int speed;
    private boolean shoot = false;
    private int lifePoint = 3;

    public Player(int speed, Component... components){
        super(components);
        getComponent(Physic.class).setSpeed(0);
        this.speed = speed;
    }

    public void setDirection(int degree){
        getComponent(Physic.class).setDirection(degree);
    }

    public void setSpeed(int speed){
        getComponent(Physic.class).setSpeed(speed);
    }

    public int getSpeed(){
        return this.speed;
    }

    public void shoot(){
        this.shoot = true;
    }

    public boolean isShooting(){
        return shoot;
    }

    public void disableShoot(){
        this.shoot = false;
    }

    public void takeDamage(){
        System.out.println(--this.lifePoint);
    }
}
