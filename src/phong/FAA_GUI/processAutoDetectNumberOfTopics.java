package phong.FAA_GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.Input2CharSequence;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.Target2Label;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.pipe.TokenSequenceLowercase;
import cc.mallet.pipe.TokenSequenceRemoveStopwords;
import cc.mallet.pipe.iterator.FileIterator;
import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.topics.TopicInferencer;
import cc.mallet.types.Alphabet;
import cc.mallet.types.FeatureSequence;
import cc.mallet.types.IDSorter;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;
import cc.mallet.types.LabelSequence;

public class processAutoDetectNumberOfTopics extends SwingWorker {

	Pipe pipe;
	static String dataFolder;
	static int numTopics;
	double AlphaSum;
	double Beta;
	int NumThreads;
	public static int numIterations;
	static int NumberOfWordsInATopic;
	public static long startTime;
	long currentTime;
	long traveredTime;
	public static int count;
	static ArrayList<TreeSet<IDSorter>> topicSortedWords = null;
	static Formatter out;
	static Formatter outTopicsTextBox;
	static double[] topicDistribution;
	static Alphabet dataAlphabet;
	static InstanceList instances;
	static ParallelTopicModel model;
	public static List<RowSorter.SortKey> sortKeys;
	public static TableRowSorter<TableModel> sorter;
	static int LoopCount =1;
	
	// Constructor
	public processAutoDetectNumberOfTopics(String data_text, int numtopics, double alphaSum, double beta, int numThreads,
			int numiterations, int numberOfWordsInATopic) {

		dataFolder = data_text;
		numTopics = numtopics;
		Beta = beta;
		AlphaSum = alphaSum;
		NumThreads = numThreads;
		numIterations = numiterations;
		NumberOfWordsInATopic = numberOfWordsInATopic;
		pipe = buildPipe();
	}

	public Pipe buildPipe() {
		ArrayList pipeList = new ArrayList();
		pipeList.add(new Input2CharSequence("UTF-8"));
		Pattern tokenPattern = Pattern.compile("\\w+");
		pipeList.add(new CharSequence2TokenSequence(tokenPattern));
		pipeList.add(new TokenSequenceLowercase());

		pipeList.add(new TokenSequenceRemoveStopwords(new File("enlish_stopwords.txt"), "UTF-8", false, false, false) );
		//pipeList.add(new TokenSequenceRemoveStopwords(new File(getClass().getResource("/Resources/en.txt").getFile()), "UTF-8", false, false, false));

		pipeList.add(new TokenSequence2FeatureSequence());
		pipeList.add(new Target2Label());

		return new SerialPipes(pipeList);
	}

	public InstanceList readDirectory(File directory) {
		return readDirectories(new File[] { directory });
	}

	public InstanceList readDirectories(File[] directories) {
		/*
		 * An instance contains four generic fields of predefined name: "data",
		 * "target", "name", and "source". "Data" holds the data represented `by the
		 * instance, "target" is often a label associated with the instance, "name" is a
		 * short identifying name for the instance (such as a filename), and "source" is
		 * human-readable sourceinformation, (such as the original text).
		 */
		FileIterator iterator = new FileIterator(directories, new TxtFilter(), FileIterator.LAST_DIRECTORY);
		InstanceList instances = new InstanceList(pipe);
		instances.addThruPipe(iterator);

		// System.out.println("addThruPipe structure: " + iterator );

		return instances;
	}

	class TxtFilter implements FileFilter {
		public boolean accept(File file) {
			return file.toString().endsWith(".txt");
		}
	}

	@Override
	protected Object doInBackground() throws Exception {
		FAA3_GUI.ProcessAutoDetectNumberOfTopics_running = true;
		FAA3_GUI.Outln(" thread start processing 'Auto Detect Number Of Topics' ... ");
		instances = this.readDirectory(new File(dataFolder));
		
		
		int NumSeparateTopWordTopics;
		int NumMakeSenseTopics= numTopics;
		do {

			//numTopics = NumMakeSenseTopics;
			numTopics = (int) (numTopics + NumMakeSenseTopics)/2; // estimate the mean of numTopics and NumMakeSenseTopics. Get closer to the NumMakeSenseTopics, but not too fast as numTopics = NumMakeSenseTopics
			
			FAA3_GUI.Outln(" Estimating " + numTopics + " topics ... ");
			model = new ParallelTopicModel(numTopics, AlphaSum, Beta);

			model.addInstances(instances);

			// Use two parallel samplers, which each look at one half the corpus and combine
			// statistics after every iteration.
			model.setNumThreads(NumThreads);
			model.setNumIterations(numIterations);

			FAA3_GUI.progressBarMallet.setMaximum(numIterations);
			FAA3_GUI.progressBarMallet.setValue(0);
			FAA3_GUI.progressBarMallet.setStringPainted(true);
			count = 0;
			startTime = System.nanoTime(); // start counting time
			try {
				model.estimate();
			} catch (Exception e) {
				FAA3_GUI.Outln(e.toString());
				FAA3_GUI.Outln("Warning ***** 'Auto Detect Number Of Topics' fail at the first try !");
				try {
					FAA3_GUI.Outln("Second time trying to estimate 'Auto Detect Number Of Topics' ... ");
					model.estimate();
				} catch (Exception e2) {
					FAA3_GUI.Outln("Warning ***** 'Auto Detect Number Of Topics' fail at the 2nd try !");
					try {
						FAA3_GUI.Outln("3rd time trying to estimate 'Auto Detect Number Of Topics' ... ");
						model.estimate();
					} catch (Exception e3) {
						FAA3_GUI.btnCancelMallet.setEnabled(false);
						FAA3_GUI.btnEstimateTopics.setEnabled(true);
						FAA3_GUI.Outln("Error ***** 'Auto Detect Number Of Topics' fail at the 3rd time also ! Stop the process.");
						FAA3_GUI.Outln(e3.toString());
						e3.printStackTrace();
						if (! FAA3_GUI.spinnerNumThreards.getValue().equals(1) ) {
							// if the Number of Threards is >1 then ask if you want to reduce into 1 -> much more stable but might be slower
							int dialogResult = JOptionPane.showConfirmDialog (null, 
									"Auto Detect Number Of Topics was fail at the 3rd time! Stop the process.\n" 
									+ "Would You Like to reduce number of parallel threads into 1? Ussually it will be much more stable","Warning",JOptionPane.YES_NO_OPTION);
							if(dialogResult == JOptionPane.YES_OPTION){
								FAA3_GUI.spinnerNumThreards.setValue(1);
							}
						}
						return -1;
					}
				}
			}

			// The data alphabet maps word IDs to strings
			dataAlphabet = instances.getDataAlphabet();

			FeatureSequence tokens = (FeatureSequence) model.getData().get(0).instance.getData();
			LabelSequence topics = model.getData().get(0).topicSequence;

			out = new Formatter(new StringBuilder(), Locale.US);
			outTopicsTextBox = new Formatter(new StringBuilder(), Locale.US);

			for (int position = 0; position < tokens.getLength(); position++) {
				out.format("%s-%d ", dataAlphabet.lookupObject(tokens.getIndexAtPosition(position)),
						topics.getIndexAtPosition(position));
			}

			// Estimate the topic distribution of the first instance,
			// given the current Gibbs state.
			topicDistribution = model.getTopicProbabilities(0);

			// Get an array of sorted sets of word ID/count pairs
			topicSortedWords = model.getSortedWords();

			NumSeparateTopWordTopics = printListOfTopics();

			NumMakeSenseTopics = printCompositionTableFromInstances();

			// Completed
			FAA3_GUI.progressBarMallet
					.setString("Done " + (count) + " / " + numIterations + " ( Estimating topics finished )");
			FAA3_GUI.btnCancelMallet.setEnabled(false);
			FAA3_GUI.btnEstimateTopics.setEnabled(true);
			FAA3_GUI.Outln("Generating topics from document files has been completed successfully !");
			FAA3_GUI.btnGetCompositionTable.setEnabled(true);
			
			autoDetectNumTopics.spinnerMaxNumLoop.commitEdit();
			
		} while ( (NumMakeSenseTopics < numTopics) &&  (NumMakeSenseTopics >= autoDetectNumTopics.getMinimumTopics()) && (LoopCount <= (int) autoDetectNumTopics.spinnerMaxNumLoop.getValue())    );
		
		
		///////////////////////////// consider the top word of each topic//////////////////////////////////////////////////////////////////////////////////////////////////
		if ( (autoDetectNumTopics.comboBoxNumTopword.getSelectedIndex() > 0) &&  (LoopCount <= (int) autoDetectNumTopics.spinnerMaxNumLoop.getValue()) ) {
			
			autoDetectNumTopics.spinnerNumIterationTopword.commitEdit();
			numIterations = (int) autoDetectNumTopics.spinnerNumIterationTopword.getValue();
			
			while ((NumSeparateTopWordTopics < numTopics)  &&  (LoopCount <= (int) autoDetectNumTopics.spinnerMaxNumLoop.getValue())
					&& (NumSeparateTopWordTopics >= autoDetectNumTopics.getMinimumTopics())) {
				numTopics = NumSeparateTopWordTopics;
				FAA3_GUI.Outln(" Estimating " + numTopics + " topics ... ");
				model = new ParallelTopicModel(numTopics, AlphaSum, Beta);
				model.addInstances(instances);
				model.setNumThreads(NumThreads);
				model.setNumIterations(numIterations);

				FAA3_GUI.progressBarMallet.setMaximum(numIterations);
				FAA3_GUI.progressBarMallet.setValue(0);
				FAA3_GUI.progressBarMallet.setStringPainted(true);
				count = 0;
				startTime = System.nanoTime(); // start counting time
				try {
					model.estimate();
				} catch (Exception e) {
					FAA3_GUI.Outln(e.toString());
					FAA3_GUI.Outln("Warning ***** 'Auto Detect Number Of Topics' fail at the first try !");
					try {
						FAA3_GUI.Outln("Second time trying to estimate 'Auto Detect Number Of Topics' ... ");
						model.estimate();
					} catch (Exception e2) {
						FAA3_GUI.Outln("Warning ***** 'Auto Detect Number Of Topics' fail at the 2nd try !");
						try {
							FAA3_GUI.Outln("3rd time trying to estimate 'Auto Detect Number Of Topics' ... ");
							model.estimate();
						} catch (Exception e3) {
							FAA3_GUI.btnCancelMallet.setEnabled(false);
							FAA3_GUI.btnEstimateTopics.setEnabled(true);
							FAA3_GUI.Outln(
									"Error ***** 'Auto Detect Number Of Topics' fail at the 3rd time also ! Stop the process.");
							FAA3_GUI.Outln(e3.toString());
							e3.printStackTrace();
							break;
						}
					}
				}
			}
			
			// The data alphabet maps word IDs to strings
			dataAlphabet = instances.getDataAlphabet();

			FeatureSequence tokens = (FeatureSequence) model.getData().get(0).instance.getData();
			LabelSequence topics = model.getData().get(0).topicSequence;

			out = new Formatter(new StringBuilder(), Locale.US);
			outTopicsTextBox = new Formatter(new StringBuilder(), Locale.US);

			for (int position = 0; position < tokens.getLength(); position++) {
				out.format("%s-%d ", dataAlphabet.lookupObject(tokens.getIndexAtPosition(position)), topics.getIndexAtPosition(position));
				
			}

			// Estimate the topic distribution of the first instance,
			// given the current Gibbs state.
			topicDistribution = model.getTopicProbabilities(0);

			// Get an array of sorted sets of word ID/count pairs
			topicSortedWords = model.getSortedWords();

			NumSeparateTopWordTopics = printListOfTopics();

			NumMakeSenseTopics = printCompositionTableFromInstances();

			// Completed
			FAA3_GUI.progressBarMallet
					.setString("Done " + (count) + " / " + numIterations + " ( Estimating topics finished )");
			FAA3_GUI.btnCancelMallet.setEnabled(false);
			FAA3_GUI.btnEstimateTopics.setEnabled(true);
			FAA3_GUI.Outln("Generating topics from document files has been completed successfully !");
			FAA3_GUI.btnGetCompositionTable.setEnabled(true);
			
		} ;
		
		
		FAA3_GUI.Outln(" Auto detect number of topics value is:  " + numTopics );
		FAA3_GUI.ProcessAutoDetectNumberOfTopics_running = false;
		
		// After auto estimate number of topic. let's update the number of topic and perform estimation with full number of iteration. 
		FAA3_GUI.spinnerNumTopics.setValue(numTopics);
		FAA3_GUI.actionPerformed_EstimateTopics();
		
		return null;
	}

	public static int printCompositionTableFromInstances() throws InterruptedException {
		// Print out the composition of the InstanceList

		boolean[] CountThisTopic = new boolean[numTopics];
		Float minThreshold = autoDetectNumTopics.getThreshold() ;
		int NumMakeSenseTopics =0;	// count only the topic have more than sliderMinThreshold.value() probability
		
		// reset the TopicModeling.compositionTable. delete all the old value from previous run.
		FAA3_GUI.compositionTable.setRowCount(0);
		FAA3_GUI.compositionTable.setColumnCount(3);

		FAA3_GUI.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 1; i < numTopics; i++)
			FAA3_GUI.compositionTable.addColumn("Topic " + i);
		
		FAA3_GUI.table.getColumnModel().getColumn(0).setPreferredWidth(30);
		FAA3_GUI.table.getColumnModel().getColumn(0).setMaxWidth(100);
		FAA3_GUI.table.getColumnModel().getColumn(1).setPreferredWidth(250);
		FAA3_GUI.table.getColumnModel().getColumn(1).setMinWidth(100);
		FAA3_GUI.table.getColumnModel().getColumn(1).setMaxWidth(1000);
		for (int i = 0; i < numTopics; i++) {
			FAA3_GUI.table.getColumnModel().getColumn(2 + i).setPreferredWidth(60);
			FAA3_GUI.table.getColumnModel().getColumn(2 + i).setMinWidth(30);
			FAA3_GUI.table.getColumnModel().getColumn(2 + i).setMaxWidth(100);
		}

		String s;
		int instanceID = 0;
		for (Instance I : instances) {

			Vector<String> row = new Vector<String>();
			row.add(StringWidthAlign(instances.size(), Integer.toString(instanceID)));
			s = Get_File_Name(I.getName().toString().replaceAll("%20", " "));
			row.add(s);

			double[] Probabilities = model.getTopicProbabilities(instanceID);
			for (int topicID = 0; topicID < numTopics; topicID++) {
				s = String.format("%.3f", Probabilities[topicID]);
				
				row.add(s);
								
				if (Probabilities[topicID] >= minThreshold) CountThisTopic[topicID] = true;
			}
			FAA3_GUI.compositionTable.addRow(row);
			instanceID++;
		}
		
		// Highline the big value in the column
		for (int i = 0; i < numTopics; i++)
			FAA3_GUI.table.getColumnModel().getColumn(i + 2).setCellRenderer(new HighlineCellRenderer(autoDetectNumTopics.getThreshold() , 0.19f ));

		// Sort the table when click on column title
		sorter = new TableRowSorter<TableModel>(FAA3_GUI.table.getModel());
		FAA3_GUI.table.setRowSorter(sorter);
		sortKeys = new ArrayList<>(numTopics + 3);
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);

		// refresh the table to make sure the cell color take effect
		FAA3_GUI.table.repaint();
		
		FAA3_GUI.SizeOfInstance = instances.size();
		for (int i = 0; i< numTopics; i++) 
			if (CountThisTopic[i]) NumMakeSenseTopics++;
		
		LoopCount ++;
		
		return NumMakeSenseTopics;
	}

	private static String Get_File_Name(String string) {
		int vt = string.lastIndexOf('/');
		String filename = string.substring(vt + 1);
		return filename;
	}

	private static String StringWidthAlign(int maxProgress, String string) {
		String s = string;

		int sopt = 0;
		int bac10 = 1;
		do {
			bac10 *= 10;
			sopt++;
		} while (bac10 < maxProgress); // Example: maxProgress = 120 => bac10 = 1000 => sopt= 3 => length of string
										// will be fixed to 3.

		while (s.length() < sopt)
			s = "0" + s; // s="0xx"

		return s;
	}

	@SuppressWarnings("unchecked")
	public static int printListOfTopics() {

		int NumSeparateTopWordTopics = numTopics;
		// Delete the old value of this Topics list
		FAA3_GUI.JListOfTopics.clear();

		String topWordList=" ";
		String word;
		int numOfWordConsiderInEachTopic =autoDetectNumTopics.comboBoxNumTopword.getSelectedIndex(); // How many number of words in each topic to be considered.  1: only consider the 1st word of each topic
		
		FAA3_GUI.JListOfTopics.add(0, "#   Alphabet_ID \n");
		
		// Show top words in topics with proportions for the first document
		for (int topic = 0; topic < numTopics; topic++) {
			Iterator<IDSorter> iterator = topicSortedWords.get(topic).iterator();

			out = new Formatter(new StringBuilder(), Locale.US);  // TO print out to console
			outTopicsTextBox = new Formatter(new StringBuilder(), Locale.US);	// to print out to the list of topics
			out.format("%d    %.4f    ", topic, topicDistribution[topic]);
			outTopicsTextBox.format("%d   ", topic);
			int rank = 0;
			boolean AlreadyCheckThisTopic = false;
			while (iterator.hasNext() && rank < NumberOfWordsInATopic) {
				IDSorter idCountPair = iterator.next();
				out.format("%s (%.0f) ", dataAlphabet.lookupObject(idCountPair.getID()), idCountPair.getWeight());
				word = dataAlphabet.lookupObject(idCountPair.getID()).toString();
				if (   (rank < numOfWordConsiderInEachTopic) && (! AlreadyCheckThisTopic)    )
					if (topWordList.indexOf(" " + word + " ") >=0 ) {	// has this word already in topWordList
						NumSeparateTopWordTopics--;
						AlreadyCheckThisTopic = true;
					} else topWordList = topWordList + word + " ";
				outTopicsTextBox.format(word + "  ");
				
				rank++;
			}
			System.out.println(out);
			FAA3_GUI.JListOfTopics.add(topic + 1, outTopicsTextBox);
			// TopicModeling.txtrTopicsList.setText(TopicModeling.txtrTopicsList.getText() +
			// outTopicsTextBox + "\n");
		}
		System.out.println("");
		
		FAA3_GUI.NumberOfTopic = numTopics; 
		FAA3_GUI.listTopics.repaint();
		return NumSeparateTopWordTopics;
	}

}
