package sample;

import javafx.scene.input.KeyCode;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.util.ArrayList;

public abstract class Species {
    public static ArrayList<Species> species = new ArrayList<>();
    public static ArrayList<Action> trigEvents = new ArrayList<>();
    private boolean enabled = false;
    private int currentIndex;
    public String name;
    public boolean getEnable() {
        return enabled;
    }
    public Vector2 position;
    private ImageView view;
    private double scale;
    protected void initImage(String fileName, Vector2 initPosition, double scale)
    {
        this.scale = scale * 0.5;
        view = new ImageView(new Image(fileName));
        position = initPosition;
        updateGraphics();
        view.setPreserveRatio(true);
        view.setFitWidth(scale);
        Garden.mainPane.getChildren().add(view);
    }

    protected void updateImage(String imageName)
    {
        view.setImage(new Image(imageName));
    }

    protected void disposeImage()
    {
        Garden.mainPane.getChildren().remove(view);
    }

    public void updateGraphics()
    {
        view.setLayoutX(position.x * Garden.mainPane.getWidth() - scale);
        view.setLayoutY((1 - position.y) * Garden.mainPane.getHeight() - scale);
    }
    public void setEnable(boolean enable) {
        if (enable == enabled) return;
        enabled = enable;
        if (enable) {
            trigEvents.add(() ->
            {
                currentIndex = species.size();
                species.add(this);
                onEnable();
            });

        } else {
            trigEvents.add(()->
            {
                species.set(currentIndex, species.get(species.size() - 1));
                species.get(currentIndex).currentIndex = currentIndex;
                species.remove(species.size() - 1);
                onDisable();
            });
        }

    }
    public abstract void onEnable();
    public abstract void onDisable();
    public abstract void tick(Vector2 value);
    public abstract void lateTick(Vector2 value);
    //TODO
    //Rendering Layer controller
}
