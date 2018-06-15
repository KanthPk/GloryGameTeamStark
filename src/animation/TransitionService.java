/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import java.awt.Color;
import javafx.animation.FadeTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author AshanPerera
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

}
