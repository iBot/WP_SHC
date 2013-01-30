package de.haw.shc.utils.buttonFactory;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import de.haw.shc.R;
import de.haw.shc.utils.colorPicker.ColorPickerDialog;
import de.haw.shc.utils.context.Room;
import de.haw.shc.utils.messageAdapter.Message;
import de.haw.shc.utils.messageAdapter.MessageSender;
import de.haw.shc.utils.messageAdapter.Messages;

import java.io.Serializable;
import java.util.Collection;

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

        final Room room = viewTransportTyp.getRoom();
        View view = viewTransportTyp.getView();
        Button button;


        button = (Button) view.findViewById(R.id.windowOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Messages.createWindowOpenMessage(room);

                //debug log
                Log.d(LOG_TAG, "Open windows in " + room);
                Log.d(LOG_TAG, "Message" + message);

                MessageSender.windowControl(message);
            }
        });


        button = (Button) view.findViewById(R.id.windowClose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message message = Messages.createWindowCloseMessage(room);

                //debug log
                Log.d(LOG_TAG, "Close windows in " + room);
                Log.d(LOG_TAG, "Message" + message);

                MessageSender.windowControl(message);
            }
        });
    }

    private void createHeatingListener(ViewTransportTyp viewTransportTyp) {

        final Room room = viewTransportTyp.getRoom();
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
                Log.d(LOG_TAG, "Heating goes to " + seekBar.getProgress() + " in " + room);
            }
        });

    }

    private void createCurtainsListener(ViewTransportTyp viewTransportTyp) {

        final Room room = viewTransportTyp.getRoom();
        View view = viewTransportTyp.getView();
        Button button;

        button = (Button) view.findViewById(R.id.CurtainsOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //debug log
                Log.d(LOG_TAG, "Curtains open in " + room);
                if (room == Room.ALL) {
                    Collection<Message> messages = Messages.createAllCurtainsOpen();
                    Log.d(LOG_TAG, "Messages: " + messages);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createCurtainsOpenMessage(room);
                    Log.d(LOG_TAG, "Message" + message);

                    MessageSender.curtainControl(message);
                }
            }
        });

        button = (Button) view.findViewById(R.id.CurtainsClose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Curtains close in " + room);
                if (room == Room.ALL) {
                    Collection<Message> messages = Messages.createAllCurtainsClose();
                    Log.d(LOG_TAG, "Messages: " + messages);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createCurtainsCloseMessage(room);
                    Log.d(LOG_TAG, "Message" + message);
                    MessageSender.curtainControl(message);
                }
            }
        });

    }

    private void createBlindsListener(ViewTransportTyp viewTransportTyp) {

        final Room room = viewTransportTyp.getRoom();
        View view = viewTransportTyp.getView();
        Button button;


        button = (Button) view.findViewById(R.id.blindsOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //debug log
                Log.d(LOG_TAG, "blinds open in " + room);

                if (room == Room.ALL) {
                    Collection<Message> messages = Messages.createAllBlindsOpenMessages();
                    Log.d(LOG_TAG, "Messages: " + messages);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createBlindsOpenMessage(room);
                    Log.d(LOG_TAG, "Message: " + message);
                    MessageSender.blindsControl(message);
                }


            }
        });

        button = (Button) view.findViewById(R.id.blindsClose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //debug log
                Log.d(LOG_TAG, "blinds close in " + room);

                if (room == Room.ALL) {
                    Collection<Message> messages = Messages.createAllBlindsCloseMessages();
                    Log.d(LOG_TAG, "Messages: " + messages);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createBlindsCloseMessage(room);
                    Log.d(LOG_TAG, "Message" + message);
                    MessageSender.blindsControl(message);
                }
            }
        });

    }

    private void createLighListener(ViewTransportTyp viewTransportTyp) {

        Button button;
        SeekBar seekBar;

        final View view = viewTransportTyp.getView();
        final Paint mPaint = new Paint();
        final Room room = viewTransportTyp.getRoom();


        button = (Button) view.findViewById(R.id.WhiteLightOn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //debug log
                Log.d(LOG_TAG, "whitelight on in " + room);


                if (room == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightOnMessage();
                    Log.d(LOG_TAG, "Messages" + messages);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createLightOnMessage(room);
                    Log.d(LOG_TAG, "Message" + message);
                    MessageSender.lightControl(message);
                }

            }
        });

        button = (Button) view.findViewById(R.id.WhiteLightOff);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //debug
                Log.d(LOG_TAG, "whitelight off in " + room);
                if (room == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightOffMessage();
                    Log.d(LOG_TAG, "Messages" + messages);
                    MessageSender.messageBatch(messages);
                } else {

                    Message message = Messages.createLightOffMessage(room);
                    Log.d(LOG_TAG, "Message" + message);
                    MessageSender.lightControl(message);
                }
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
                //debug log
                Log.d(LOG_TAG, "whitelight dim to " + seekBar.getProgress() + " in " + room);
                if (room == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightIntesityMessage(seekBar.getProgress());
                    Log.d(LOG_TAG, "Messages: " + messages);
                    MessageSender.messageBatch(messages);
                } else {

                    Message message = Messages.createLightIntesityMessage(room, seekBar.getProgress());
                    Log.d(LOG_TAG, "Message: " + message);
                    MessageSender.lightControl(message);
                }
            }
        });

        final TextView colorPreview = (TextView) view.findViewById(R.id.ColorPreview);
        button = (Button) view.findViewById(R.id.CallColorPicker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialog colorPickerDialog = new ColorPickerDialog(view.getContext(), new ColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void colorChanged(int color) {
                        colorPreview.setBackgroundColor(color);
                        int red = Color.red(color);
                        int green = Color.green(color);
                        int blue = Color.blue(color);


                        //                        debug log
                        Log.d(LOG_TAG, "Color changGGGE event");
                        if (room == Room.ALL) {
                            Collection<Message> messages = Messages.createAllLightColorMessages(red, green, blue);
                            MessageSender.messageBatch(messages);
                        } else {
                            Message message = Messages.createColorLightMessage(room, red, green, blue);
                            Log.d(LOG_TAG, "Message :" + message);
                            MessageSender.lightControl(message);
                        }


                    }
                }, mPaint.getColor());
                colorPickerDialog.show();

            }
        });
    }


}
