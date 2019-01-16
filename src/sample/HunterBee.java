/*
 * SE 2811-041
 * Winter 2018
 * Lab
 * Name: Junxiao Li
 * Created: {date}
 */

package sample;

public class HunterBee extends Bee {
    private double speed;
    public HunterBee(String beeType){
        super(beeType);
        this.speed = ORIGINSPEED;
        tickEvent = (o, c)->hunterFunc(c);
    }

    @Override
    public void onEnable(){
        initImage("file:bee-3.jpg",new Vector2(Math.random() * 0.8 + 0.1, Math.random() * 0.8 + 0.1), 50);
    }

//    @Override
//    public void onDisable(){
//        allBees.remove(this);
//        disposeImage();
//    }
}
