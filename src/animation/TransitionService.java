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
 * @author AshanPerera
 */
public class TransitionService {

    private FadeTransition fadeTransition;

    public TransitionService() {
        fadeTransition = new FadeTransition();
    }

    public FadeTransition makeFadeOut(AnchorPane layout) {
        try {
            fadeTransition.setDuration(Duration.millis(1000.0));
            fadeTransition.setNode((Node) layout);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(.5);
            return fadeTransition;
        } catch (Exception e) {
        }
        return null;
    }
}
