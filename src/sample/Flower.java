package sample;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Random;

public class Flower extends Species {
    public int energy;
    final int FULLENERGY = 100;
    public static ArrayList<Flower> allFlowers = new ArrayList<>();
    public Flower() {
        setEnable(true);
        this.energy = FULLENERGY;
    }
    @Override
    public void tick(Vector2 value) {

    }

    @Override
    public void lateTick(Vector2 value) {

    }

    public void onCollideWithBee(Bee targetBee){
        if(energy > 0) {
            targetBee.energy++;
            this.energy--;
            if (this.energy <= 0) {
                updateImage("file:rose.jpg");
            }
        }else{
            targetBee.energy--;
        }
        position = new Vector2(Math.random() * 0.8 + 0.1, Math.random() * 0.8 + 0.1);
    }

    @Override
    public void onEnable() {
        initImage("file:aster.jpg", new Vector2(Math.random() * 0.8 + 0.1, Math.random() * 0.8 + 0.1), 50);
        energy = 3;
        allFlowers.add(this);
        position = new Vector2(Math.random(), Math.random());
    }

    @Override
    public void onDisable() {
        disposeImage();
        allFlowers.remove(this);
    }
}
