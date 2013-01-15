package de.haw.shc.utils;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import de.haw.shc.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMNF
 * Date: 21.12.12
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class ButtonListenerFactory {

    static final String LOG_TAG = "ButtonFactory";



    private ViewTransportTyp viewTransportTyp;


    public ButtonListenerFactory(ViewTransportTyp viewTransportTyp) {
        this.viewTransportTyp = viewTransportTyp;
    }


    private void checkControlls() {
        List<Control> controlList = Arrays.asList(viewTransportTyp.getControls());

        if (controlList.contains(Control.LIGHT)) {
            createLighListener();
        } else if (controlList.contains(Control.BLINDS)) {
            createBlindsListener();

        } else if (controlList.contains(Control.CURTAIN)) {
              createCurtainsListener();

        } else if (controlList.contains(Control.HEATING)) {
               createHeatingListener();

        } else if (controlList.contains(Control.WINDOW)) {
                createWindowsListener();

        }
    }

    private void createWindowsListener() {
        final Context  context = viewTransportTyp.getContext();
        View view =   viewTransportTyp.getView();
        Button button;


        button = (Button)view.findViewById(R.id.windowOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"Open windows in " + context);
            }
        });


        button = (Button)view.findViewById(R.id.windowClose);
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"Close windows in "+ context);
            }
        });
    }

    private void createHeatingListener() {
        final Context  context = viewTransportTyp.getContext();
        View view =   viewTransportTyp.getView();
        SeekBar seekBar;

        seekBar = (SeekBar)view.findViewById(R.id.HeatingSlider);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               Log.d(LOG_TAG,"Heating goes to " + seekBar.getProgress() + " in " + context);
            }
        });

    }

    private void createCurtainsListener() {
        final Context  context = viewTransportTyp.getContext();
        View view =   viewTransportTyp.getView();
        Button button;

        button = (Button)view.findViewById(R.id.CurtainsOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"Curtains open in "+context);
            }
        });

        button = (Button)view.findViewById(R.id.blindsClose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"Curtains close in "+context);
            }
        });

    }

    private void createBlindsListener() {
        final Context  context = viewTransportTyp.getContext();
        View view =   viewTransportTyp.getView();
        Button button;


        button = (Button)view.findViewById(R.id.blindsOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Log.d(LOG_TAG,"blinds open in " + context);
            }
        });

        button = (Button)view.findViewById(R.id.blindsClose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Log.d(LOG_TAG,"blinds close in "+context);
            }
        });

    }

    private void createLighListener() {
        View view =   viewTransportTyp.getView();
        Button button;
        SeekBar seekBar;

        final Context  context = viewTransportTyp.getContext();
        button =  (Button)view.findViewById(R.id.WhiteLightOn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"whitelight on in " + context);
            }
        });

        button = (Button)view.findViewById(R.id.WhiteLightOff);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"whitelight off in " + context);
            }
        });

        seekBar =  (SeekBar)view.findViewById(R.id.dimmbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               Log.d(LOG_TAG,"whitelight dim to "+ seekBar.getProgress() + " in " + context);
            }

              //TODO COLOR



        });

    }


    /*
    public void createButtons() {


        //TODO prüfen ob  controll in controlls vorhanden ist
        // buttons aus views suchen
        // butonlistener erstellen


        switch (viewTransportTyp.getContext()) {
            case BEDROOM:
                break;
            case DINING:
                break;
            case HALL:
                break;
            case KITCHEN:
                break;
            case LOUNGE:
                break;
            case ALL:
                break;


        }


    }
     */
}
