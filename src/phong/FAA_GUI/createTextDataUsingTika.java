package phong.FAA_GUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JList;
import javax.swing.SwingWorker;

import org.apache.commons.io.FileUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

class createTextDataUsingTika extends SwingWorker {

	String InputFolderName;
	String OutputFolderName;
	
	// Constructor
	public createTextDataUsingTika(String inputFolderName , String outputFolderName) throws IOException, SAXException, TikaException {
		InputFolderName = inputFolderName;
		OutputFolderName = outputFolderName;
		}
	
	protected Object doInBackground() throws Exception {

		
		String inputFolderName = InputFolderName;
		String outputFolderName = OutputFolderName;
		Outln("Extracting text data from '" + inputFolderName + "' to '" + outputFolderName + "'");
		
		DeleteOldFilesInDirectory(outputFolderName);
		FAA3_GUI.JListOfFiles.clear();
		
		
		String mainPhrasesOutputFolder = FAA3_GUI.txtCfaamainphrases.getText();
		DeleteOldFilesInDirectory(mainPhrasesOutputFolder);
		
		File inputFolder = new File(inputFolderName);
		File[] listOfFiles = inputFolder.listFiles();
		
		String fileName, fileName2;
		
		FAA3_GUI.list = new JList(FAA3_GUI.JListOfFiles);
		String[] fileValues = new String[listOfFiles.length];
		
		FAA3_GUI.progressBar.setMaximum(listOfFiles.length);
		FAA3_GUI.progressBar.setValue(0);
		FAA3_GUI.progressBar.setStringPainted(true);
		long startTime,currentTime,traveredTime;
		int count =0;
		
		startTime = System.nanoTime(); // start counting time
		for (File file : listOfFiles) {
			FAA3_GUI.progressBar.setValue(FAA3_GUI.progressBar.getValue()+1);
			double percent = 100* FAA3_GUI.progressBar.getValue()/  FAA3_GUI.progressBar.getMaximum();
			
			currentTime =  System.nanoTime(); // get current time
			traveredTime = (currentTime - startTime) ;
			
			long remainingTime = (long) (traveredTime * ((100-percent) /percent ));
			remainingTime = remainingTime / 1000000000; // converted from nanoseconds to second
			
			FAA3_GUI.progressBar.setString("Doing " + (count+1) + " / " + listOfFiles.length + " ( " + percent  
					+ " % )  Time remaining: " + remainingTime/60 +" m "  + remainingTime % 60 + " s" 
					);
			
			
			if (FAA3_GUI.process_createTextDataUsingTika.isCancelled()) { Out("The process of creating text data had been canceled by user  \n"); FAA3_GUI.btnExtractTextContents.setEnabled(true); FAA3_GUI.btnGetFiles.setEnabled(true); return -1; }
			if (file.isFile()) {
				fileName = file.getName();

				fileName2 = CheckFileName(fileName);			// if there is any %20 in file name, 
				if (!fileName.equalsIgnoreCase(fileName2)) {	// rename it to space character
					// Rename the file
					File newfile = new File(inputFolderName + "/" + fileName2);

					if (file.renameTo(newfile)) {
						System.out.println(fileName + " have been renamed succesfully to : " + fileName2);
						Out(fileName + " have been renamed succesfully to : " + fileName2 + "\n");
						fileName = fileName2;
					} else {
						System.out.println("Warning: " + fileName + " COUNLD NOT rename to : " + fileName2
								+ ". This file will be igrored");
						Out("Warning: " + fileName + " COUNLD NOT rename to : " + fileName2
								+ ". This file will be igrored\n");
						
						fileValues[count] =  (count+1) + " __________ " + fileName;
						FAA3_GUI.JListOfFiles.add(count, fileValues[count]);
						count ++;
						continue;
					}
				}

				Out("\nExtracting '" + fileName + "'\n");

				// System.out.println("Parsing using the Auto-Detect Parser:");
				BodyContentHandler handler = new BodyContentHandler();
				AutoDetectParser parser = new AutoDetectParser();
				Metadata metadata = new Metadata();
				ParseContext parseContext = new ParseContext();
				FileInputStream stream = new FileInputStream(new File(inputFolderName +"/" + fileName));
				if (FAA3_GUI.process_createTextDataUsingTika.isCancelled()) { Out("The process of creating text data had been canceled by user  \n"); FAA3_GUI.btnExtractTextContents.setEnabled(true);FAA3_GUI.btnGetFiles.setEnabled(true); return -1; }
				try {
					parser.parse(stream, handler, metadata, parseContext);

				} catch (Exception e) {
					System.out.println("Warning: Error when processing file:" + fileName
							+ " . This file will be igrored! \n" + e.getMessage() + "\n" + e.toString());
					e.printStackTrace();
					Out("Warning: Error when processing file:" + fileName + " . This file will be igrored! \n"
							+ e.getMessage() + "\n");
					Outln(e.toString());
					fileValues[count] =  (count+1) + " __________ " + fileName;
					FAA3_GUI.JListOfFiles.add(count, fileValues[count]);
					count ++;
					continue;
				} finally {
					stream.close();
				}
				String s = handler.toString();
				/*
				 * // getting metadata of the document
				 * System.out.println("Metadata of the file:"); String [] metadataNames =
				 * metadata.names(); for (String name : metadataNames) { System.out.println(name
				 * + " : " + metadata.get(name)); }
				 */
				if (FAA3_GUI.process_createTextDataUsingTika.isCancelled()) { Out("The process of creating text data had been canceled by user  \n"); FAA3_GUI.btnExtractTextContents.setEnabled(true);FAA3_GUI.btnGetFiles.setEnabled(true); return -1; }
				
				if (FAA3_GUI.checkBox_LetterOnly.isSelected()) {
					s = TextProcessing.getWordsWithLetterOnly(s);
				}
				
				if (FAA3_GUI.chckbxAlsoParseThe.isSelected()) {
					ParseHeaderPhrases headerPhrases = new ParseHeaderPhrases(file,mainPhrasesOutputFolder,
							FAA3_GUI.comboBox_FromPage.getSelectedItem().toString(),FAA3_GUI.comboBox_ToPage.getSelectedItem().toString() );
				}
				
				if (FAA3_GUI.chckbxEnglishOnly.isSelected() ) {
					String language;
					language = identifyLanguage(s);
					System.out.println("Language of text:" + language);
					Out("Language of text:" + language + "\n");
					if ((! (language.equalsIgnoreCase("en") || language.equalsIgnoreCase("et")))  ) {
						System.out.println("Warning: the content of the file:" + fileName
								+ " have NOT been writen in English then this file will be ignored!");

						Outln("Warning: the content of the file:" + fileName
								+ " have NOT been writen in English then this file will be ignored!");
						fileValues[count] =  (count+1) + " [" + language + "]________ " + fileName;
						FAA3_GUI.JListOfFiles.add(count, fileValues[count]);
						count ++;
					}
				}
				else {
					Writer writer = null;
					try {
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream( outputFolderName + "/" + fileName + ".txt"), "utf-8"));
						writer.write(s);
						
						fileValues[count] =  (count+1) + " : " + fileName;
						FAA3_GUI.JListOfFiles.add(count, fileValues[count]);
						count ++;
						
					} catch (IOException ex) {
						// report
						System.out.println("Warning: Error when saving file:" + fileName
								+ ".txt  . This file had been ignore! \n" + ex.getMessage());
						Outln("Warning: Error when saving file:" + fileName + ".txt  . This file had been ignore! \n"
								+ ex.getMessage());
						fileValues[count] =  (count+1) + " __________ " + fileName;
						FAA3_GUI.JListOfFiles.add(count, fileValues[count]);
						count ++;
						continue;
					} finally {
						try {
							writer.close();
						} catch (Exception ex) {}
					}
					
				}
			}
		}
		
		FAA3_GUI.progressBar.setString("Done " + (count) + " / " + listOfFiles.length + " ( 100 % )");
		
		FAA3_GUI.btnGetFiles.setEnabled(true);
		FAA3_GUI.btnXCancel.setEnabled(false);
		FAA3_GUI.btnExtractTextContents.setEnabled(true);
        Outln("Extracting text data from document files has been completed!");
		
		return 0 ;
    }
	
	private void DeleteOldFilesInDirectory(String outputFolderName) {
		// Delete the old file in this directory
		try {
			FileUtils.deleteDirectory(new File(outputFolderName ));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		if (new File(outputFolderName).mkdir()) {
			//Out("Make folder '"+ folder +"' successfully \n");
		}
		//Out("Delete all of the old files in directory'"+ outputFolderName +"' successfully \n");
	}

	private static String Check_Folder_Name(String folderName) {
		String s = folderName.replaceAll("^\\s+|\\s+$", ""); // remove all the space at the beginning and at the end of
																// string S
		if (s.endsWith("/"))
			s = s.substring(0, s.length()-2) ; // delete the last '/' character at the end if it's present.
		return s;
	}
	private static void Out(int i) {
		// TODO Auto-generated method stub
		String s = Integer.toString(i);
		Out(s);
	}
	private static void Outln(String string) {
		Out(string + "\n");
		FAA3_GUI.console.setCaretPosition(FAA3_GUI.console.getDocument().getLength());
	}

	public static void Out(String s) {
		FAA3_GUI.console.setText(FAA3_GUI.console.getText() + s);
		FAA3_GUI.console.setCaretPosition(FAA3_GUI.console.getDocument().getLength());
	}
	
	
	public static String identifyLanguage(String text) {
		LanguageIdentifier identifier = new LanguageIdentifier(text);
		return identifier.getLanguage();
	}


	private static String CheckFileName(String fileName) {
		String s = fileName;
		s = s.replaceAll("^\\s+|\\s+$", ""); // remove all the space at the beginning and at the end of string S
		s = s.replaceAll("%20", " ");
		return s;
	}

}