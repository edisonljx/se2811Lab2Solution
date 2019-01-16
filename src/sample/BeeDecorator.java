/*
 * SE 2811-041
 * Winter 2018
 * Lab
 * Name: Junxiao Li
 * Created: {date}
 */

package sample;

abstract class BeeDecorator extends Bee{
    private Bee wrappedBee;

    public BeeDecorator(Bee bee, String beeType){
        super(beeType);
        this.wrappedBee = bee;
    }
}
