/**
 * 
 */
package phong.FAA_GUI;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;

/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class FindTopicsByTitlePhrases2 {

	String topics;
	public static Set<String> phrasesSet = new LinkedHashSet<String>();
	TreeMap<Integer, String> phrasesLevel = new TreeMap<>((Collections.reverseOrder()));
	
	String inputFolderS = "C:\\FAA3\\main_phrases";
	public static String stopwordTopicSearch="\n";
	

	/**
	 * 
	 */
	public FindTopicsByTitlePhrases2() {
		if (phrasesSet.isEmpty())
			initPhrasesSet();
		if (stopwordTopicSearch.length()<2)
			initStopWordsList("topicsSearch_stopwords.txt");
		actionPerformed_FindTopicsButtonClicked();
	}

	private void updateModelContent(String topics) {
		
		topics = topics.trim().toLowerCase();
		topics = TextProcessing.removeDuplicateSpace(topics);
		if (topics.equals(FAA3_GUI.modelContent[0])) return;
		
		for (int i = 0 ;i < FAA3_GUI.modelContent.length; i++) 
			FAA3_GUI.modelContent[i] = FAA3_GUI.modelContent[i].trim().toLowerCase();
		Set<String> topicsSearchSet = new LinkedHashSet<String>(); 
		topicsSearchSet.add(topics);
		topicsSearchSet.addAll(Arrays.asList(FAA3_GUI.modelContent));
		FAA3_GUI.modelContent = new String[topicsSearchSet.size()];
		int i=0;
		for (String s : topicsSearchSet) {
			FAA3_GUI.modelContent[i]=s;
			i++;
		}
		FAA3_GUI.comboBox_TopicSearch.setModel(new DefaultComboBoxModel(FAA3_GUI.modelContent));
		FAA3_GUI.comboBox_TopicSearch.repaint();
	}

	protected int CountWordMatch(Set<String> topicsSet, String phrase) {
		int count = 0;
		phrase = " " + phrase + " ";
		for (String topic:topicsSet)
			if (phrase.indexOf(" " + topic.trim() + " ") >= 0 ) 
				count++;
		return count;
	}
	public void actionPerformed_mouseClicked() {
		int index = FAA3_GUI.list_3.getSelectedIndex();
		String topic = FAA3_GUI.listPhrases.getElementAt(index).toString();
		topic = topic.substring(topic.lastIndexOf(')') + 1 ).trim();
		updateModelContent(topic);
		actionPerformed_FindTopicsButtonClicked();
	}
	
	public void actionPerformed_FindTopicsButtonClicked() {
		topics = FAA3_GUI.comboBox_TopicSearch.getSelectedItem().toString().trim().toLowerCase();
		topics = TextProcessing.removeDuplicateSpace(topics);
		if (topics.length()<=0) return;
		
		updateModelContent(topics);
		Set<String> topicsSet = new LinkedHashSet<String>();
		String[] tA = topics.split(" ");
		for (int length = tA.length ; length> 1; length--) {
			for (int i = 0 ; i <= tA.length - length; i++ ) {
				String s = "";
				for (int j = 0 ; j < length ; j++) {
					if (s.equals("")) s = tA[i+j];
					else s += " " + tA[i+j];
				}
				topicsSet.add(s);	
			}
		}
		
		for (String t:tA) {
			if (stopwordTopicSearch.indexOf("\n" + t + "\n") < 0 )
				topicsSet.add(t);
		}
		phrasesLevel = new TreeMap<>((Collections.reverseOrder()));
		//phrasesLevel.clear();
		for (String phrase: phrasesSet) {
			int numberWordMatch = CountWordMatch(topicsSet,phrase);
			if (numberWordMatch > 0) {
				if (phrasesLevel.containsKey(numberWordMatch))
					phrasesLevel.put(numberWordMatch, phrasesLevel.get(numberWordMatch) + "\n" + phrase);
				else 
					phrasesLevel.put(numberWordMatch, phrase);
			}
		}
		
		FAA3_GUI.listPhrases.clear();
		
		for (Entry<Integer,String> entry : phrasesLevel.entrySet()) {
			String value = entry.getValue();
			for (String s: value.split("\n"))
				FAA3_GUI.listPhrases.addElement("(" +entry.getKey() + ") " + s );
		}
		
		FAA3_GUI.list_3.ensureIndexIsVisible(0);
		FAA3_GUI.textField_RelatedPhrases.setText(topics);
		
	}
	
	private void initStopWordsList(String fileName) {
		stopwordTopicSearch = "\n";
		File file = new File(fileName);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.length() > 0) {
					stopwordTopicSearch += line + "\n";
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	private void initPhrasesSet() {
		inputFolderS = FAA3_GUI.txtCfaamainphrases.getText();
		File inputFolder = new File(inputFolderS);
		File[] listOfFiles = inputFolder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				BufferedReader br;
				try {
					br = new BufferedReader(new FileReader(file));
					String line;
					while ((line = br.readLine()) != null) {
						line = line.trim();
						line = TextProcessing.removeDuplicateSpace(line);
						if (line.length() > 0) {
							phrasesSet.add(line);
						}
					}
						
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
