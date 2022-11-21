package game.Graphic;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class AnimationFrames {
    private List<Image> sprites = new ArrayList<>();
    private int spriteCounter = 0;
    private int animationFramerate = 0;
    private int maxAnimationFramerate = 0;

    public AnimationFrames(int framerate, int width, int height, String[] spritePaths){
        this.animationFramerate = framerate;
        this.maxAnimationFramerate = framerate;

        BufferedImage tempAsset;
        for(String sprite : spritePaths){

            try {
                tempAsset = ImageIO.read(getClass().getResource(sprite));
                this.sprites.add(tempAsset.getScaledInstance(width, height, Image.SCALE_DEFAULT));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }

    }

    public void setAnimationFramerate(int framerate){
        this.animationFramerate = framerate;
        this.maxAnimationFramerate = framerate;
    }

    public Image getCurrentSprite(){
        return this.sprites.get(this.spriteCounter);
    }

    public void updateSprite(){
        this.animationFramerate++;
        if(this.animationFramerate >= this.maxAnimationFramerate){
            this.animationFramerate = 0;
            this.spriteCounter++;
            this.spriteCounter %= this.sprites.size();
        }
    }
}
