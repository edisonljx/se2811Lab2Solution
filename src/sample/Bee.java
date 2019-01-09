package sample;

import javafx.scene.input.KeyCode;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.Random;

public class Bee extends Species {

    public enum BeeType
    {
        Hunter, Player, Seeker
    }
    private BeeType beeType;
    public int energy;
    private double speed;
    private TickFunc<Bee, Vector2> tickEvent;
    final int FULLHP = 50;
    final double ORIGINSPEED  = 0.05;
    final double SLOWSPEED = 0.01;
    private int slowStep = 0;
    public static ArrayList<Bee> allBees = new ArrayList<>();
    public Bee(BeeType beeType){
        this.beeType = beeType;
        setEnable(true);
        energy = FULLHP;
        speed = ORIGINSPEED;
        switch (beeType)
        {
            case Hunter:
                tickEvent = (o, c)->o.hunterFunc(c);
                break;
            case Player:
                tickEvent =(o, c)->o.characterFunc(c);
                break;
            case Seeker:
                tickEvent = (o, c) -> o.seekerFunc(c);
                break;
        }
    }

    @Override
    public void tick(Vector2 value) {
        if(slowStep > 0){
            slowStep--;
            speed = SLOWSPEED;
        }else{
            speed = ORIGINSPEED;
        }
        if(energy <= 0) setEnable(false);
        tickEvent.run(this, value);
        energy--;
        position.x = Math.max(Math.min(1, position.x), 0);
        position.y = Math.max(Math.min(1, position.y), 0);
        System.out.println(energy);
    }

    public void characterFunc(Vector2 value)
    {
        position.x += value.x * speed;
        position.y += value.y * speed;
    }

    private boolean seekForFlower()
    {
        double leastDistance = Double.MAX_VALUE;
        Flower targetFlower = null;
        for (Flower f : Flower.allFlowers){
            if(f.energy > 0){
                double dist = Vector2.distance(f.position, position);
                if(dist < leastDistance){
                    leastDistance = dist;
                    targetFlower = f;
                }
            }
        }
        if(targetFlower != null) {
            Vector2 target = new Vector2((targetFlower.position.x - position.x), (targetFlower.position.y - position.y));
            position.x += speed * target.x / leastDistance;//Normalize the vector direction
            position.y += speed * target.y / leastDistance;
            return true;
        }
        return false;
    }

    private boolean seekForAnotherBee()
    {
        double beeDist = Double.MAX_VALUE;
        Bee bb = null;
        for (Bee b : allBees) {
            if (b != this && b.energy > 0) {
                double currentDist = Vector2.distance(b.position, position);
                if (currentDist < beeDist) {
                    bb = b;
                    beeDist = currentDist;
                }
            }
        }
        if(bb == null) return false;
        Vector2 target = new Vector2((bb.position.x - position.x), (bb.position.y - position.y));
        position.x += speed * target.x / beeDist;//Normalize the vector direction
        position.y += speed * target.y / beeDist;
        return true;
    }

    public void hunterFunc(Vector2 value)
    {
        if(!seekForAnotherBee())
            seekForFlower();
    }

    public void seekerFunc(Vector2 value)
    {
        if(!seekForFlower())
            seekForAnotherBee();
    }

    @Override
    public void lateTick(Vector2 value) {
        for (Bee b : allBees) {
            if( b != this && Vector2.distance(b.position, position) < 0.05){ //Contact with other bees
                speed = SLOWSPEED;
                slowStep = 3;
                position = new Vector2(Math.random() * 0.8 + 0.1, Math.random() * 0.8 + 0.1);
            }
        }
        for(Flower f : Flower.allFlowers){
            if(Vector2.distance(f.position, position) < 0.05){
                f.onCollideWithBee(this);
                position = new Vector2(Math.random() * 0.8 + 0.1, Math.random() * 0.8 + 0.1);
            }
        }
    }

    @Override
    public void onEnable() {
        allBees.add(this);
        switch (beeType) {
            case Hunter:
                initImage("file:bee-3.jpg",new Vector2(Math.random() * 0.8 + 0.1, Math.random() * 0.8 + 0.1), 50);
                break;
            case Player:
                initImage("file:bee-1.jpg", new Vector2(0.5, 0.5), 50);
                break;
            case Seeker:
                initImage("file:bee-2.jpg", new Vector2(Math.random() * 0.8 + 0.1, Math.random() * 0.8 + 0.1), 50);
                break;
        }
    }

    @Override
    public void onDisable() {
        allBees.remove(this);
        disposeImage();
    }
}
