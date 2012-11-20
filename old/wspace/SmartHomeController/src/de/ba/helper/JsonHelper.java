/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 ***************************************************/
package de.ba.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import de.ba.ui.R;
import android.os.Environment;
import android.util.Log;
import de.ba.datenstructure.Message;
import de.ba.datenstructure.MyTree;
import de.ba.datenstructure.NodeProperties;
import de.ba.ui.FragmentComunicationIF;
import de.ba.ui.SmartHomeControllerActivity;
import de.ba.ui.TabContext;
import de.ba.uielements.ControlFactory;
import de.ba.uielements.ControlItemIF;
import de.ba.uielements.ElementGroup;

/**
 * JsonHelper contains a static helper functions to read and write JSON-Files
 * from/to external or internal storage. JsonHelper converts JSON to data
 * structure and data structure to JSON
 * */
public class JsonHelper {

	/**
	 * Reads JSON File and convert it to data structure
	 * 
	 * @param raededFile
	 *            JSON File
	 * @return data structure as TabContext
	 * @see TabContext
	 * */
	public static TabContext readListFromJsonFile(String readedFile) {
		TabContext tc = new TabContext();
		MyTree<NodeProperties> liste = new MyTree<NodeProperties>("LP");

		NodeProperties tmpNP = new NodeProperties();
		ElementGroup tmpEG = new ElementGroup(
				SmartHomeControllerActivity.appContext,
				(FragmentComunicationIF) SmartHomeControllerActivity.mainActivity);
		Message tmpMsg = new Message();
		String key;
		ControlItemIF cntrItem = null;

		try {
			JSONObject jsonObject = new JSONObject(readedFile);
			tc.setTabName(jsonObject
					.getString(getSysString(R.string.plugin_name)));
			tc.setPointer(jsonObject
					.getString(getSysString(R.string.plugin_pointer)));
			tc.setServer(jsonObject
					.getString(getSysString(R.string.plugin_server)));
			tc.setPort(jsonObject.getInt(getSysString(R.string.plugin_port)));

			JSONArray jsonArray = jsonObject
					.getJSONArray(getSysString(R.string.main_array));

			JSONObject item, controlitem, nachricht, groupitem;
			JSONArray controls, group, arguments;

			for (int i = 0; i < jsonArray.length(); i++) {
				// Holle Control Objekt aus dem JSON-Array
				item = jsonArray.getJSONObject(i);
				// Path
				key = item.getString(getSysString(R.string.location_path));
				// Holle Array mit Bedienelementen(Buttons, Slider etc.)
				controls = item
						.getJSONArray(getSysString(R.string.controls_array));
				for (int x = 0; x < controls.length(); x++) {
					// Nehme Bedienelement aus dem Array
					controlitem = controls.getJSONObject(x);
					// Nehme die Eigenschaft des Bedienelementes
					controlitem = controlitem
							.getJSONObject(getSysString(R.string.control_item));

					group = controlitem
							.getJSONArray(getSysString(R.string.group_array));

					for (int z = 0; z < group.length(); z++) {
						groupitem = group.getJSONObject(z);
						cntrItem = ControlFactory
								.getControlItem(
										groupitem
												.getString(getSysString(R.string.group_item_typ)),
										SmartHomeControllerActivity.appContext);

						// ArgumentenArray befühlen
						arguments = groupitem
								.getJSONArray(getSysString(R.string.group_item_argument_array));
						String[] args = new String[arguments.length()];
						for (int y = 0; y < arguments.length(); y++) {
							args[y] = (String) arguments.getString(y);
						}
						cntrItem.setArguments(args);

						cntrItem.setName(groupitem
								.getString(getSysString(R.string.group_item_title)));

						tmpEG.addElement(cntrItem);
						cntrItem = null;
					}
					tmpEG.setGroupName(controlitem
							.getString(getSysString(R.string.group_title)));
					tmpEG.setLPlabel(controlitem
							.getString(getSysString(R.string.lp_label)));

					// Holle das Nachrichten Objekt und befühle es mit
					// Attributen
					nachricht = controlitem
							.getJSONObject(getSysString(R.string.message));
					tmpMsg.setTopic(nachricht
							.getString(getSysString(R.string.message_topic)));
					tmpMsg.setTyp(nachricht
							.getString(getSysString(R.string.message_typ)));
					tmpMsg.setMessage(nachricht.getJSONObject(
							getSysString(R.string.message_value)).toString());

					// und füge es der Eigenschaft zu
					tmpEG.setMessage(tmpMsg);
					// Füge das Bedienelement einer Liste zu
					tmpNP.addItemToList(tmpEG);
					tmpEG = new ElementGroup(
							SmartHomeControllerActivity.appContext,
							(FragmentComunicationIF) SmartHomeControllerActivity.mainActivity);
					tmpMsg = new Message();
				}
				// Füge die Liste mit Bedienelementen (Eigenschaften) einer
				// Liste zu
				liste.addNode(key, tmpNP);
				for (ElementGroup ci : tmpNP.getElementGroupList()) {
					ci.createIt(SmartHomeControllerActivity.appContext);
				}
				tmpNP = new NodeProperties();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("onCreate", "String can't be parsed!");
		}

		// // //Erstelle alle Grafischen Objekte
		// for (NodeProperties np : liste.getAllValues()) {
		// for (ElementGroup ci : np.getList()) {
		// ci.createIt(SmartHomeControllerActivity.appContext);
		// }
		// }
		tc.setTree(liste);
		return tc;
	}

	/**
	 * Writs data structure from TabContext as JSON to the storage
	 * 
	 * @param tc
	 *            data structure as TabContext
	 * @see TabContext
	 * */
	public static void writeListAsJsonFile(TabContext tc) {
		HashMap<String, NodeProperties> map = tc.getTree().getMap();
		JSONStringer jStringer = new JSONStringer();

		try {
			jStringer.object().key(getSysString(R.string.plugin_name))
					.value(tc.getTabName())
					.key(getSysString(R.string.plugin_pointer))
					.value(tc.getPointer())
					.key(getSysString(R.string.plugin_server))
					.value(tc.getServer())
					.key(getSysString(R.string.plugin_port))
					.value(tc.getPort()).key(getSysString(R.string.main_array))
					.array();

			for (String pfad : map.keySet()) {
				jStringer.object().key(getSysString(R.string.location_path))
						.value(pfad).key(getSysString(R.string.controls_array))
						.array();

				for (ElementGroup groupitem : map.get(pfad)
						.getElementGroupList()) {
					jStringer.object().key(getSysString(R.string.control_item))
							.object().key(getSysString(R.string.group_title))
							.value(groupitem.getGroupName())
							.key(getSysString(R.string.lp_label))
							.value(groupitem.getLPlabel())
							.key(getSysString(R.string.group_array)).array();
					for (ControlItemIF controlitem : groupitem.getAllControls()) {
						jStringer
								.object()
								.key(getSysString(R.string.group_item_typ))
								.value(controlitem.getTyp())
								.key(getSysString(R.string.group_item_title))
								.value(controlitem.getName())
								.key(getSysString(R.string.group_item_argument_array))
								.value(new JSONArray(Arrays.asList(controlitem
										.getArguments()))).endObject();
					}
					jStringer
							.endArray()
							.key(getSysString(R.string.message))
							.object()
							.key(getSysString(R.string.message_topic))
							.value(groupitem.getMessage().getTopic())
							.key(getSysString(R.string.message_typ))
							.value(groupitem.getMessage().getTyp())
							.key(getSysString(R.string.message_value))
							.value(new JSONObject(groupitem.getMessage()
									.getMessage())).endObject().endObject()
							.endObject();
				}
				jStringer.endArray().endObject();
			}

			jStringer.endArray().endObject();
		} catch (JSONException e) {
			Log.e("Error", "Can't create jString");
		}
		// writeTextFileToExternalStorage(jStringer.toString(),
		// tc.getTabName()+".json");
		writeTextFileToInternalStorage(jStringer.toString(), tc.getTabName()
				+ ".json");
	}

	/**
	 * Shorts the notation of getString
	 * 
	 * @param id
	 *            ID from values/string.xml
	 * @see getString
	 * */
	private static String getSysString(int id) {
		return SmartHomeControllerActivity.mainActivity.getString(id);
	}

	/**
	 * Reads files from external storage and returns it as JSON-String
	 * 
	 * @param pfad
	 *            Path to file / File name
	 * @return readed file as JSON-String
	 * */
	public static String readFileFromExternalStorage(String pfad) {
		StringBuilder builder = new StringBuilder();

		try {
			FileInputStream in = new FileInputStream(pfad);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));

			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

		} catch (FileNotFoundException e) {
			Log.e("readFile", "File not found");

		} catch (IOException e) {
			Log.e("readFile", "IO Exception");
		}
		return builder.toString();
	}

	/**
	 * Reads files from internal storage and returns it as JSON-String
	 * 
	 * @param pfad
	 *            Path to file / File name
	 * @return readed file as JSON-String
	 * */
	public static String readFileFromInternalStorage(String name) {
		StringBuilder buf = null;
		InputStream in = null;
		try {
			in = SmartHomeControllerActivity.appContext.openFileInput(name);
			if (in != null) {
				InputStreamReader tmp = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(tmp);
				String str;
				buf = new StringBuilder();

				while ((str = reader.readLine()) != null) {
					buf.append(str + "\n");
				}
			}
		} catch (IOException e) {
			Log.e("IOException", "File not found!");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				Log.e("IOException", "File can't be closed!");
			}
		}

		return buf.toString();
	}

	/**
	 * Writes the data structure to internal storage
	 * 
	 * @param text
	 *            data structure as JSON-String
	 * @param name
	 *            File name
	 * */
	private static void writeTextFileToInternalStorage(String text, String name) {

		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(
					SmartHomeControllerActivity.appContext.openFileOutput(name,
							0));
			out.write(text);
			out.close();
		} catch (IOException e) {
			Log.e("IOException", "File can't be write!");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				Log.e("IOException", "File can't be closed!");
			}
		}
	}

	/**
	 * @deprecated Writes the data structure to external storage
	 * @param text
	 *            data structure as JSON-String
	 * @param filename
	 *            File name
	 * */
	// Das Schreiben in den Externen Speicher ist Momentan nicht nötig
	@SuppressWarnings("unused")
	private static void writeTextFileToExternalStorage(String text,
			String filename) {
		File f = new File(Environment.getExternalStorageDirectory()
				+ "/SmartHomeController/Temp/" + filename);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(f)));
			writer.write(text);
		} catch (Exception e) {
			Log.e("IOException", "File can't created!");
		} finally {
			if (writer != null) {
				try {
					writer.close();
					Log.d("Success", "File was created!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
