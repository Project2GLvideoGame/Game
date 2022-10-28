package game.Graphic;

import javafx.scene.image.ImageView;

public class Displayable {

    private final ImageView asset;

    public Displayable(ImageView asset, double x, double y, int height, int width) {
        this.asset = asset;
        this.asset.setX(x);
        this.asset.setY(y);
        this.asset.setFitHeight(height);
        this.asset.setFitWidth(width);
    }

    public double getX() {
        return asset.getX();
    }

    public double getY() {
        return asset.getY();
    }

    public void setX(double x) {
        asset.setX(x);
    }

    public void setY(double y) {
        asset.setY(y);
    }

    public ImageView getAsset() {
        return asset;
    }
}
