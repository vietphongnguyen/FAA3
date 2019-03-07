/**
 * 
 */
package phong.FAA_GUI;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import cc.mallet.types.IDSorter;

import javax.swing.event.ChangeEvent;
import javax.swing.JTextPane;


/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class SuggestedDocumentsForATopic extends JFrame{
	private JPanel contentPane;
	private JTextField textField;
	private JList listDocs;
	public DefaultListModel listModel = new DefaultListModel();
	private JTextField textField_MinProb;
	private JSpinner spinnerDigit;
	String format;
	DecimalFormat decimalFormat;
	private JLabel lblListOfWords;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;
	
	public SuggestedDocumentsForATopic() {
		
		initComponents();
		createEvents();
		initNumberFormat(4);
	}



	private void initNumberFormat(int exp) {
		
		format  = "#,##0.";
		do {
			exp --;
			format += "0";
		} while (exp > 0);
		
		
		decimalFormat = new DecimalFormat(format);
		
	}



	public SuggestedDocumentsForATopic(String searchText, String[] fileNames, double[] probs, int topic) {
		this();
		textField.setText(searchText);
		double minProb;
		try {
			minProb = Double.parseDouble(textField_MinProb.getText());
		} catch (NumberFormatException e1) {
			minProb = 0.005;
			e1.printStackTrace();
		}
		for (int i = 0; i< probs.length; i++) {
			try {
				if (probs[i]>= minProb)
					listModel.add(i, decimalFormat.format(probs[i]) + " : " + fileNames[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		printListOfWordsInTopic(topic);
		
	}


	private void printListOfWordsInTopic(int topic) {
		String output="";
		Iterator<IDSorter> iterator = generateTopics.topicSortedWords.get(topic).iterator();
		while (iterator.hasNext() ) {
			IDSorter idCountPair = iterator.next();
			output += generateTopics.dataAlphabet.lookupObject(idCountPair.getID()) + " (" + idCountPair.getWeight() + "), ";
			
		}
		
		textArea.setText(output);
	}



	private void createEvents() {
		spinnerDigit.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int exp;
				try {
					exp = (Integer) spinnerDigit.getValue();
				} catch (NumberFormatException e) {
					exp = 4;
					e.printStackTrace();
				}
				format  = "#,##0.";
				double min = 5;
				{
					min /=10;
					exp --;
					format += "0";
				} while (exp > 0);
				
				
				decimalFormat = new DecimalFormat(format);
				String numberAsString = decimalFormat.format(min);
				textField_MinProb.setText(numberAsString);
			}
		});
		
	}

	private void initComponents() {
		setTitle("Suggested Documents For A Topic");
		try {
			//setIconImage(Toolkit.getDefaultToolkit().getImage(autoDetectNumTopics.class.getResource("/Resources/icon2.png")));
			setIconImage(Toolkit.getDefaultToolkit().getImage("icons/icon2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
		setBounds(100, 100, 554, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSuggestedDocumentsFor = new JLabel("Suggested Documents for :");
		lblSuggestedDocumentsFor.setHorizontalAlignment(SwingConstants.LEFT);
		lblSuggestedDocumentsFor.setBounds(30, 11, 149, 14);
		contentPane.add(lblSuggestedDocumentsFor);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(183, 8, 321, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		listDocs = new JList(listModel);
		
		listDocs.setVisibleRowCount(10);
		JScrollPane scrollPane = new JScrollPane(listDocs);
		scrollPane.setBounds(30, 62, 474, 137);
		contentPane.add(scrollPane);
		
		JLabel lblMinimumProbabilityScore = new JLabel("Minimum probability score:");
		lblMinimumProbabilityScore.setHorizontalAlignment(SwingConstants.LEFT);
		lblMinimumProbabilityScore.setBounds(30, 36, 149, 14);
		contentPane.add(lblMinimumProbabilityScore);
		
		spinnerDigit = new JSpinner();
		spinnerDigit.setEnabled(false);
		
		spinnerDigit.setModel(new SpinnerNumberModel(4, 1, 8, 1));
		spinnerDigit.setBounds(466, 33, 38, 20);
		contentPane.add(spinnerDigit);
		
		textField_MinProb = new JTextField();
		textField_MinProb.setText("0.0005");
		textField_MinProb.setEditable(false);
		textField_MinProb.setBounds(183, 33, 86, 20);
		contentPane.add(textField_MinProb);
		textField_MinProb.setColumns(10);
		
		JLabel lblNumberOfDigit = new JLabel("number of digit after decimal");
		lblNumberOfDigit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfDigit.setBounds(300, 37, 163, 14);
		contentPane.add(lblNumberOfDigit);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setRows(14);
		
		scrollPane_1 = new JScrollPane(textArea);
		scrollPane_1.setBounds(29, 246, 475, 267);
		contentPane.add(scrollPane_1);
		
		lblListOfWords = new JLabel("List of words in the topic:");
		lblListOfWords.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfWords.setBounds(30, 221, 474, 14);
		contentPane.add(lblListOfWords);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuggestedDocumentsForATopic frame = new SuggestedDocumentsForATopic( );
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					FAA3_GUI.Outln(e.toString());
				}
			}
		});
	}
}
