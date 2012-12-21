package de.haw.shc.utils;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import de.haw.shc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMNF
 * Date: 21.12.12
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class ButtonListenerFactory {

    static final  String LOG_TAG = "ButtonFactory";

    static final String LOUNGE = "LOUNGE";
    static final String KITCHEN = "KITCHEN";
    static final String HALL = "HALL";
    static final String BEDROOM = "BEDROOM";
    static final String DINNING = "DINNING";

    private List<ButtonContextTransportTyp> buttonList;



    public ButtonListenerFactory(List<ButtonContextTransportTyp> buttonList) {
        this.buttonList = buttonList;
    }

    public void createButtons() {


        //TODO prüfen ob  controll in controlls vorhanden ist
        // buttons aus views suchen
        // butonlistener erstellen


        for(ButtonContextTransportTyp buttonContext: buttonList){

            if(buttonContext.getControls()  == Control.LIGHT){

            }
            else if(buttonContext.getControl() == Control.CURTAIN){


            }
            else if(buttonContext.getControl() == Control.BLINDS){


            }
            else if(buttonContext.getControl() == Control.WINDOW){


            }
            else if(buttonContext.getControl() == Control.HEATING){


            }



        }



    }

}
