package com.example.shc;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import de.hawhamburg.livingplace.messaging.android.AndroidPublisher;

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
    
    public void onClickButtonDinningRed(View view){
    	//System.out.println("-OOOOOOOOOOOOOON");
    	//String msg = "{\"action\":\"dining_light_color\",\"values\":{\"red\":\"255\",\"green\":\"0\",\"blue\":\"128\",\"fadeTime\":\"2\"},\"Id\":\"NFTM-SHC-Client\",\"Version\":null}";
    	Map<String,String> valueMap =  new HashMap<String,String>();
    	valueMap.put("red","255");
    	valueMap.put("green","0");
    	valueMap.put("blue","128");
    	valueMap.put("fadeTime","0");
    	JSONObject valueJSONObj = new JSONObject(valueMap); 
    	
    	Map<String,Object> aendernMap = new HashMap<String, Object>();
    	aendernMap.put("values", valueJSONObj);
    	aendernMap.put("Id", "client_1578909153");
    	aendernMap.put("Version", 9001);
    	aendernMap.put("action", "dining_light_color");
//    	aendernMap.put("anAry", jsoa );
    	
    	JSONObject msgFarbeEsszimmerAendern = new JSONObject(aendernMap);
    	
    	Log.d("FirstTest", msgFarbeEsszimmerAendern.toString());
    	
    	String[] params = new String[5];
    	params[0] = "172.16.0.200";
    	params[1] = "12349";
    	params[2] = "LP.LIGHTCONTROL";
    	params[3] = "Topic";
    	params[4] = msgFarbeEsszimmerAendern.toString();
    	
    	//(new SendMessageToProxy()).doInBackground("172.16.0.200","12349","LP.LIGHTCONTROL","Topic",msgFarbeEsszimmerAendern.);
    	SendMessageToProxy sendMessage = new SendMessageToProxy();
    	
    	sendMessage.execute(params);	
    }
    
    public void lightAllOn(View view){
 	  	
    	Map<String,Object> aendernMap = new HashMap<String, Object>();
    	aendernMap.put("values", "");
    	aendernMap.put("Id", "client_1578909153");
    	aendernMap.put("Version", 9001);
    	aendernMap.put("action", "kitchen_main_light_on");
//    	aendernMap.put("anAry", jsoa );
    	
    	JSONObject msgFarbeEsszimmerAendern = new JSONObject(aendernMap);
    	
    	Log.d("FirstTest", msgFarbeEsszimmerAendern.toString());
    	
    	String[] params = new String[5];
    	params[0] = "172.16.0.200";
    	params[1] = "12349";
    	params[2] = "LP.LIGHTCONTROL";
    	params[3] = "Topic";
    	params[4] = msgFarbeEsszimmerAendern.toString();
    	
    	//(new SendMessageToProxy()).doInBackground("172.16.0.200","12349","LP.LIGHTCONTROL","Topic",msgFarbeEsszimmerAendern.);
    	SendMessageToProxy sendMessage = new SendMessageToProxy();
    	
    	sendMessage.execute(params);	
    	
    }
    
    public void lightAllOff(View view){
    	Map<String,Object> aendernMap = new HashMap<String, Object>();
    	aendernMap.put("values", "");
    	aendernMap.put("Id", "client_1578909153");
    	aendernMap.put("Version", 5);
    	aendernMap.put("action", "kitchen_main_light_off");
//    	aendernMap.put("anAry", jsoa );
    	
    	JSONObject msgFarbeEsszimmerAendern = new JSONObject(aendernMap);
    	
    	Log.d("FirstTest", msgFarbeEsszimmerAendern.toString());
    	
    	String[] params = new String[5];
    	params[0] = "172.16.0.200";
    	params[1] = "12349";
    	params[2] = "LP.LIGHTCONTROL";
    	params[3] = "Topic";
    	params[4] = msgFarbeEsszimmerAendern.toString();
    	
    	//(new SendMessageToProxy()).doInBackground("172.16.0.200","12349","LP.LIGHTCONTROL","Topic",msgFarbeEsszimmerAendern.);
    	SendMessageToProxy sendMessage = new SendMessageToProxy();
    	
    	sendMessage.execute(params);	
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
