package de.haw.shc.utils.buttonFactory;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import de.haw.shc.ContextListActivity;
import de.haw.shc.R;
import de.haw.shc.utils.colorPicker.ColorPickerDialog;
import de.haw.shc.utils.context.Room;
import de.haw.shc.utils.control.Control;
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
    private Color color= null;


    //private ViewTransportTyp viewTransportTyp;


    public ButtonListenerFactory() {
        //this.viewTransportTyp = viewTransportTyp;
    }

    public void setListenerForView(View view, Control control, Room room) {

        switch (control) {
            case LIGHT:



                createLighListener(view,room);
                if(! room.equals(Room.CORRIDOR)){
                    createColorLightListener(view,room);
                }

                break;
            case BLINDS:
                createBlindsListener(view,room);
                break;
            case CURTAIN:
                createCurtainsListener(view,room);
                break;
            case HEATING:
                createHeatingListener(view,room);
                break;
            case WINDOW:
                createWindowsListener(view,room);
                break;
        }


    }



    private void createWindowsListener(View view, Room room) {
        final Room mRoom = room;



        Button button;
        button = (Button) view.findViewById(R.id.windowOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Messages.createWindowOpenMessage(mRoom);

                //debug log
                Log.d(LOG_TAG, "Open windows in " + mRoom);
                Log.d(LOG_TAG, "Message" + message);

                MessageSender.windowControl(message);
            }
        });


        button = (Button) view.findViewById(R.id.windowClose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message message = Messages.createWindowCloseMessage(mRoom);

                //debug log
                Log.d(LOG_TAG, "Close windows in " + mRoom);
                Log.d(LOG_TAG, "Message" + message);

                MessageSender.windowControl(message);
            }
        });
    }

    private void createHeatingListener(View view, Room room) {

        final Room mRroom = room;

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
                Log.d(LOG_TAG, "Heating goes to " + seekBar.getProgress() + " in " + mRroom);
            }
        });

    }

    private void createCurtainsListener(View view, Room room) {

        final Room mRoom = room;

        Button button;

        button = (Button) view.findViewById(R.id.CurtainsOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //debug log
                Log.d(LOG_TAG, "Curtains open in " + mRoom);
                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllCurtainsOpen();
                    Log.d(LOG_TAG, "Messages: " + messages);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createCurtainsOpenMessage(mRoom);
                    Log.d(LOG_TAG, "Message" + message);

                    MessageSender.curtainControl(message);
                }
            }
        });

        button = (Button) view.findViewById(R.id.CurtainsClose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Curtains close in " + mRoom);
                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllCurtainsClose();
                    Log.d(LOG_TAG, "Messages: " + messages);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createCurtainsCloseMessage(mRoom);
                    Log.d(LOG_TAG, "Message" + message);
                    MessageSender.curtainControl(message);
                }
            }
        });

    }

    private void createBlindsListener(View view, Room room) {

        final Room mRoom= room;

        Button button;


        button = (Button) view.findViewById(R.id.blindsOpen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //debug log
                Log.d(LOG_TAG, "blinds open in " + mRoom);

                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllBlindsOpenMessages();
                    Log.d(LOG_TAG, "Messages: " + messages);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createBlindsOpenMessage(mRoom);
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
                Log.d(LOG_TAG, "blinds close in " + mRoom);

                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllBlindsCloseMessages();
                    Log.d(LOG_TAG, "Messages: " + messages);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createBlindsCloseMessage(mRoom);
                    Log.d(LOG_TAG, "Message" + message);
                    MessageSender.blindsControl(message);
                }
            }
        });

    }

    private void createLighListener(View view , Room room) {

        Button button;
        SeekBar seekBar;

        final Room mRoom =room;



        button = (Button) view.findViewById(R.id.WhiteLightOn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //debug log
                Log.d(LOG_TAG, "whitelight on in " + mRoom);


                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightOnMessage();
                    Log.d(LOG_TAG, "Messages" + messages);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createLightOnMessage(mRoom);
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
                Log.d(LOG_TAG, "whitelight off in " + mRoom);
                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightOffMessage();
                    Log.d(LOG_TAG, "Messages" + messages);
                    MessageSender.messageBatch(messages);
                } else {

                    Message message = Messages.createLightOffMessage(mRoom);
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
                Log.d(LOG_TAG, "whitelight dim to " + seekBar.getProgress() + " in " + mRoom);
                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightIntesityMessage(seekBar.getProgress());
                    Log.d(LOG_TAG, "Messages: " + messages);
                    MessageSender.messageBatch(messages);
                } else {

                    Message message = Messages.createLightIntesityMessage(mRoom, seekBar.getProgress());
                    Log.d(LOG_TAG, "Message: " + message);
                    MessageSender.lightControl(message);
                }
            }
        });


    }

    private void createColorLightListener(View view, Room room) {
        final View mView = view;
        final Room mRoom = room;
        Button button;
        final Paint mPaint = new Paint();

        TextView tvColorPreview = (TextView) mView.findViewById(R.id.ColorPreview);
        tvColorPreview.setOnTouchListener(new View.OnTouchListener() {
            /**
             * Called when a touch event is dispatched to a view. This allows listeners to
             * get a chance to respond before the target view.
             *
             * @param v     The view the touch event has been dispatched to.
             * @param event The MotionEvent object containing full information about
             *              the event.
             * @return True if the listener has consumed the event, false otherwise.
             */
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d(LOG_TAG, "MotionEvent Action_Down");
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    return true;
                } else {
                    return false;
                }
            }
        });

        TextView tvColorFav1 = (TextView) mView.findViewById(R.id.ColorFav1);
        tvColorFav1.setOnDragListener( new View.OnDragListener(){

            /**
             * Called when a drag event is dispatched to a view. This allows listeners
             * to get a chance to override base View behavior.
             *
             * @param v     The View that received the drag event.
             * @param event The {@link android.view.DragEvent} object for the drag event.
             * @return {@code true} if the drag event was handled successfully, or {@code false}
             *         if the drag event was not handled. Note that {@code false} will trigger the View
             *         to call its {link #onDragEvent(android.view.DragEvent) onDragEvent()} handler.
             */
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                View dragView = (View) event.getLocalState();
                switch (event.getAction()) {
//                    case DragEvent.ACTION_DRAG_STARTED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_STARTED");
//                        break;
//                    case DragEvent.ACTION_DRAG_ENTERED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_ENTERED");
//                        break;
//                    case DragEvent.ACTION_DRAG_EXITED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_EXITED");
//                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d(LOG_TAG, "ACTION_DROP");
                        v.setBackgroundColor(((ColorDrawable)dragView.getBackground()).getColor());
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_ENDED");
//                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        TextView tvColorFav2 = (TextView) mView.findViewById(R.id.ColorFav2);
        tvColorFav2.setOnDragListener( new View.OnDragListener(){

            /**
             * Called when a drag event is dispatched to a view. This allows listeners
             * to get a chance to override base View behavior.
             *
             * @param v     The View that received the drag event.
             * @param event The {@link android.view.DragEvent} object for the drag event.
             * @return {@code true} if the drag event was handled successfully, or {@code false}
             *         if the drag event was not handled. Note that {@code false} will trigger the View
             *         to call its {link #onDragEvent(android.view.DragEvent) onDragEvent()} handler.
             */
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                View dragView = (View) event.getLocalState();
                switch (event.getAction()) {
//                    case DragEvent.ACTION_DRAG_STARTED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_STARTED");
//                        break;
//                    case DragEvent.ACTION_DRAG_ENTERED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_ENTERED");
//                        break;
//                    case DragEvent.ACTION_DRAG_EXITED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_EXITED");
//                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d(LOG_TAG, "ACTION_DROP");
                        v.setBackgroundColor(((ColorDrawable)dragView.getBackground()).getColor());
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_ENDED");
//                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        TextView tvColorFav3 = (TextView) mView.findViewById(R.id.ColorFav3);
        tvColorFav3.setOnDragListener( new View.OnDragListener(){

            /**
             * Called when a drag event is dispatched to a view. This allows listeners
             * to get a chance to override base View behavior.
             *
             * @param v     The View that received the drag event.
             * @param event The {@link android.view.DragEvent} object for the drag event.
             * @return {@code true} if the drag event was handled successfully, or {@code false}
             *         if the drag event was not handled. Note that {@code false} will trigger the View
             *         to call its {link #onDragEvent(android.view.DragEvent) onDragEvent()} handler.
             */
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                View dragView = (View) event.getLocalState();
                switch (event.getAction()) {
//                    case DragEvent.ACTION_DRAG_STARTED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_STARTED");
//                        break;
//                    case DragEvent.ACTION_DRAG_ENTERED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_ENTERED");
//                        break;
//                    case DragEvent.ACTION_DRAG_EXITED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_EXITED");
//                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d(LOG_TAG, "ACTION_DROP");
                        v.setBackgroundColor(((ColorDrawable)dragView.getBackground()).getColor());
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_ENDED");
//                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        TextView tvColorFav4 = (TextView) mView.findViewById(R.id.ColorFav4);
        tvColorFav4.setOnDragListener( new View.OnDragListener(){

            /**
             * Called when a drag event is dispatched to a view. This allows listeners
             * to get a chance to override base View behavior.
             *
             * @param v     The View that received the drag event.
             * @param event The {@link android.view.DragEvent} object for the drag event.
             * @return {@code true} if the drag event was handled successfully, or {@code false}
             *         if the drag event was not handled. Note that {@code false} will trigger the View
             *         to call its {link #onDragEvent(android.view.DragEvent) onDragEvent()} handler.
             */
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                View dragView = (View) event.getLocalState();
                switch (event.getAction()) {
//                    case DragEvent.ACTION_DRAG_STARTED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_STARTED");
//                        break;
//                    case DragEvent.ACTION_DRAG_ENTERED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_ENTERED");
//                        break;
//                    case DragEvent.ACTION_DRAG_EXITED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_EXITED");
//                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d(LOG_TAG, "ACTION_DROP");
                        v.setBackgroundColor(((ColorDrawable)dragView.getBackground()).getColor());
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
//                        Log.d(LOG_TAG, "ACTION_DRAG_ENDED");
//                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        TextView textView = (TextView) mView.findViewById(R.id.ColorFav1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ((ColorDrawable)v.getBackground()).getColor();

                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);


                //debug log
                Log.d(LOG_TAG, "Color changged by Fav-Button-1");
                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightColorMessages(red, green, blue);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createColorLightMessage(mRoom, red, green, blue);
                    Log.d(LOG_TAG, "Message :" + message);
                    MessageSender.lightControl(message);
                }

            }
        });

        textView = (TextView) mView.findViewById(R.id.ColorFav2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ((ColorDrawable)v.getBackground()).getColor();

                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);


                //debug log
                Log.d(LOG_TAG, "Color changged by Fav-Button-2");
                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightColorMessages(red, green, blue);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createColorLightMessage(mRoom, red, green, blue);
                    Log.d(LOG_TAG, "Message :" + message);
                    MessageSender.lightControl(message);
                }

            }
        });

        textView = (TextView) mView.findViewById(R.id.ColorFav3);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ((ColorDrawable)v.getBackground()).getColor();

                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);


                //debug log
                Log.d(LOG_TAG, "Color changged by Fav-Button-3");
                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightColorMessages(red, green, blue);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createColorLightMessage(mRoom, red, green, blue);
                    Log.d(LOG_TAG, "Message :" + message);
                    MessageSender.lightControl(message);
                }

            }
        });

        textView = (TextView) mView.findViewById(R.id.ColorFav4);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ((ColorDrawable)v.getBackground()).getColor();

                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);


                //debug log
                Log.d(LOG_TAG, "Color changged by Fav-Button-4");

                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightColorMessages(red, green, blue);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createColorLightMessage(mRoom, red, green, blue);
                    Log.d(LOG_TAG, "Message :" + message);
                    MessageSender.lightControl(message);
                }

            }
        });

        textView = (TextView) mView.findViewById(R.id.ColorPreview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = ((ColorDrawable) v.getBackground()).getColor();

                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);


                //debug log
                Log.d(LOG_TAG, "Color changged by ColorPreview-Button");
                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightColorMessages(red, green, blue);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createColorLightMessage(mRoom, red, green, blue);
                    Log.d(LOG_TAG, "Message :" + message);
                    MessageSender.lightControl(message);
                }

            }
        });


        final TextView colorPreview = (TextView) mView.findViewById(R.id.ColorPreview);
        button = (Button) mView.findViewById(R.id.CallColorPicker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialog colorPickerDialog = new ColorPickerDialog(mView.getContext(), new ColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void colorChanged(int color) {
                        colorPreview.setBackgroundColor(color);
                        int red = Color.red(color);
                        int green = Color.green(color);
                        int blue = Color.blue(color);


                        //                        debug log
                        Log.d(LOG_TAG, "Color changGGGE event");
                        if (mRoom == Room.ALL) {
                            Collection<Message> messages = Messages.createAllLightColorMessages(red, green, blue);
                            MessageSender.messageBatch(messages);
                        } else {
                            Message message = Messages.createColorLightMessage(mRoom, red, green, blue);
                            Log.d(LOG_TAG, "Message :" + message);
                            MessageSender.lightControl(message);
                        }


                    }
                }, mPaint.getColor());
                colorPickerDialog.show();

            }
        });

        button = (Button)view.findViewById(R.id.ColorButtonOff);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int red = 0x00;
                int green = 0x00;
                int blue = 0x00;


                //debug log
                Log.d(LOG_TAG, "Color changged by ColorPreview-Button");
                if (mRoom == Room.ALL) {
                    Collection<Message> messages = Messages.createAllLightColorMessages(red, green, blue);
                    MessageSender.messageBatch(messages);
                } else {
                    Message message = Messages.createColorLightMessage(mRoom, red, green, blue);
                    Log.d(LOG_TAG, "Message :" + message);
                    MessageSender.lightControl(message);
                }

            }
        });


    }

}
