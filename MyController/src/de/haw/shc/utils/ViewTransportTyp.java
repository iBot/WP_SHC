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
    private Control control;
    private View view;

    //private String name;


    public ViewTransportTyp(Context context, Control control, View view){
        this.context = context;
        this.control = control;
        this.view = view;
       //this.name = name;

    }

    public Context getContext() {
        return context;
    }

    public Control getControl() {
        return control;
    }

    public View getView() {
        return view;
    }
}
