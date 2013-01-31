package de.haw.shc.utils.fileAgent;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: HerpDerp
 * Date: 31.01.13
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class FileAgent extends Activity{

    private String fileName;
    private static String LOGTAG = "FileAgent";

    public FileAgent(){
        fileName = "favoriteColorSettings";
    }

    public void writeColorToFile(int red, int green, int blue) {

        String wirteString = red+":"+green+":"+blue+"\n";
        try {
            FileOutputStream fos = openFileOutput(fileName,Context.MODE_PRIVATE);
              fos.write(wirteString.getBytes());
              fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Log.d(LOGTAG,"write string "+ wirteString + " to file.");
    }

    public  String[] readNextColorFromFile(){
        String[] result;
        FileInputStream fis = null;
        String line = "";
        try {
            fis = openFileInput(fileName);
            BufferedReader bfis = new BufferedReader(new InputStreamReader(fis));
            line =  bfis.readLine();
            bfis.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if(line != null){
         Log.d(LOGTAG,"read line is" + line);
         result = line.split(":");

        return result;
        }

        Log.d(LOGTAG,"reached eof");
        return null;
    }

}
