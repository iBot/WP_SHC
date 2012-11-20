/**************************************************
 * @author Viktor Kolbaja
 * HAW Hamburg
 * BA Smart Home Controller
 * 05.2012 Hamburg
 ***************************************************/
package de.ba.helper;

import java.io.File;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import android.os.Environment;
import de.ba.ui.SmartHomeControllerActivity;

/**
 * Filewalker contains just static functions to search for files in external or
 * internal storage.
 * */
public class Filewalker {

	/**
	 * Searching for plugin files in the external storage:
	 * (/sdcard/SmartHomeController/Plugins/)
	 * 
	 * @param startD
	 *            Directory for start searching
	 * @return List of searched files
	 * */
	public static LinkedList<File> findPluginFiles(String startD) {

		File startDirectory = new File(startD);
		Set<File> foundFiles = new TreeSet<File>();
		Stack<File> files = new Stack<File>();
		files.push(startDirectory);
		while (!files.empty()) {
			File currentDirectory = files.pop();
			for (File currentFile : currentDirectory.listFiles()) {
				if (currentFile.isDirectory()) {
					files.push(currentFile);
				} else {
					if (currentFile.getName().endsWith(".json")) {
						foundFiles.add(currentFile);
					}
				}
			}
		}
		LinkedList<File> tmp = new LinkedList<File>();
		tmp.addAll(foundFiles);
		return tmp;
	}

	/**
	 * Searching for temporary saved files in the internal program storage
	 * 
	 * @return List of searched files
	 * */
	public static LinkedList<String> findPluginFilePrivate() {
		LinkedList<String> tmp = new LinkedList<String>();
		String[] privateDateien = SmartHomeControllerActivity.appContext
				.fileList();
		for (int i = 0; i < privateDateien.length; i++) {
			tmp.add(privateDateien[i]);
		}
		return tmp;
	}

	/**
	 * @deprecated clears the external storage from temporary files
	 * */
	public static void clearExternalTemp() {
		LinkedList<File> files = findPluginFiles(Environment
				.getExternalStorageDirectory() + "/SmartHomeController/Temp/");
		for (File f : files) {
			if (f.exists())
				f.delete();
		}
	}

	/**
	 * clears the internal storage from temporary files
	 * */
	public static void clearInternalTemp() {
		for (String s : SmartHomeControllerActivity.appContext.fileList()) {
			SmartHomeControllerActivity.appContext.deleteFile(s);
		}
	}
}
