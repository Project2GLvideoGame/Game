package game.entity;

import engine.Component;
import engine.GameObject;
import engine.Kernel;
import engine.physics.Physic;

public class Player extends GameObject{

    final private int speed;
    private int lifePoint = 3;
    private PlayerLifePoint lifePointGameObject;

    public Player(int speed, Component... components){
        super(components);
        getComponent(Physic.class).setSpeed(0);
        this.lifePointGameObject = new PlayerLifePoint();
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

    public PlayerLifePoint getLifePointGameObject(){
        return lifePointGameObject;
    }

    public void takeDamage(){
        this.lifePoint-=1;
        System.out.println("On Player.class : LifePoint = "+lifePoint);
        this.lifePointGameObject.graphicPlayerLife(this.lifePoint);
    }

    public boolean isDead(){
        return this.lifePoint<=0;
    }



}
