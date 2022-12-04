package game.entity;

import engine.Component;
import engine.GameObject;
import engine.physics.Physic;

/**
 *  A Player  
 **/
public class Player extends GameObject{

    final private int speed;
    private int lifePoint = 3;
    private PlayerLifePoint lifePointGameObject;

    /**
     * Create a player
     * @param speed Speed of a player
     * @param components Components for a player
     */
    public Player(int speed, Component... components){
        super(components);
        getComponent(Physic.class).setSpeed(0);
        this.lifePointGameObject = new PlayerLifePoint();
        this.speed = speed;
    }

    /**
     * Set a player direction
     * @param degree Direction in degree
     */
    public void setDirection(int degree){
        getComponent(Physic.class).setDirection(degree);
    }

    /**
     * Set a player speed
     * @param speed Speed for a player
     */
    public void setSpeed(int speed){
        getComponent(Physic.class).setSpeed(speed);
    }

    /**
     * @return Player speed
     */
    public int getSpeed(){
        return this.speed;
    }

    /**
     * @return Player life point
     */
    public PlayerLifePoint getLifePointGameObject(){
        return lifePointGameObject;
    }

    /**
     * Reduce player HP
     */
    public void takeDamage(){
        this.lifePoint-=1;
        System.out.println("On Player.class : LifePoint = "+lifePoint);
        this.lifePointGameObject.graphicPlayerLife(this.lifePoint);
    }

    /**
     * @return If a player is alive
     */
    public boolean isDead(){
        return this.lifePoint<=0;
    }

    /**
     * Kill a player
     */
    public void kill(){
        this.lifePoint=0;
    }



}
