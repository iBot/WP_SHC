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

    public static final void messageBatch(Collection<? extends Message> messages) {
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

    private static final void send( String ip, String port, String messageTopic, String queueTopic, String messageContent){
        (messageSender.new Sender()).execute(ip, port, messageTopic,queueTopic, messageContent);
    }

    public static final void lightControl(Message message) {
        send(IP, PORT, message.getTopic(), TOPIC, message.getContent());
    }

    public static final void windowControl(Message message) {
        send(IP, PORT, message.getTopic(), TOPIC, message.getContent());
    }

    public static final void curtainControl(Message message) {
        send(IP, PORT, message.getTopic(), TOPIC, message.getContent());
    }

    public static final void heatingControl(Message message) {
        send(IP, PORT, message.getTopic(), TOPIC, message.getContent());
    }

    public static final void blindsControl(Message message) {
        send(IP, PORT, message.getTopic(), TOPIC, message.getContent());
    }

    private class Sender extends AsyncTask<String, Void, String> {

        Sender() {
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d(LOGTAG, "doInBackground(...) wurde aufgerufen!");
            try {
                AndroidPublisher publisher = new AndroidPublisher(params[0],
                        Integer.valueOf(params[1]), params[2]);
                publisher.setMessage(params[4]);
                if (params[3].equals("topic")) {
                    publisher.publishToTopic();
                } else {
                    publisher.publishToQueue();
                }
                Log.i(LOGTAG, "Message sent...");
            } catch (IOException e) {
                Log.e(LOGTAG, e.toString());
                Log.e(LOGTAG, "Can't publish the message");
            }
            return null;
        }
    }
}
