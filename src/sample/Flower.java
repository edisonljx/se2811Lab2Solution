package sample;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Random;

public class Flower extends Species {
    public int energy;
    final int FULLENERGY = 10;
    public static ArrayList<Flower> allFlowers = new ArrayList<>();
    public Flower() {
        setEnable(true);
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
            energy--;
            if (energy <= 0) {
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
