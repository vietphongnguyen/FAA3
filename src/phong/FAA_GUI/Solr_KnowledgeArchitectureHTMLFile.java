/**
 * 
 */
package phong.FAA_GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;

/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class Solr_KnowledgeArchitectureHTMLFile {

	String htmlContent = "";
	List<StringIntegerPair> relatedTopics = new ArrayList<StringIntegerPair>();
	
	/**
	 * 
	 */
	public Solr_KnowledgeArchitectureHTMLFile() {
		// TODO Auto-generated constructor stub
	}

	public Solr_KnowledgeArchitectureHTMLFile(String fileName) {
		this();
		
		FileInputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		String inputFolder = "C:\\FAA3\\Solr_KnowledgeArchitecture\\";
		try {
			inputStream = new FileInputStream(inputFolder + fileName);
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			String line;
			
			while ((line = bufferedReader.readLine()) != null	) {
				htmlContent += line + System.lineSeparator();
				
			}
			
			parserRelatedTopics();
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { 	bufferedReader.close();	} catch (IOException e) {}
			try {	inputStreamReader.close();} catch (IOException e) {	}
			try {	inputStream.close();} catch (IOException e) {	}
		}
	}

	private void parserRelatedTopics() {
		String s = htmlContent;
		int vt_results = s.indexOf("id=\"results\""); // id="results"
		if (vt_results<0) return;
		int start = vt_results;
		Set<String> topicsSet = new HashSet<String>();
		while (true) {
			int vt_topicWord = s.indexOf(" class=\"topicWord\"", start); // class="topicWord"
			if (vt_topicWord<0) break;
			int vt_href = s.indexOf("href=\"", vt_topicWord); // href=
			if (vt_href<0) break;
			int vt_close = s.indexOf(">", vt_href);
			if (vt_close<0) break;
			int vt_open = s.indexOf("<", vt_close);
			if (vt_open<0) break;
			String topic = s.substring(vt_close + 1, vt_open).trim();
			topic = TextProcessing.getLetterAndNumber(topic);
			if (topic.equals("")) break;
			start = vt_open;
			if (!topicsSet.contains(topic)) {
				
				int vt_frequency = s.indexOf("class=\"frequencyCircle\"", vt_open);
				if (vt_frequency<0) break;
				int vt_close2 = s.indexOf(">", vt_frequency);
				if (vt_close2<0) break;
				int vt_open2 = s.indexOf("<", vt_close2);
				if (vt_open2<0) break;
				String s_frequency = null;
				try {
					s_frequency = s.substring(vt_close2 + 1, vt_open2).trim();
					s_frequency = TextProcessing.getNumber(s_frequency);
					int frequency = Integer.parseInt(s_frequency);
					relatedTopics.add(new StringIntegerPair(topic,frequency));
					topicsSet.add(topic);
					
				} catch (Exception e) {
					continue;
				}
				
				
				start = vt_open2;	// set start position for the next loop
			}
			
			
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Solr_KnowledgeArchitectureHTMLFile sKA =  new Solr_KnowledgeArchitectureHTMLFile("weather condition.html");
		displayTopics(sKA.getRelatedTopics());
	}

	private static void displayTopics(List<StringIntegerPair> relatedTopics) {
		for (StringIntegerPair relatedTopic : relatedTopics) {
			System.out.println(relatedTopic.Topic + " (" + relatedTopic.Frequency + ") ");
		}
		
	}

	public List<StringIntegerPair> getRelatedTopics() {
		
		return relatedTopics;
	}

	public void displayListSolr(List<StringIntegerPair> relatedTopics,DefaultListModel listSolr) {
		for (StringIntegerPair relatedTopic : relatedTopics) {
			listSolr.addElement(relatedTopic.Topic + " (" + relatedTopic.Frequency + ") ");
		}

	}

}
