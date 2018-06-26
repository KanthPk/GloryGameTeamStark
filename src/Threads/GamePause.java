/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Server.Controller.MiddleTier;
import glory_schema.ConstantElement;

/**
 *
 * @author TeamStark
 */
public class GamePause implements Runnable {

    MiddleTier server = new MiddleTier();

    @Override
    public void run() {
        try {
            if (ConstantElement.isPause) {               
                ConstantElement.isLive = true;               
                System.out.println("Thread for pause");
                server.pauseGame("Stop");
            }
        } catch (Exception e) {
        }
    }

}
