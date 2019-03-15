/**
 * 
 */
package phong.FAA_GUI;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;

import cc.mallet.types.IDSorter;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;

/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class LDATopicsSearch {

	static SuggestedDocumentsForATopic suggestedDocumentsForATopic;
	
	/**
	 * 
	 */
	public LDATopicsSearch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed_EstimateTopicsSearch() {
		//createTextFileFromTopic(topicSearch, folder, maxNumberOfWords, fileName);
		String topics;
		try {
			topics = FAA3_GUI.comboBox_TopicSearch.getSelectedItem().toString();
			FAA3_GUI.createTextFileFromTopic(topics, FAA3_GUI.txtTextdatafolder.getText(), 4000, "P_N_Topic_Search.txt");
		} catch (Exception e) {
			e.printStackTrace();
			FAA3_GUI.Outln("Error: " + e.getMessage());
			return;
		}
		FAA3_GUI.btnEstimateTopics_1.setEnabled(false);
		FAA3_GUI.textField_LDARelatedTopics.setText(topics);
		FAA3_GUI.actionPerformed_EstimateTopics();
		
	}

	protected static void removeTopicSearchFile(String folder, String fileName) {
		// folder, fileName
		try {
			File file = new File(folder + "\\" + fileName);
			if (file.delete()) {
				//System.out.println(file.getName() + " is deleted!");
			} else {
				//System.out.println("Delete operation is failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FAA3_GUI.Outln("Warning: " + e.getMessage());
		}
	}
	public static void getTopicDocsScore(String fileName, InstanceList instances,String searchText) {
		// fileName, instances
		String s;
		
		int topic_Search=0;
		double maxProbabilities = 0;
		int instanceID = 0;
		for (Instance I : instances) {
			s = generateTopics.Get_File_Name(I.getName().toString().replaceAll("%20", " "));
			if (s.equalsIgnoreCase("P_N_Topic_Search.txt")) {
				double[] Probabilities = generateTopics.model.getTopicProbabilities(instanceID);
				
				for (int topicID = 0; topicID < generateTopics.numTopics; topicID++) {
					if ( maxProbabilities < Probabilities[topicID]) {
						topic_Search = topicID;
						maxProbabilities = Probabilities[topicID];
					}
						
				}
				break;
			}
			instanceID++;
		}
		
		// found that "P_N_Topic_Search.txt" has max value of Probabilities at topic_Search
		int n = instanceID;
		String[] fileNames = new String[n] ;
		double[] probs = new double[n];
		int NN =-1;
		instanceID = 0;
		for (Instance I : instances) {
			s = generateTopics.Get_File_Name(I.getName().toString().replaceAll("%20", " "));
			if (!s.equalsIgnoreCase("P_N_Topic_Search.txt")) {
				double prob = generateTopics.model.getTopicProbabilities(instanceID)[topic_Search];
				
					NN++;
					fileNames[NN] = s;
					probs[NN] = prob;
				
			}
			instanceID++;
		}
		
		/*for (int i=0 ; i<= NN;i++) {
			System.out.println(fileNames[i]);
			System.out.println(probs[i]);
		}*/
		for (int i=0; i<n-1; i++) {
			for (int j = i+1; j<n ; j++) {
				if (probs[i]<probs[j]) {
					// Swap
					String temName = fileNames[i];
					fileNames[i] = fileNames[j];
					fileNames[j] = temName;
					double temProb = probs[i];
					probs[i] = probs[j];
					probs[j] = temProb;
				}
			}
		}
		printListOfWordsInTopic(topic_Search, FAA3_GUI.textArea_LDATopicWords);
		
		//printSuggestedDocumentsForATopic(FAA3_GUI.listModel_LDATopicsSearch, new DecimalFormat("#,##0.0000"), fileNames, probs);
		searchText = FAA3_GUI.textField_LDARelatedTopics.getText();
		suggestedDocumentsForATopic = new SuggestedDocumentsForATopic(searchText,fileNames, probs, topic_Search);
		suggestedDocumentsForATopic.setVisible(true);
		
		
	}
	
	public static void printSuggestedDocumentsForATopic(DefaultListModel<String> listModel, DecimalFormat decimalFormat, String[] fileNames, double[] probs) {
		
		double minProb;
		try {
			minProb = Double.parseDouble(SuggestedDocumentsForATopic.textField_MinProb.getText());
		} catch (NumberFormatException e1) {
			minProb = 0.005;
			e1.printStackTrace();
		}
		listModel.clear();
		for (int i = 0; i< probs.length; i++) {
			try {
				if (probs[i]>= minProb)
					listModel.add(i, decimalFormat.format(probs[i]) + " : " + fileNames[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void printListOfWordsInTopic(int topic, JTextArea textArea) {
		String output="";
		Iterator<IDSorter> iterator = generateTopics.topicSortedWords.get(topic).iterator();
		while (iterator.hasNext() ) {
			IDSorter idCountPair = iterator.next();
			output += generateTopics.dataAlphabet.lookupObject(idCountPair.getID()) + " (" + idCountPair.getWeight() + "), ";
			
		}
		
		textArea.setText(output);
	}


}
