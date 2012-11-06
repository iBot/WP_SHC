/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 ***************************************************/
package de.ba.ui;

//import android.R;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.ba.datenstructure.Node;
import de.ba.datenstructure.NodeProperties;

/**
 * This class is the navigation part of program. It displays navigation tree on
 * the left side of the ui.
 * */
public class NavigationListFragment extends ListFragment implements
		View.OnClickListener {

	private View root;
	private TextView text_button;
	private Node<String, NodeProperties> pointer;
	private FragmentComunicationIF comIF;

	/**
	 * this method gets the child list from pointer node and creates new
	 * ListAdapter that displays a List of entries
	 * */
	private void setItems() {
		String[] tmp;
		if (pointer.hasChildren()) {
			tmp = new String[pointer.getChildListKeySet().size()];
			pointer.getChildListKeySet().toArray(tmp);

		} else {
			tmp = new String[] {};
		}
		ListAdapter adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, tmp);
		setListAdapter(adapter);
	}

	/***/
	// will call when fragment was created
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate layout
		root = inflater.inflate(R.layout.navigationlistfragment, container,
				false);
		// Referenz auf CommunacationFragmentIF und den aktuellen Pointer hollen
		this.comIF = ((SmartHomeControllerActivity) getActivity()).getIF();
		this.pointer = ((SmartHomeControllerActivity) getActivity())
				.getPointer();
		// Text Button ist ein Textfeld, was als zurück button genutzt wird
		text_button = ((TextView) root.findViewById(R.id.my_back_button));
		text_button.setText(pointer.getKey());
		text_button.setOnClickListener(this);
		this.setItems();
		return root;
	}

	/***/
	// ein Eintrag aus der List wurde gewählt
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		pointer = pointer.getChildNode(((TextView) v).getText().toString());
		this.setItems();
		text_button.setText(pointer.getKey());
		comIF.setNewPointer(pointer);
	}

	/***/
	// zurück wurde geclickt
	@Override
	public void onClick(View v) {
		pointer = pointer.getParent();
		this.setItems();
		text_button.setText(pointer.getKey());
		comIF.setNewPointer(pointer);
	}

}
