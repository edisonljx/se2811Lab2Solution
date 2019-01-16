/*
 * SE 2811-041
 * Winter 2018
 * Lab
 * Name: Junxiao Li
 * Created: {date}
 */

package sample;

public class PlayerBee extends Bee {
    private double speed;

    public PlayerBee(String beeType) {
        super(beeType);
        speed = ORIGINSPEED;
        tickEvent = (o, c)->characterFunc(c);
    }

    @Override
    public void onEnable(){
        initImage("file:bee-1.jpg", new Vector2(0.5, 0.5), 50);
    }

//    @Override
//    public void onDisable(){
//        allBees.remove(this);
//        disposeImage();
//    }
}
