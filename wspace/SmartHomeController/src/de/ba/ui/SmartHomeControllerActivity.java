/**************************************************
 * @mainpage Generic Smart Home Controller 
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 ***************************************************/
package de.ba.ui;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

//import android.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import de.ba.datenstructure.Message;
import de.ba.datenstructure.MyTree;
import de.ba.datenstructure.Node;
import de.ba.datenstructure.NodeProperties;
import de.ba.helper.Filewalker;
import de.ba.helper.JsonHelper;
import de.ba.mockup.Mockup;
import de.ba.uielements.ElementGroup;
import de.hawhamburg.livingplace.messaging.android.AndroidPublisher;

/**
 * @class Main Class extends Android Activity Class and Implements Fragment
 *        Communication Interface to exchange data with Fragments
 * @see FragmentComunicationIF
 * @see Activity
 * */
public class SmartHomeControllerActivity extends Activity implements
		FragmentComunicationIF {

	public static Context appContext;
	public static Activity mainActivity;
	private MyTree<NodeProperties> liste;
	private LinearLayout controlarea;
	private TextView controlareatext;
	private Node<String, NodeProperties> pointer;
	private LinkedList<File> plugins;
	private LinkedList<TabContext> tabContextList;
	private TabContext selectedTab;
	public AndroidPublisher ap;

	/**
	 * Called when the activity is first created or restarted. For Example by
	 * the Change of Screen Orientation. Control Area will be create here,
	 * Plugins will be read from External Storage on first start or restored
	 * from Internal Storage if it isn't first start
	 * 
	 * @param savedInstanceState
	 *            contains the saved Tab-Number
	 * */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("changed");
		super.onCreate(savedInstanceState);
		// HauptLayout aus der xml laden
		
		setContentView(R.layout.main);
		appContext = getApplicationContext();
		mainActivity = this;
		// Alle Tabs/Plugins werden in einer Liste verwaltet
		tabContextList = new LinkedList<TabContext>();
		// Layout und Textfeld des Areas mit den Bedienelementen laden
		controlarea = (LinearLayout) findViewById(R.id.controlarea_linearlayout);
		controlareatext = (TextView) findViewById(R.id.controlarea_textView);

		// wenn die Methode das 1 mal aufgerufen wird, wird die Methode setup
		// aufgerufen
		// sonst wird der vorheriger Zustand wiederherrgestellt
		if (savedInstanceState != null) {
			restore();
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt("tab"));

		} else {
			setup(Environment.getExternalStorageDirectory()
					+ "/SmartHomeController/Plugins/");
		}
		// Nachdem die Plugins geladen wurden ist der Navigations Baum und
		// Pointer Node des aktuellen Plugins verfügbar
		this.liste = selectedTab.getTree();
		this.pointer = liste.getNode(selectedTab.getPointer());

	}

	/**
	 * This Method will be call after some Tab was selected. The View will be
	 * refreshed.
	 * 
	 * @see void setNewPointer(Node<String, NodeProperties>)
	 * */
	public void refresh() {
		this.liste = selectedTab.getTree();
		this.pointer = liste.getNode(selectedTab.getPointer());
		this.setNewPointer(pointer);
	}

	/**
	 * This Method will be call once on program starts. ActionBar will be
	 * created here and also filled with TabContext. TabContext contains
	 * information from readed plugins from /sdcard/SmartHomeController/Plugins.
	 * The InitState of all needed controlled Items will be checked here out.
	 * Now it just works with some mockup.
	 * 
	 * @param path
	 *            Path to the plugin directory. Default:
	 *            "/sdcard/SmartHomeController/Plugins"
	 * 
	 * */

	public void setup(String path) {
		// ActionBar erstellen
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionbar.setTitle(R.string.app_name);
		LinkedList<ActionBar.Tab> tabs = new LinkedList<ActionBar.Tab>();

		// Lese alle Plugins ein und packe in die Liste
		plugins = Filewalker.findPluginFiles(path);
		for (int i = 0; i < plugins.size(); i++) {

			TabContext tc = JsonHelper.readListFromJsonFile(JsonHelper
					.readFileFromExternalStorage(plugins.get(i)
							.getAbsolutePath()));

			this.pointer = tc.getTree().getRootNode();

			// Mockup nach dem InitState fragen und die Bedienelemente
			// entsprechend aktualisieren
			Mockup mock = new Mockup();
			String[] args;
			for (NodeProperties np : tc.getTree().getMap().values()) {
				for (ElementGroup egitem : np.getElementGroupList()) {
					args = mock.getState(egitem.getLPlabel());
					if (args != null) {
						for (int y = 0; y < egitem.getAllControls().size(); y++) {
							if (args[y] != null) {
								egitem.getAllControls().get(y)
										.setState(args[y]);
							}
						}
					}
				}
			}
			// Tabs, TabNamen setzen
			tabs.add(i, actionbar.newTab().setText(tc.getTabName()));
			// neues Fragment dem TabContext hinzufügen
			tc.setFragment(new NavigationListFragment());
			// einen Listener an die Tabs verbinden
			tabs.get(i).setTabListener(
					new MyTabsListener<Fragment>(tc.getFragment()));
			// Ausgelesenes und befülltes Tabcontext in die Liste hinzufügen
			tabContextList.add(i, tc);
		}
		// Tabs an die Actionbar heften
		for (Tab tab : tabs) {
			actionbar.addTab(tab);
		}
	}

	/**
	 * The behavior of restore() is like setup(String path). The different is
	 * the directory where the program search for plugin files. Here is this
	 * Internal Storage, where the actual state of each plugin was saved.
	 * 
	 * @see void setup(String path)
	 * */
	public void restore() {

		// ActionBar
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionbar.setTitle(R.string.app_name);
		LinkedList<ActionBar.Tab> tabs = new LinkedList<ActionBar.Tab>();

		LinkedList<String> savedPlugins = Filewalker.findPluginFilePrivate();
		for (int i = 0; i < savedPlugins.size(); i++) {

			TabContext tc = JsonHelper.readListFromJsonFile(JsonHelper
					.readFileFromInternalStorage(savedPlugins.get(i)));
			this.pointer = tc.getTree().getNode(tc.getPointer());
			tabs.add(i, actionbar.newTab().setText(tc.getTabName()));
			tc.setFragment(new NavigationListFragment());
			tabs.get(i).setTabListener(
					new MyTabsListener<Fragment>(tc.getFragment()));
			tabContextList.add(i, tc);
		}
		for (Tab tab : tabs) {
			actionbar.addTab(tab);
		}

	}

	/**
	 * 
	 * */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	/***/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// case R.id.menuitem_search:
		// Toast.makeText(appContext, "search", Toast.LENGTH_SHORT).show();
		// return true;
		// case R.id.menuitem_add:
		// Toast.makeText(appContext, "add", Toast.LENGTH_SHORT).show();
		// return true;
		// case R.id.menuitem_share:
		// Toast.makeText(appContext, "share", Toast.LENGTH_SHORT).show();
		// return true;
		case R.id.menuitem_feedback:
			Toast.makeText(appContext, "feedback", Toast.LENGTH_SHORT).show();
			// JsonHelper.writeListAsJsonFile(selectedTab);
			return true;
		case R.id.menuitem_about:
			Toast.makeText(appContext, "about", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menuitem_quit:
			Toast.makeText(appContext, "quit", Toast.LENGTH_SHORT).show();
			finish();
			return true;
		}
		return false;
	}

	/***/
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// hier den Zustand der Plugins speichern, sowie die Nummer des
		// aktuellen Tabs
		outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
		for (int i = 0; i < getActionBar().getTabCount(); i++)
			JsonHelper.writeListAsJsonFile(this.tabContextList.get(i));
	}

	/***/
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (isFinishing()) {
			// Toast.makeText(appContext, "Bis denn also...",
			// Toast.LENGTH_SHORT)
			// .show();
			// Internal Storage von temporären Dateien bereinigen
			Filewalker.clearInternalTemp();
		}
	}

	/**
	 * getter for Pointer Node
	 * 
	 * @return Node<String, NodeProperties>
	 * */
	public Node<String, NodeProperties> getPointer() {
		return this.pointer;
	}

	/**
	 * getter for Fragment Comunication Interface
	 * 
	 * @returns FragmentComunicationIF
	 * */
	public FragmentComunicationIF getIF() {
		return this;
	}

	/***/
	@Override
	public void setNewPointer(Node<String, NodeProperties> pointer) {
		// wenn root Knoten gewählt ist und keine Bedienelemente in diesem
		// vorhanden sind, lösche das Bedienfeld
		if (pointer.isRoot()) {
			if (pointer.getData() == null) {
				controlarea.removeAllViews();
			}
		}
		// Wenn Knoten auf den Pointer zeigt, Bedienelemente enthält
		// wird der Pfad in der Anzeige neu gesetzt, pointer aktualisiert, sowie
		// neue
		// Bedienelemente gemalt.
		// Falls der Knoten keine Daten enthällt, soll die alte Anzeige bleiben
		if (pointer.getData() != null) {
			if (!pointer.getData().getElementGroupList().isEmpty()) {
				controlareatext.setText(liste.getFullPath(pointer));
				controlarea.removeAllViews();
				this.pointer = pointer;
				this.selectedTab.setPointer(liste.getFullPath(pointer));
				for (ElementGroup item : pointer.getData()
						.getElementGroupList()) {
					this.controlarea.addView((LinearLayout) item);
				}
			}
		}
	}

	/***/
	@Override
	public void statusChanged(Message msg) {
		String[] params = new String[5];
		params[0] = selectedTab.getServer();
		params[1] = Integer.toString(selectedTab.getPort());
		params[2] = msg.getTopic();
		params[3] = msg.getTyp();
		params[4] = msg.getMessage().toString();

		sendMessageToProxy smtp = new sendMessageToProxy();
		smtp.execute(params);

	}

	/**
	 * @class sendMessageToProxy: Internal class of SmartHomeControllerActivity
	 *        that implements AsyncTask for creating socket connction to Android
	 *        ActiveMQ Proxy and to send Message to it. AsyncTask is neded for
	 *        non-block of main/ui-Thread
	 * */
	private class sendMessageToProxy extends AsyncTask<String, Void, String> {

		/**
		 * Creates instance of AndroidPublisher publisher and publish the
		 * massage to queue or topic
		 * 
		 * @param params
		 *            [0] Server; [1] Port; [2] TopicName; [3] topic/queue; [4]
		 *            massage;
		 * */
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

	/**
	 * Tab Listener for Actionbar
	 * */
	private class MyTabsListener<T extends Fragment> implements
			ActionBar.TabListener {

		public T fragment;

		/**
		 * Constructor
		 * 
		 * @param Fragment
		 * */
		public MyTabsListener(T fragment) {
			this.fragment = fragment;
		}

		/***/
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// Toast.makeText(SmartHomeControllerActivity.appContext,
			// "Reselected!", Toast.LENGTH_LONG).show();
		}

		/***/
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace(R.id.fragment_container, fragment);
			selectedTab = tabContextList.get(tab.getPosition());
			refresh();
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);
			selectedTab.setPointer(liste.getFullPath(pointer));
			Log.d("Pointer to save", liste.getFullPath(pointer));
			JsonHelper.writeListAsJsonFile(selectedTab);
		}

	}

}
