package phong.FAA_GUI;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.topics.TopicInferencer;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;

public class printCompositionTable extends SwingWorker{

	public static List<RowSorter.SortKey> sortKeys;
	public static TableRowSorter<TableModel> sorter;
	String InputFolder;
	
	//Constructor
	public printCompositionTable(String text) {
		InputFolder = text;
	}

	private static String Get_File_Name(String string) {
		int vt = string.lastIndexOf('/');
		String filename = string.substring(vt+1);
		return filename;
	}
	
	private String alighTextWidth(int width, double d) {
		String s = String.format("%.3f", d);
		return alighTextWidth(width, s);
	}
	private String alighTextWidth(int width, int i) {
		String s = Integer.toString(i);
		return alighTextWidth(width, s);
	}
	private String alighTextWidth(int width, String string) {
		String s = string;
		if (width<=0) return s;
		while (s.length()<width) s = " " +s;
		while (s.length()>width) s = s.substring(1);
		return s;
	}
	@Override
	protected Object doInBackground() throws Exception {
		
/*		while (!TopicModeling.process_GenerateTopics.isDone() ) {	// wait until the process of generating topics is done.
			Thread.sleep(1000);
		}
		while (TopicModeling.ProcessAutoDetectNumberOfTopics_running || (!TopicModeling.threadProcessAutoDetectNumberOfTopics.isDone()) ) {	// wait until the process of 'ProcessAutoDetectNumberOfTopics' is done.
			Thread.sleep(1000);
		}*/
		
		FAA3_GUI.processPrintCompositionTable_running = true;
		// Print out the composition of the InstanceList
		
		// reset the TopicModeling.compositionTable. delete all the old value from previous run.
		FAA3_GUI.compositionTable.setRowCount(0);
		FAA3_GUI.compositionTable.setColumnCount(3);
		
		FAA3_GUI.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		for (int i=1;i<generateTopics.numTopics;i++)
		FAA3_GUI.compositionTable.addColumn("Topic "+i);
		FAA3_GUI.table.setAutoResizeMode(0);
		FAA3_GUI.table.getColumnModel().getColumn(0).setPreferredWidth(30);
		FAA3_GUI.table.getColumnModel().getColumn(0).setMaxWidth(100);
		FAA3_GUI.table.getColumnModel().getColumn(1).setPreferredWidth(250);
		FAA3_GUI.table.getColumnModel().getColumn(1).setMinWidth(100);
		FAA3_GUI.table.getColumnModel().getColumn(1).setMaxWidth(1000);
		for (int i = 0; i < generateTopics.numTopics; i++) {
			FAA3_GUI.table.getColumnModel().getColumn(2+i).setPreferredWidth(60);
			FAA3_GUI.table.getColumnModel().getColumn(2+i).setMinWidth(30);
			FAA3_GUI.table.getColumnModel().getColumn(2+i).setMaxWidth(100);
		}
		
        System.out.printf(" %80.75s","Composition of the InstanceList:");
        FAA3_GUI.Out(alighTextWidth(85,"Composition of the InstanceList:"));
        
        for (int i=0; i<generateTopics.numTopics; i++) {
       		System.out.printf(" %3d", i);
        	FAA3_GUI.Out(alighTextWidth(7, i));
        }
        System.out.println("\n");
        FAA3_GUI.Outln("\n");
        
        File[] files = new File(InputFolder).listFiles(); 		// If this pathname does not denote a directory, then listFiles() returns null.
        
        System.out.println("Printing composition table ...");
        FAA3_GUI.Outln("Printing composition table ... ");
		int MaxProgress = files.length;
        FAA3_GUI.progressBarMallet.setMaximum(MaxProgress);
		FAA3_GUI.progressBarMallet.setValue(0);
		FAA3_GUI.progressBarMallet.setStringPainted(true);
		long currentTime,traveredTime,remainingTime;
		String s;
        int count=0;
        
		long startTime = System.nanoTime(); // start counting time
		
		for (File file : files) {
			if (file.isFile()) {
				
				FAA3_GUI.progressBarMallet.setValue(count);
				double percent = 100 * count / MaxProgress;

				currentTime = System.nanoTime(); // get current time
				traveredTime = (currentTime - startTime);
				remainingTime = (long) (traveredTime * ((100 - percent) / percent));
				remainingTime = remainingTime / 1000000000; // converted from nanoseconds to second
				FAA3_GUI.progressBarMallet.setString("Doing " + (count) + " / " + MaxProgress + " ( " + percent
						+ " % )  Time remaining: " + remainingTime / 60 + " m " + remainingTime % 60 + " s");

				// Cancel the process by user
				if (FAA3_GUI.processPrintCompositionTable_running == false) {
					FAA3_GUI.Out("The process of printing the composition table had been canceled by user  \n");
					FAA3_GUI.btnGetCompositionTable.setEnabled(true);
					FAA3_GUI.btnEstimateTopics.setEnabled(true);
					// TopicModeling.processPrintCompositionTable_running = false;
					break;
				}

				Vector<String> row = new Vector<String>();
				System.out.print(count + "  ");
				FAA3_GUI.Out(alighTextWidth(5, count));
				row.add(StringWidthAlign(MaxProgress, Integer.toString(count)));

				s = file.getName();
				System.out.printf(" %80.75s ",s	);
				FAA3_GUI.Out(alighTextWidth(80, s));
				row.add(s);

				BufferedReader br = new BufferedReader(new FileReader(file));
				String st = "";
				String line;
				while ((line = br.readLine()) != null)
					st += line;
				br.close();

				StringBuilder textContentOfInstance = new StringBuilder();
				textContentOfInstance.append(st);
				InstanceList testing = new InstanceList(generateTopics.instances.getPipe());
				testing.addThruPipe(new Instance(textContentOfInstance.toString(), "", file.getName(), null));
				TopicInferencer inferencer = generateTopics.model.getInferencer();
				double[] testProbabilities = inferencer.getSampledDistribution(testing.get(0), generateTopics.numIterations, 1, 5);
				for (int i = 0; i < generateTopics.numTopics; i++) {
					s = String.format("%.3f", testProbabilities[i]);
					System.out.printf(s + "  ");
					FAA3_GUI.Out(alighTextWidth(7, s));
					row.add(s);
				}
				count++;
				System.out.println("");
				FAA3_GUI.Outln("");
				FAA3_GUI.compositionTable.addRow(row);

			}
		}
 
		System.out.println();

		// Highline the big value in the column
		for (int i=0; i<generateTopics.numTopics; i++) 
			FAA3_GUI.table.getColumnModel().getColumn(i+2).setCellRenderer(new HighlineCellRenderer(0.6f,0.19f));
		
		// Sort the table when click on column title 
		sorter = new TableRowSorter<TableModel>(FAA3_GUI.table.getModel());
		FAA3_GUI.table.setRowSorter(sorter);
        sortKeys = new ArrayList<>(count +3);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
		
		// Completed
		FAA3_GUI.progressBarMallet
				.setString("Done " + (count) + " / " + MaxProgress + " ( Printing composition table finished )");
		FAA3_GUI.btnCancelMallet.setEnabled(false);
		FAA3_GUI.btnGetCompositionTable.setEnabled(true);
		FAA3_GUI.btnEstimateTopics.setEnabled(true);

		FAA3_GUI.Outln("Printing composition table has been completed successfully !");
		FAA3_GUI.processPrintCompositionTable_running = false;
		
		// refresh the table to make sure the cell color take effect
		FAA3_GUI.table.repaint();
		
		
		return null;
	}

	private String StringWidthAlign(int maxProgress, String string) {
		String s = string;
		
		int sopt =0;
		int bac10 =1;
		do {
			bac10 *=10;
			sopt ++;
		}
		while (bac10 < maxProgress);   	// Example: maxProgress = 120 => bac10 = 1000	=> sopt= 3  => length of string will be fixed to 3.
			
			
		
		while (s.length() < sopt) s = "0" + s;	// s="0xx"
		
		return s;
	}
}
