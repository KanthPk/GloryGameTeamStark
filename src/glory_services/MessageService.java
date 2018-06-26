/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_services;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author TeamStark
 */
public class MessageService {

    public static String imgPath;
    public static String headerName;
    public static String msgValue;

    static Image img = null;
    static ImageView imgView = new ImageView();

    public MessageService() {
    }

    public static Image getMakeMessageUI() {
        return img;
    }

    public static void setMakeMessageUI(String msgType, String header, String value) throws Exception {
        try {

            switch (msgType) {
                case "Error":
                    img = null;
                    img = new Image("/resources/icons/Err-Information.png");
                    headerName = header;
                    msgValue = value;
                    break;
                case "Success":
                    img = null;
                    img = new Image("/resources/icons/success.png");
                    headerName = header;
                    msgValue = value;
                    break;
                case "Warning":
                    img = null;
                    img = new Image("/resources/icons/warning.png");
                    headerName = header;
                    msgValue = value;
                    break;
                case "Live":
                    img = null;
                    img = new Image("/resources/icons/live_err.png");
                    headerName = header;
                    msgValue = value;
                    break;
                case "Information":
                    img = null;
                    img = new Image("/resources/icons/Information.png");
                    headerName = header;
                    msgValue = value;
                    break;
                case "Delete":
                    img = null;
                    img = new Image("/resources/icons/button_cancel.png");
                    headerName = header;
                    msgValue = value;
                    break;
                case "mail":
                    img = null;
                    img = new Image("/resources/icons/mail_receiver.png");
                    headerName = header;
                    msgValue = value;
                    break;
                case "successFlag":
                    img = null;
                    img = new Image("/resources/icons/glory_sucess.png");
                    headerName = header;
                    msgValue = value;
                    break;
                case "question":
                    img = null;
                    img = new Image("/resources/icons/question.png");
                    headerName = header;
                    msgValue = value;
                    break;
                case "pause":
                    img = null;
                    img = new Image("/resources/icons/pause.png");
                    headerName = header;
                    msgValue = value;
                    break;
                default:
                    throw new Exception("Invalid");
            }
        } catch (Exception e) {
        }
    }
}
