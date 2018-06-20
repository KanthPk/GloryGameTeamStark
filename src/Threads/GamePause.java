/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import glory_schema.ConstantElement;

/**
 *
 * @author AshanPerera
 */
public class GamePause implements Runnable {

    @Override
    public void run() {
        if (ConstantElement.isPause) {
            //server call
            //save the data from here
            ConstantElement.isLive = true;
            //push data
            //sleep Thread 1000
            //Thread.sleep(1000);
            System.out.println("Thread 1");
        }
    }

}
