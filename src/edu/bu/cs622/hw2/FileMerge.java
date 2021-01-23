package edu.bu.cs622.hw2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// merge all files into one for each sensor
public class FileMerge {
	
	// list all files in directories and sub-directories
	public static List<String> listFiles(String directory) {
		
		List<String> result = null;
		
		// get all files from directory including within sub directories
		try (Stream<Path> filePaths = Files.walk(Paths.get(directory))) { // try block with resources 
			// Files.walk traverses directories depth first recursively
			result = filePaths.filter(Files::isRegularFile) // only keep files not directory paths
					.map(x -> x.toString()) // map paths to strings
					.filter(f -> !f.contains("SA")) // ignore all files in SA folder
					.sorted(Comparator.naturalOrder()) // sort list by date
					.collect(Collectors.toList()); // collect into list
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// separate file list into different sub lists
	// takes the files list and returns one list that fits the category
	public static List<String> separateFileList(List<String> files, String category) {
		
		// add file String to sub list if its name contains the category name
		// one way to create lists with for loop
		
		/*
		List<String> separatedFiles = new ArrayList<String>();
		for (String file : files) if (file.contains(category)) separatedFiles.add(file);
		*/
		
		// alternatively using stream instead of for loop
		List<String> separatedFiles2 = files.stream()
				.filter(f -> f.contains(category))
				.collect(Collectors.toList());
		
		return separatedFiles2;
	}
	
	// merge files of each category into one
	public static String mergeFiles(List<String> files, String mergedFileName)
			throws IOException {
		
		// use string builder to keep content of file to write
		StringBuilder contentToWrite = new StringBuilder();
		
		// output file
		File outFile = new File(mergedFileName);
		BufferedWriter mergedFile = new BufferedWriter(new FileWriter(outFile));
		
		// string to return 
		String outFilePath = "";
		
		// reading each file into contentToWrite with help of readFile method
		for (String fileName : files) {
			String fileContent = readFile(fileName);
			contentToWrite.append(fileContent);
		}
		
		// writing into file mergedFile
		try{
			// try block with resources, no need to close file
		    mergedFile.write(contentToWrite.toString()); // write the string in string builder to file
		    outFilePath = outFile.getAbsolutePath();
		} catch(IOException e){
		    e.printStackTrace();
		} finally {
			mergedFile.close();
		}
		
		return outFilePath;
	}
	
	// helper method to read one file content into string builder
	// takes path of file and returns its content as string
	private static String readFile(String filePath) throws IOException {
		
	    StringBuilder tmpContent = new StringBuilder();
	    
	    try (Stream<String> stream = Files.lines(Paths.get(filePath))){ 
	    	// try with resources, no need to close file 
	        stream.forEach(
	        		s -> tmpContent.append(s).append("\n")
	        		); // append each line to tmpContent then new line
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	    
	    return tmpContent.toString(); // return string that's in string builder
	}

}
 