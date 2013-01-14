package de.haw.shc.utils;

import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: Nils Feyerabend
 * Date: 21.12.12
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public class ViewTransportTyp {

    private Context context;
    private Control[] controls;
    private View view;

    //private String name;


    public ViewTransportTyp(Context context, Control[] controls, View view){
        this.context = context;
        this.controls = controls;
        this.view = view;
       //this.name = name;

    }

    public Context getContext() {
        return context;
    }

    public Control[] getControls() {
        return controls;
    }

    public View getView() {
        return view;
    }
}
