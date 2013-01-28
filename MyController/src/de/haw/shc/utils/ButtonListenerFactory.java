package de.haw.shc.utils;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import de.haw.shc.R;
import de.haw.shc.utils.colorPicker.ColorPickerDialog;
import de.haw.shc.utils.messageAdapter.Message;
import de.haw.shc.utils.messageAdapter.MessageSender;
import de.haw.shc.utils.messageAdapter.Messages;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: TMNF
 * Date: 21.12.12
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class ButtonListenerFactory implements Serializable {

    static final String LOG_TAG = "ButtonFactory";


    //private ViewTransportTyp viewTransportTyp;


    public ButtonListenerFactory() {
        //this.viewTransportTyp = viewTransportTyp;
    }

    public void setListenerForView(ViewTransportTyp viewTransportTyp) {

        switch (viewTransportTyp.getControl()) {
            case LIGHT:
                createLighListener(viewTransportTyp);
                break;
            case BLINDS:
                createBlindsListener(viewTransportTyp);
                break;
            case CURTAIN:
                createCurtainsListener(viewTransportTyp);
                break;
            case HEATING:
                createHeatingListener(viewTransportTyp);
                break;
            case WINDOW:
                createWindowsListener(viewTransportTyp);
                break;
        }


    }

    private void createWindowsListener(ViewTransportTyp viewTransportTyp) {

        final Context context = viewTransportTyp.getRoom();
        View view = viewTransportTyp.getView();
        Button button;


        button = (Button) view.findViewById(R.id.windowOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Messages.createWindowOpenMessage(context);

                //debug log
                //Log.d(LOG_TAG, "Open windows in " + context);
                //Log.d(LOG_TAG, "Message" + message);

                MessageSender.windowControl(message);
            }
        });


        button = (Button) view.findViewById(R.id.windowClose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message message = Messages.createWindowCloseMessage(context);

                //debug log
                //Log.d(LOG_TAG, "Close windows in " + context);
                //Log.d(LOG_TAG, "Message" + message);

                MessageSender.windowControl(message);
            }
        });
    }

    private void createHeatingListener(ViewTransportTyp viewTransportTyp) {

        final Context context = viewTransportTyp.getRoom();
        View view = viewTransportTyp.getView();
        SeekBar seekBar;

        seekBar = (SeekBar) view.findViewById(R.id.HeatingSlider);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(LOG_TAG, "Heating goes to " + seekBar.getProgress() + " in " + context);
            }
        });

    }

    private void createCurtainsListener(ViewTransportTyp viewTransportTyp) {

        final Context context = viewTransportTyp.getRoom();
        View view = viewTransportTyp.getView();
        Button button;

        button = (Button) view.findViewById(R.id.CurtainsOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message message = Messages.createCurtainsOpenMessage(context);

                //debug log
                //Log.d(LOG_TAG, "Curtains open in " + context);
                //Log.d(LOG_TAG, "Message" + message);

                MessageSender.curtainControl(message);
            }
        });

        button = (Button) view.findViewById(R.id.CurtainsClose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Curtains close in " + context);
                Message message = Messages.createCurtainsCloseMessage(context);
                Log.d(LOG_TAG, "Message" + message);
                MessageSender.curtainControl(message);
            }
        });

    }

    private void createBlindsListener(ViewTransportTyp viewTransportTyp) {

        final Context context = viewTransportTyp.getRoom();
        View view = viewTransportTyp.getView();
        Button button;


        button = (Button) view.findViewById(R.id.blindsOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message message = Messages.createBlindsOpenMessage(context);

                //debug log
                //Log.d(LOG_TAG, "blinds open in " + context);
                //Log.d(LOG_TAG, "Message" + message);

                MessageSender.blindsControl(message);
            }
        });

        button = (Button) view.findViewById(R.id.blindsClose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "blinds close in " + context);
                Message message = Messages.createBlindsCloseMessage(context);
                Log.d(LOG_TAG, "Message" + message);
                MessageSender.blindsControl(message);
            }
        });

    }

    private void createLighListener(ViewTransportTyp viewTransportTyp) {

        Button button;
        SeekBar seekBar;

        final View view = viewTransportTyp.getView();
        final Paint mPaint = new Paint();
        final Context context = viewTransportTyp.getRoom();


        button = (Button) view.findViewById(R.id.WhiteLightOn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message message = Messages.createLightOnMessage(context);

                //debug log
                Log.d(LOG_TAG, "whitelight on in " + context);
                Log.d(LOG_TAG, "Message" + message);

                MessageSender.lightControl(message);

            }
        });

        button = (Button) view.findViewById(R.id.WhiteLightOff);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Message message = Messages.createLightOffMessage(context);

                //debug
                //Log.d(LOG_TAG, "whitelight off in " + context);
                //Log.d(LOG_TAG, "Message" + message);

                MessageSender.lightControl(message);

            }
        });

        seekBar = (SeekBar) view.findViewById(R.id.dimmbar);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                Message message = Messages.createLightIntesityMessage(context, seekBar.getProgress());

                //debug log
                //Log.d(LOG_TAG, "whitelight dim to " + seekBar.getProgress() + " in " + context);
                //Log.d(LOG_TAG, "Message" + message);

                MessageSender.lightControl(message);
            }
        });

        final TextView colorPreview = (TextView) view.findViewById(R.id.ColorPreview);
        button = (Button) view.findViewById(R.id.CallColorPicker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialog colorPickerDialog = new ColorPickerDialog(view.getContext() ,new ColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void colorChanged(int color) {
                        colorPreview.setBackgroundColor(color);
                        int red = Color.red(color);
                        int green = Color.green(color);
                        int blue = Color.blue(color);

                        Message message = Messages.createColorLightMessage(context,red,green,blue);

                        //debug log
                        //Log.d(LOG_TAG,"Color changGGGE event");
                        // Log.d(LOG_TAG,"Message :" + message);
                        MessageSender.lightControl(message);

                    }
                },mPaint.getColor());
                colorPickerDialog.show();

            }
        });
    }


}
