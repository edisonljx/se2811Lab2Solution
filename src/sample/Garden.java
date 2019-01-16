package sample;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Garden {
    private String hunterBee = "Hunter";
    private String seekerBee = "Seeker";
    private String playerBee = "Player";

    @FXML
    private Pane gardenPane;                // capture the pane we are drawing on from JavaFX
    public static Pane mainPane;
    @FXML
    public void initialize() {              // executed after scene is loaded but before any methods
        mainPane = gardenPane;
        // for fun, set up a gradient background; could probably do in SceneBuilder as well
        // note the label has a Z index of 2 so it is drawn above the panel, otherwise it may be displayed "under" the panel and not be visible
        gardenPane.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(goldenrod, 20%), derive(goldenrod, -40%));");
        gardenPane.setFocusTraversable(true); // ensure garden pane will receive keypresses
        //Generate components here:
//        new Bee(Bee.BeeType.Hunter);
//        new Bee(Bee.BeeType.Player);
//        new Bee(Bee.BeeType.Seeker);
//        Bee pB = new ShieldBee(new PlayerBee(playerBee), playerBee);
        new SpeedBee(new PlayerBee(playerBee), playerBee);
        new ShieldBee(new SeekerBee(seekerBee), seekerBee);
        new SeekerBee(seekerBee);
        new HunterBee(hunterBee);
//        new SpeedBee(pB, playerBee);

        new Flower();
        new Flower();
        new Flower();
        new Flower();


    }
    //Pre load the vector2 value in case GC stress

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        Vector2 value = null;
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            value = Vector2.getRight();
        } else if (keyEvent.getCode() == KeyCode.LEFT) {
            value = Vector2.getLeft();
        } else if (keyEvent.getCode() == KeyCode.DOWN) {
            value = Vector2.getDown();
        } else if (keyEvent.getCode() == KeyCode.UP) {
            value = Vector2.getUp();
        }
        if(value != null){
            for(Action a : Species.trigEvents){
                a.run();
            }
            Species.trigEvents.clear();
            for (Species s : Species.species) {
                s.tick(value);
            }
            for(Species s : Species.species){
                s.lateTick(value);
            }
            for(Species s: Species.species){
                s.updateGraphics();
            }
        }

    }
}
