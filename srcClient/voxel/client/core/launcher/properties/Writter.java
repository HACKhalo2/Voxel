package voxel.client.core.launcher.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import voxel.client.core.launcher.Constants;
import voxel.client.core.util.Logger;

public class Writter {
	public static void configWritter() {

		Properties prop = new Properties();
		OutputStream output = null;

		File f = new File(Constants.optionsPath);
		if (!f.exists()) {

			try {
				Logger.log("Creating config file");
				output = new FileOutputStream(Constants.optionsPath);
				prop.setProperty("IconPath", Constants.iconPath1);
				prop.setProperty("BackgroundPath", Constants.iconPath2);
				prop.store(output, null);

			} catch (IOException io) {
				io.printStackTrace();
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}
}