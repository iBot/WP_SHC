package de.haw.shc.utils.messageAdapter;

import java.io.IOException;

import de.haw.publisher.AndroidPublisher;
import android.os.AsyncTask;
import android.util.Log;
import static de.haw.shc.utils.messageAdapter.Values.*;

public class MessageSender {
	


	
	private static final MessageSender messageSender = new MessageSender();
	
	private static final void lightControl(Message message){
		MessageSender.Sender sender = messageSender.new Sender();
		sender.doInBackground(IP, PORT, message.getTopic(),TOPIC, message.getContent());
	}
	
	private static final void windowControl(Message message){
		MessageSender.Sender sender = messageSender.new Sender();
		sender.doInBackground(IP, PORT, message.getTopic(), TOPIC, message.getContent());
	}
	
	private static final void curtainControl() {
		throw new Error("Not implemented yet!");
	}
	
	private static final void heatingControl() {
		throw new Error("Not implemented yet!");
	}
	
	private MessageSender(){};
	
	private class Sender extends AsyncTask<String, Void, String> {
		
		Sender(){};

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
				Log.e("Publisher", "Can't publish the message");
			}
			return null;
		}
	}
}
