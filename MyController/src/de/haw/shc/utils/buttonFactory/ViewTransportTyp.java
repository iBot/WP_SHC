package de.haw.shc.utils.buttonFactory;

import android.view.View;
import de.haw.shc.utils.context.Context;
import de.haw.shc.utils.control.Control;

/**
 * Created with IntelliJ IDEA.
 * User: Nils Feyerabend
 * Date: 21.12.12
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public class ViewTransportTyp {

    private Context room;
    private Control control;
    private View view;

    public ViewTransportTyp(Context room, Control control, View view){
        this.room = room;
        this.control = control;
        this.view = view;

    }

    public Context getRoom() {
        return room;
    }

    public Control getControl() {
        return control;
    }

    public View getView() {
        return view;
    }
}
