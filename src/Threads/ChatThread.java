package Threads;

import Server.Controller.MiddleTier;
import glory_schema.ConstantElement;

/**
 *
 * @author Team stark
 */
public class ChatThread implements Runnable {

    MiddleTier ServerCall;

    public ChatThread() {
        ServerCall = new MiddleTier();
    }

    @Override
    public void run() {
        try {
            ServerCall.sendMessage(ConstantElement.GlobalUserName, ConstantElement.message, ConstantElement.chatReciever);
            ConstantElement.isSend = true;
        } catch (Exception e) {
        }
    }

}
