package edu.bu.cs622.hw2;

import java.io.IOException;
import java.util.List;

public class Run {
	
	// define consts of category names
	private static final String ACTIVFIT_CATEGORY = "ActivFit";
	private static final String ACTIVITY_CATEGORY = "Activity";
	private static final String BATTERY_SENSOR_CATEGORY = "BatterySensor";
	private static final String BLUETOOTH_CATEGORY = "Bluetooth";
	private static final String LIGHT_SENSOR_CATEGORY = "LightSensor";
	private static final String HEART_RATE_CATEGORY = "HeartRate";
	private static final String SCREEN_USAGE_CATEGORY = "ScreenUsage";
	
	// generated file paths from file merge
	private static String activFitFileMerged;
	private static String activityFileMerged;
	private static String batterySensorFileMerged;
	private static String bluetoothFileMerged;
	private static String lightSensorFileMerged;
	private static String heartRateFileMerged;
	private static String screenUsageFileMerged;
	
	public static void main(String[] args) throws IOException {
		
		// merge files
		
		// list of all files from directory
		List<String> files = FileMerge.listFiles("SampleUserSmartwatch");
		
		// get list of files in each category
		List<String> activFitFiles = FileMerge.separateFileList(files, ACTIVFIT_CATEGORY);
		List<String> activityFiles = FileMerge.separateFileList(files, ACTIVITY_CATEGORY);
		List<String> batterySensorFiles = FileMerge.separateFileList(files, BATTERY_SENSOR_CATEGORY);
		List<String> bluetoothFiles = FileMerge.separateFileList(files, BLUETOOTH_CATEGORY);
		List<String> lightSensorFiles = FileMerge.separateFileList(files, LIGHT_SENSOR_CATEGORY);
		List<String> heartRateFiles = FileMerge.separateFileList(files, HEART_RATE_CATEGORY);
		List<String> screenUsageFiles = FileMerge.separateFileList(files, SCREEN_USAGE_CATEGORY);
		
		// merge all files
		activFitFileMerged = FileMerge.mergeFiles(activFitFiles, "smartWatchData\\ActivFit.txt");
		activityFileMerged = FileMerge.mergeFiles(activityFiles, "smartWatchData\\Activity.txt");
		batterySensorFileMerged = FileMerge.mergeFiles(batterySensorFiles, "smartWatchData\\BatterySensor.txt");
		bluetoothFileMerged = FileMerge.mergeFiles(bluetoothFiles, "smartWatchData\\Bluetooth.txt");
		lightSensorFileMerged = FileMerge.mergeFiles(lightSensorFiles, "smartWatchData\\LightSensor.txt");
		heartRateFileMerged = FileMerge.mergeFiles(heartRateFiles, "smartWatchData\\HeartRate.txt");
		screenUsageFileMerged = FileMerge.mergeFiles(screenUsageFiles, "smartWatchData\\ScreenUsage.txt");

		// ouput paths of generated files
		System.out.println("merged file paths" + activFitFileMerged + "\n" + activityFileMerged 
				+ "\n" + batterySensorFileMerged + "\n" + bluetoothFileMerged + "\n" 
				+ lightSensorFileMerged + "\n" + heartRateFileMerged + "\n" + screenUsageFileMerged);
		
		// search files
		
		// pass in filepath and keyword to search for lines
		FileSearch.regexSearch(activFitFileMerged, "Mon Jun 6 19:32:17 EDT 2016");
		FileSearch.regexSearch(batterySensorFileMerged, "percent\":20");
		FileSearch.regexSearch(activityFileMerged, "step_counts\":1809");
		FileSearch.regexSearch(lightSensorFileMerged, "Sun Jun 4 13:53:09 EDT 2017");
		FileSearch.regexSearch(heartRateFileMerged, "\"bpm\":156");
		

	}
}
