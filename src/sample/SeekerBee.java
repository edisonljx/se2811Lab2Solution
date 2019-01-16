/*
 * SE 2811-041
 * Winter 2018
 * Lab
 * Name: Junxiao Li
 * Created: {date}
 */

package sample;

public class SeekerBee extends Bee {
    private double speed;
    public SeekerBee(String beeType) {
        super(beeType);
        speed = ORIGINSPEED;
        tickEvent = (o, c)->seekerFunc(c);
    }

    @Override
    public void onEnable(){
        initImage("file:bee-2.jpg", new Vector2(Math.random() * 0.8 + 0.1, Math.random() * 0.8 + 0.1), 50);
    }

//    @Override
//    public void onDisable(){
//        allBees.remove(this);
//        disposeImage();
//    }


}
