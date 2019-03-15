/**
 * 
 */
package phong.FAA_GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class SuggestedDocumentsForATopic extends JFrame{
	private JPanel contentPane;
	private JTextField textField;
	private JList listDocs;
	public static DefaultListModel<String> listModel = new DefaultListModel<String>();
	public static JTextField textField_MinProb;
	private JSpinner spinnerDigit;
	String format;
	static DecimalFormat decimalFormat;
	private JLabel lblListOfWords;
	private JScrollPane scrollPane_1;
	static JTextArea textArea_words;
	private JTextField txtHttplocalhostsolrknowledgearchitecturehtml;
	private JButton btnNewButton;
	
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

		LDATopicsSearch.printSuggestedDocumentsForATopic(listModel, decimalFormat, fileNames, probs);
		LDATopicsSearch.printListOfWordsInTopic(topic,textArea_words);
		
	}

	
	private void createEvents() {
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GetRelatedTopicsOnSolr relatedTopicsOnSolr = new GetRelatedTopicsOnSolr();
				//setVisible(false);
			}
		});
		
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
		setBounds(0, 200, 800, 600);
		
		try {
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			//int x = (int) ((dimension.getWidth() - getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - getHeight()) ) - 50; // 50 is about the height of the window start bar
			if (y<0)  y =0;
			setLocation(0,y);
		} catch (HeadlessException e2) {}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSuggestedDocumentsFor = new JLabel("Suggested Documents for :");
		lblSuggestedDocumentsFor.setHorizontalAlignment(SwingConstants.LEFT);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(10);
		
		listDocs = new JList(listModel);
		
		listDocs.setVisibleRowCount(10);
		JScrollPane scrollPane = new JScrollPane(listDocs);
		
		JLabel lblMinimumProbabilityScore = new JLabel("Minimum probability score:");
		lblMinimumProbabilityScore.setHorizontalAlignment(SwingConstants.LEFT);
		
		spinnerDigit = new JSpinner();
		spinnerDigit.setEnabled(false);
		
		spinnerDigit.setModel(new SpinnerNumberModel(4, 1, 8, 1));
		
		textField_MinProb = new JTextField();
		textField_MinProb.setText("0.0005");
		textField_MinProb.setEditable(false);
		textField_MinProb.setColumns(10);
		
		JLabel lblNumberOfDigit = new JLabel("number of digit after decimal");
		lblNumberOfDigit.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textArea_words = new JTextArea();
		textArea_words.setLineWrap(true);
		textArea_words.setRows(10);
		
		scrollPane_1 = new JScrollPane(textArea_words);
		
		lblListOfWords = new JLabel("List of words in the topic:");
		lblListOfWords.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		
		JLabel lblNewLabel = new JLabel("Knowledge Architecture results on Solr");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtHttplocalhostsolrknowledgearchitecturehtml = new JTextField();
		txtHttplocalhostsolrknowledgearchitecturehtml.setHorizontalAlignment(SwingConstants.LEFT);
		txtHttplocalhostsolrknowledgearchitecturehtml.setText("http://localhost:8983/solr/knowledgeArchitecture.html");
		txtHttplocalhostsolrknowledgearchitecturehtml.setColumns(10);
		
		btnNewButton = new JButton("Get Related Topics on Solr");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(lblSuggestedDocumentsFor, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
					.addGap(27))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(lblMinimumProbabilityScore, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(textField_MinProb, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNumberOfDigit, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinnerDigit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(186))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
					.addGap(27))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
					.addGap(27))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(scrollPane_1))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addComponent(lblListOfWords, GroupLayout.PREFERRED_SIZE, 722, GroupLayout.PREFERRED_SIZE)))
					.addGap(27))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSuggestedDocumentsFor))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_MinProb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblMinimumProbabilityScore))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerDigit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblNumberOfDigit)))
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(lblListOfWords)
					.addGap(11)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
					.addGap(21)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtHttplocalhostsolrknowledgearchitecturehtml, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)
							.addGap(11)
							.addComponent(btnNewButton))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(txtHttplocalhostsolrknowledgearchitecturehtml, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
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
