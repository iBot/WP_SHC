package com.example.shc;

import java.io.IOException;

import de.hawhamburg.livingplace.messaging.android.AndroidPublisher;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.*;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
//        private OnClickListener mCorkyListener = new OnClickListener() {
//            public void onClick(View v) {
//              // do something when the button is clicked
//            }
//        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClickButtonOn(View view){
    	System.out.println("-OOOOOOOOOOOOOON");
    	String msg = "{\"action\":\"dining_light_color\",\"values\":{\"red\":\"255\",\"green\":\"0\",\"blue\":\"128\",\"fadeTime\":\"2\"},\"Id\":\"NFTM-SHC-Client\",\"Version\":null}";
    	(new SendMessageToProxy()).doInBackground("172.16.0.200","12349","LP.LIGHTCONTROL","Topic",msg);
    }
    
    public void onClickButtonOff(View view){
    	System.out.println("-OOOOOOOOOOOOOOFF");
    }
    
	private class SendMessageToProxy extends AsyncTask<String, Void, String> {
		// params [0] Server; [1] Port; [2] TopicName; [3] topic/queue;
		// [4] message;
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
