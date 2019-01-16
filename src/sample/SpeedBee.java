/*
 * SE 2811-041
 * Winter 2018
 * Lab
 * Name: Junxiao Li
 * Created: {date}
 */

package sample;

public class SpeedBee extends BeeDecorator {


    private String type;
    public SpeedBee(Bee bee, String beeType){
        super(bee,beeType);
        setEnable(true);
        this.type = beeType;
        super.speed = SUPERSPEED;

        super.tickEvent = (o, c)->characterFunc(c, SUPERSPEED);
    }

    @Override
    public void onEnable(){
        if(this.type.equals("Player")){
            initImage("file:speedBee.jpg",new Vector2(0.5, 0.5), 50);
        }
    }

}
