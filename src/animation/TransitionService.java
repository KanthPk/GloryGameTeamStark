/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author TeamStark
 */
public class TransitionService {

    private final FadeTransition fadeTransition;

    public TransitionService() {
        fadeTransition = new FadeTransition();
    }

    public FadeTransition MakeFadeOut(AnchorPane layout) {
        try {
            fadeTransition.setDuration(Duration.millis(300.0));
            fadeTransition.setNode((Node) layout);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(.0);
            return fadeTransition;
        } catch (Exception e) {
        }
        return null;
    }

    public FadeTransition MakeFadeIn(AnchorPane layout) {
        try {
            fadeTransition.setDuration(Duration.millis(300.0));
            fadeTransition.setNode((Node) layout);
            fadeTransition.setFromValue(.0);
            fadeTransition.setToValue(1);
            return fadeTransition;
        } catch (Exception e) {
        }
        return null;
    }

    public FadeTransition MakeFadeOutInLiveGame(AnchorPane layout) {
        try {
            fadeTransition.setDuration(Duration.millis(550.0));
            fadeTransition.setNode((Node) layout);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(.0);
            return fadeTransition;
        } catch (Exception e) {
        }
        return null;
    }

    public FadeTransition MakeFadeInLiveGame(AnchorPane layout) {
        try {
            fadeTransition.setDuration(Duration.millis(550.0));
            fadeTransition.setNode((Node) layout);
            fadeTransition.setFromValue(.0);
            fadeTransition.setToValue(0.58);
            return fadeTransition;
        } catch (Exception e) {
        }
        return null;
    }

    public FadeTransition MakeFadeOutForAutoGen(AnchorPane layout) {
        try {
            fadeTransition.setDuration(Duration.millis(200.0));
            fadeTransition.setNode((Node) layout);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(.6);
            return fadeTransition;
        } catch (Exception e) {
        }
        return null;
    }

    public FadeTransition MakeFadeInForAutoGen(AnchorPane layout) {
        try {
            fadeTransition.setDuration(Duration.millis(200.0));
            fadeTransition.setNode((Node) layout);
            fadeTransition.setFromValue(.0);
            fadeTransition.setToValue(1);
            return fadeTransition;
        } catch (Exception e) {
        }
        return null;
    }

}
