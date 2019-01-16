/*
 * SE 2811-041
 * Winter 2018
 * Lab
 * Name: Junxiao Li
 * Created: {date}
 */

package sample;

public class ShieldBee extends BeeDecorator {
    private String type;
    public ShieldBee(Bee bee, String beeType) {
        super(bee, beeType);
        this.type = beeType;
        setEnable(true);
        tickEvent = (o,c)->seekerFunc(c);
    }

    @Override
    public void onEnable(){
        if(this.type.equals("Seeker")){
            initImage("file:shieldBee.jpg",new Vector2(0.5, 0.5), 50);
        }
    }

}
