/**************************************************
 * @author Sven Boris Bornemann
 * HAW Hamburg
 * 03.2012 Hamburg
***************************************************/
package de.hawhamburg.livingplace.messaging.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import android.util.Log;

public class AndroidPublisher
{
  private String address;
  private int port;
  private String topicName;
  private String message;

  /**
   * 
   * @param address: IP from Proxy
   * @param port: Port from Proxy
   * @param topicName: Name from Topic or Queue to deliver
   */
  public AndroidPublisher(String address, int port, String topicName)
  {
    this.address = address;
    this.port = port;
    this.topicName = topicName;
  }

  /**
   * 
   * @param message: MSG to deliver
   */
  public void setMessage(String message)
  {
    this.message = message;
  }

  /**
   * Publish to Topic
   * @throws IOException
   */
  public void publishToTopic() throws IOException
  {
	 
    Socket sender = new Socket(this.address, this.port);
    Log.d("SEND", this.address + ":" + Integer.toString(this.port));
    PrintWriter out = new PrintWriter(sender.getOutputStream());
    out.write("topic:" + this.topicName + ":" + this.message);
//    System.out.println("AndroidPublisher sending to proxy: topic:" + this.topicName + ":" + this.message);
    Log.d("AndroidPublisher sending to proxy: topic:", this.topicName + ":" + this.message);
    out.flush();
    sender.close();
  }

  /**
   * Publish to Queue
   * @throws IOException
   */
  public void publishToQueue() throws IOException
  {
	  Log.d("SEND", this.address + ":" + Integer.toString(this.port));
	   
    Socket sender = new Socket(this.address, this.port);

    PrintWriter out = new PrintWriter(sender.getOutputStream());
    out.write("queue:" + this.topicName + ":" + this.message);
//    System.out.println("AndroidPublisher sending to proxy: queue:" + this.topicName + ":" + this.message);
    Log.d("AndroidPublisher sending to proxy: queue:", this.topicName + ":" + this.message);
    out.flush();
    sender.close();
  }
}