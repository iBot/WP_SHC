package de.haw.shc.utils.messageAdapter;

import android.os.AsyncTask;
import android.util.Log;
import de.haw.publisher.AndroidPublisher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

import static de.haw.shc.utils.messageAdapter.Values.*;

public class MessageSender {

    private static final String LOGTAG = "MessageSender";

    private static final MessageSender messageSender = new MessageSender();

    private MessageSender() {
    }

    public static final void messageBatch(Collection<Message> messages) {
        Collection<Message> messagesToSend = new ArrayList<Message>(messages);
        for (Message msg : messagesToSend) {
            if (msg instanceof BlindsMessage) {
                blindsControl((BlindsMessage)msg);
            } else if (msg instanceof CurtainMessage) {
                curtainControl((CurtainMessage)msg);
            } else if (msg instanceof HeatingMessage) {
                heatingControl((HeatingMessage)msg);
            } else if (msg instanceof LightMessage) {
                lightControl((LightMessage)msg);
            } else if (msg instanceof WindowMessage) {
                windowControl((WindowMessage)msg);
            } else {
                Log.w(LOGTAG, msg.getClass().getName() + " is not supported by MessageSender!");
            }
        }
    }

    public static final void lightControl(LightMessage message) {
        MessageSender.Sender sender = messageSender.new Sender();
        sender.doInBackground(IP, PORT, message.getTopic(), TOPIC, message.getContent());
    }

    public static final void windowControl(WindowMessage message) {
        MessageSender.Sender sender = messageSender.new Sender();
        sender.doInBackground(IP, PORT, message.getTopic(), TOPIC, message.getContent());
    }

    public static final void curtainControl(CurtainMessage message) {
        MessageSender.Sender sender = messageSender.new Sender();
        sender.doInBackground(IP, PORT, message.getTopic(), TOPIC, message.getContent());
    }

    public static final void heatingControl(HeatingMessage message) {
        MessageSender.Sender sender = messageSender.new Sender();
        sender.doInBackground(IP, PORT, message.getTopic(), TOPIC, message.getContent());
    }

    public static final void blindsControl(BlindsMessage message) {
        MessageSender.Sender sender = messageSender.new Sender();
        sender.doInBackground(IP, PORT, message.getTopic(), TOPIC, message.getContent());
    }

    private class Sender extends AsyncTask<String, Void, String> {

        Sender() {
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                AndroidPublisher publisher = new AndroidPublisher(params[0],
                        Integer.valueOf(params[1]), params[2]);
                publisher.setMessage(params[4]);
                if (params[3].equals("topic")) {
                    publisher.publishToTopic();
                } else {
                    publisher.publishToQueue();
                }
            } catch (IOException e) {
                Log.e(LOGTAG, "Can't publish the message");
            }
            return null;
        }
    }
}
