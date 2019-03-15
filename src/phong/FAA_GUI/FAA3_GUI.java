package phong.FAA_GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.types.Alphabet;
import cc.mallet.types.IDSorter;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;
import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import edu.mit.jwi.morph.WordnetStemmer;

import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.RowSorter;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.SortOrder;
import javax.swing.ListModel;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JPopupMenu;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class FAA3_GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	public static JButton btnExtractTextContents;
	public static JTextArea txtrCdata;
	public static JTextArea console;
	public static JProgressBar progressBar;
	public static createTextDataUsingTika process_createTextDataUsingTika;
	public static generateTopics process_GenerateTopics;
	public static printCompositionTable processPrintCompositionTable;
	public static boolean process_GenerateTopics_running = false;
	public static boolean processPrintCompositionTable_running = false;
	public static JButton btnXCancel;
	public static JButton btnGetFiles;
	@SuppressWarnings("rawtypes")
	public static JList list;

	@SuppressWarnings("rawtypes")
	public static DefaultListModel JListOfFiles;
	private JTextField txtDatatextfolder;
	private JToggleButton tglbtnUsedefaultdatatext;
	static JTextField txtTextdatafolder;
	private JLayeredPane layeredPane_1;
	static JTextField textAlphaSum;
	static JTextField textBeta;
	public static JButton btnEstimateTopics;
	static JButton btnCancelMallet;
	public static JProgressBar progressBarMallet;
	static JSpinner spinnerNumOfIterations;
	static JSpinner spinnerNumTopics;
	static JSpinner spinnerNumThreards;
	static JSpinner spinnerNumWordsInTopic;
	public static JTable table;
	@SuppressWarnings("rawtypes")
	public static JList listTopics;

	@SuppressWarnings("rawtypes")
	public static DefaultListModel JListOfTopics, JListOfTopicsWordnet, JListOfTopicsWordnetParent;
	public static DefaultTableModel compositionTable;
	public static JButton btnGetCompositionTable;
	private JMenuItem mntmRestartThisApplication;
	private JMenuItem mntmExit;
	private JMenuItem mntmPreferences;
	private JMenuItem mntmClose;
	private JMenuItem mntmSaveAs;
	private JMenuItem mntmSave;
	private JMenuItem mntmOpen;
	private JMenuItem mntmNew;
	private JMenuItem mntmClearConsole;
	private JMenuItem mntmTurnOff;
	private JMenuItem mntmExtractTextContents;
	private JMenuItem mntmOption;
	private JMenuItem mntmEstimateTopicsUsing;
	private JMenuItem mntmGetCompositionTable;
	private JMenuItem mntmOptions;
	private JMenuItem mntmGetHelp;
	private JMenuItem mntmUpdate;
	private JMenuItem mntmAbout;
	private JTabbedPane tabbedPane;
	private JMenuItem mntmPrintTheComposition;
	private JMenuItem mntmPrintTheList;
	private JMenuItem mntmAutomaticallyDetectAppropriate;
	private JButton btnAutoDetectNumber;
	
	private JTextField textFieldCompositionTable;
	private JScrollPane scrollPane_4;
	
	public static boolean NoConsoleOutput;

	public static processAutoDetectNumberOfTopics threadProcessAutoDetectNumberOfTopics;

	public static boolean ProcessAutoDetectNumberOfTopics_running;
	
	static int NumberOfTopic;

	static int SizeOfInstance;
	autoDetectNumTopics autoTopic;
	private JTextField textField;
	private JButton btnGetStemWords;
	private JSpinner spinnerNumWordTipicWordnet;
	private JList listWordnet;
	
	static IDictionary dict;
	public static final int MAX_DEPTH_OF_HIERARCHY = 16;
	private static WordnetStemmer wordnetStemmer;
	private JTextField txtDatatextstem;
	private JSpinner spinnerParentUpLevel;
	private JList listTopicsWordnetParent;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JMenu menu;
	private JMenuItem mntmSaveAllText;
	public static JCheckBox chckbxEnglishOnly;
	private JTextField txtContentstopwordstxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	static JTextField txSearchTopic;
	public static JCheckBoxMenuItem chckbxmntmAutoClearAfter;
	public static JButton btnSearchTopics;
	private JButton btnEstimateTopicsSimilar;
	private JButton btnGetScore;
	static JTextField txtCfaamainphrases;
	static JCheckBox checkBox_LetterOnly;
	private JSpinner spinner_fromCharacter;
	private JSpinner spinner_ToCharacter;
	static JCheckBox chckbxAlsoParseThe;
	static JComboBox comboBox_FromPage;
	static JComboBox comboBox_ToPage;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	static JComboBox comboBox_MaxLength;
	static JCheckBox chckbxRemoveNormalSize;
	private JButton btnNewButton;
	static JList list_3;
	private JButton button_4;
	static JComboBox comboBox_TopicSearch;
	static DefaultListModel listPhrases;
	public static String[] modelContent;
	FindTopicsByTitlePhrases2 findTopicsByTitlePhrases2;
	static JTextField textField_RelatedPhrases;
	static JTextField textField_LDARelatedTopics;
	private JTextField txtHTMLFile;
	LDATopicsSearch lDATopicsSearch = new LDATopicsSearch();
	static JButton btnShowResults;
	static JButton btnEstimateTopics_1;
	static JTextArea textArea_LDATopicWords;

	public static DefaultListModel<String> listModel_LDATopicsSearch;
	private JButton btnGetRelatedTopics;
	private JComboBox comboBox_2;
	public static DefaultListModel listSolr;
	static JScrollPane scrollPane_11;
	static JList list_5;
	
	
	public void InitWordnet(String wnhome) throws IOException {

		// construct the URL to the Wordnet dictionary directory
		String path = wnhome + File.separator + "dict";
		URL url = null;
		try {
			url = new URL("file", null, path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (url == null)
			return;

		// construct the dictionary object and open it
		dict = new Dictionary(url);
		dict.open();
		wordnetStemmer = new WordnetStemmer(dict);

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {
		
		try {
			//	setIconImage(Toolkit.getDefaultToolkit().getImage(FAA3_GUI.class.getResource("/Resources/icon2.png")));
			setIconImage(Toolkit.getDefaultToolkit().getImage("icons/icon2.png"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		setTitle("FAA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1200, 700);
		
		try {
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - getHeight()) / 2);
			setLocation(x, 0);
		} catch (HeadlessException e2) {}
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);

		mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);

		JMenu mnOpenRecently = new JMenu("Open recently");
		mnFile.add(mnOpenRecently);

		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);

		mntmSaveAs = new JMenuItem("Save as ...");
		mnFile.add(mntmSaveAs);

		mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		mntmPreferences = new JMenuItem("Preferences");
		mnFile.add(mntmPreferences);

		JSeparator separator_16 = new JSeparator();
		mnFile.add(separator_16);

		mntmRestartThisApplication = new JMenuItem("Restart this application ...");

		mnFile.add(mntmRestartThisApplication);

		JSeparator separator_12 = new JSeparator();
		mnFile.add(separator_12);

		mntmExit = new JMenuItem("Exit");

		mnFile.add(mntmExit);

		JMenu mnConsole = new JMenu("Console");
		menuBar.add(mnConsole);

		mntmClearConsole = new JMenuItem("Clear console");

		mnConsole.add(mntmClearConsole);
		
		chckbxmntmAutoClearAfter = new JCheckBoxMenuItem("Auto clear before each estimation");
		chckbxmntmAutoClearAfter.setSelected(true);
		mnConsole.add(chckbxmntmAutoClearAfter);
		
		JCheckBoxMenuItem chckbxmntmAutoSaveConsole = new JCheckBoxMenuItem("Auto save console before each estimation");
		chckbxmntmAutoSaveConsole.setSelected(true);
		mnConsole.add(chckbxmntmAutoSaveConsole);
		
		JSeparator separator_29 = new JSeparator();
		mnConsole.add(separator_29);
		
		mntmSaveAllText = new JMenuItem("Save all text in console to a text file");
		
		mnConsole.add(mntmSaveAllText);
		
		JSeparator separator_28 = new JSeparator();
		mnConsole.add(separator_28);

		mntmTurnOff = new JMenuItem("Turn off");

		mnConsole.add(mntmTurnOff);

		JMenu mnImportLectures = new JMenu("Extract documents");
		menuBar.add(mnImportLectures);

		mntmExtractTextContents = new JMenuItem("Extract text contents from folder");

		mnImportLectures.add(mntmExtractTextContents);

		JSeparator separator_15 = new JSeparator();
		mnImportLectures.add(separator_15);

		mntmOption = new JMenuItem("Option ...");
		mnImportLectures.add(mntmOption);

		JMenu mnEstimateTopics = new JMenu("Estimate topics");
		menuBar.add(mnEstimateTopics);
		
		mntmAutomaticallyDetectAppropriate = new JMenuItem("Automatically detect appropriate number of topics");
		
		mnEstimateTopics.add(mntmAutomaticallyDetectAppropriate);
		
		JSeparator separator_18 = new JSeparator();
		mnEstimateTopics.add(separator_18);

		mntmEstimateTopicsUsing = new JMenuItem("Estimate topics using Mallet");

		mnEstimateTopics.add(mntmEstimateTopicsUsing);

		mntmGetCompositionTable = new JMenuItem("Get new composition table from folder");

		mnEstimateTopics.add(mntmGetCompositionTable);

		JSeparator separator_14 = new JSeparator();
		mnEstimateTopics.add(separator_14);
		
		mntmPrintTheList = new JMenuItem("Print the list of topics (send to printer device)");
		
		mnEstimateTopics.add(mntmPrintTheList);

		mntmPrintTheComposition = new JMenuItem("Print the composition table result (send to printer devide)");

		mnEstimateTopics.add(mntmPrintTheComposition);

		JSeparator separator_17 = new JSeparator();
		mnEstimateTopics.add(separator_17);

		mntmOptions = new JMenuItem("Options ...");
		mnEstimateTopics.add(mntmOptions);
		
		JMenu mnLexicalDatabase = new JMenu("Lexical database");
		menuBar.add(mnLexicalDatabase);
		
		JMenu mnAutomateTask = new JMenu("Automate tasks");
		menuBar.add(mnAutomateTask);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmGetHelp = new JMenuItem("Get help");
		mnHelp.add(mntmGetHelp);

		JSeparator separator_1 = new JSeparator();
		mnHelp.add(separator_1);

		mntmUpdate = new JMenuItem("Check for updates");
		mnHelp.add(mntmUpdate);

		JSeparator separator_2 = new JSeparator();
		mnHelp.add(separator_2);

		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		console = new JTextArea(6, 0);
		console.setText(" Output console text: \n ");

		JScrollPane scrollPane = new JScrollPane(console);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1173, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1173, Short.MAX_VALUE))
					.addGap(1))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addGap(20))
		);

		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Text parsing", null, layeredPane, null);

		JLabel lblFilesAndDocuments = new JLabel("Files and documents");
		lblFilesAndDocuments.setFont(new Font("Tahoma", Font.BOLD, 11));

		JScrollPane scrollPane_1 = new JScrollPane();

		txtrCdata = new JTextArea();

		scrollPane_1.setViewportView(txtrCdata);
		txtrCdata.setBackground(Color.WHITE);
		txtrCdata.setToolTipText("Please input the the folder where you want to get documents and files");
		txtrCdata.setText("C:\\FAA3\\data");

		JButton btnNew = new JButton("New");
		btnNew.setEnabled(false);

		JButton btnCheck = new JButton("Check");
		btnCheck.setEnabled(false);

		JButton btnBrowse = new JButton("Browse...");
		btnBrowse.setEnabled(false);

		JButton btnSave = new JButton("Save");
		btnSave.setEnabled(false);

		JScrollPane scrollPane_2 = new JScrollPane();

		JListOfFiles = new DefaultListModel();
		list = new JList(JListOfFiles);
		scrollPane_2.setViewportView(list);

		btnGetFiles = new JButton("Get files >>");
		btnGetFiles.setEnabled(false);

		btnExtractTextContents = new JButton("Extract text contents");

		progressBar = new JProgressBar();

		btnXCancel = new JButton("Cancel");
		btnXCancel.setEnabled(false);

		try {
			//btnXCancel.setIcon(new ImageIcon(FAA3_GUI.class.getResource("/Resources/cancel6_16x16.png")));
			btnXCancel.setIcon(new ImageIcon("icons/cancel6_16x16.png","Cancel"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		txtDatatextfolder = new JTextField();

		txtDatatextfolder.setEditable(false);
		txtDatatextfolder.setText("C:\\FAA3\\data_text");
		txtDatatextfolder.setColumns(10);

		tglbtnUsedefaultdatatext = new JToggleButton("");

		tglbtnUsedefaultdatatext.setSelected(true);

		JLabel lblDefaultOutputText = new JLabel("Use default output text folder:");
		lblDefaultOutputText.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDefaultOutputText.setHorizontalAlignment(SwingConstants.RIGHT);
		
		chckbxEnglishOnly = new JCheckBox("Extract English documents only");
		chckbxEnglishOnly.setEnabled(false);
		
		JCheckBox chckbxRemoveSpecialCharacters = new JCheckBox("Remove special characters and double space");
		
		JCheckBox chckbxRemoveStopwords = new JCheckBox("Remove Stopwords");
		
		txtContentstopwordstxt = new JTextField();
		txtContentstopwordstxt.setEnabled(false);
		txtContentstopwordstxt.setText("ContentStopwords.txt");
		txtContentstopwordstxt.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblFilesAndDocuments, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblDefaultOutputText, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(tglbtnUsedefaultdatatext, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtDatatextfolder, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(chckbxEnglishOnly))
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_layeredPane.createSequentialGroup()
									.addComponent(chckbxRemoveStopwords, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtContentstopwordstxt, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
								.addComponent(chckbxRemoveSpecialCharacters, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnXCancel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_layeredPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_layeredPane.createSequentialGroup()
									.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addGap(20)
									.addComponent(btnCheck, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addGap(41)
									.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane.createSequentialGroup()
									.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnGetFiles, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
							.addGap(48)
							.addComponent(btnExtractTextContents, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFilesAndDocuments, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGap(7)
							.addComponent(lblDefaultOutputText, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGap(4)
							.addComponent(tglbtnUsedefaultdatatext, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGap(4)
							.addGroup(gl_layeredPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDatatextfolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxEnglishOnly))))
					.addGap(10)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_layeredPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_layeredPane.createSequentialGroup()
								.addComponent(chckbxRemoveSpecialCharacters)
								.addGap(15)
								.addGroup(gl_layeredPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(chckbxRemoveStopwords)
									.addComponent(txtContentstopwordstxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(11)
								.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addGap(32))))
					.addGap(10)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNew)
						.addComponent(btnCheck)
						.addComponent(btnBrowse)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGap(1)
							.addComponent(btnExtractTextContents)))
					.addGap(10)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGetFiles)
						.addComponent(btnSave))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnXCancel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		checkBox_LetterOnly = new JCheckBox("Words with letter only");
		
		checkBox_LetterOnly.setSelected(true);
		
		JLabel lblOnlyFrom = new JLabel("From ");
		lblOnlyFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinner_fromCharacter = new JSpinner();
		spinner_fromCharacter.setModel(new SpinnerNumberModel(2, 1, 3, 1));
		
		JLabel lblTo = new JLabel("To");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		
		spinner_ToCharacter = new JSpinner();
		spinner_ToCharacter.setModel(new SpinnerNumberModel(30, 10, 150, 10));
		
		JLabel lblCharacters = new JLabel("Characters");
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(12)
					.addComponent(checkBox_LetterOnly, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOnlyFrom, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(spinner_fromCharacter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner_ToCharacter, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCharacters, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_LetterOnly)
						.addComponent(lblOnlyFrom)
						.addComponent(lblCharacters)
						.addComponent(spinner_fromCharacter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTo)
						.addComponent(spinner_ToCharacter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		chckbxAlsoParseThe = new JCheckBox("Parse the header and title phrases");
		
		chckbxAlsoParseThe.setSelected(true);
		
		txtCfaamainphrases = new JTextField();
		txtCfaamainphrases.setText("C:\\FAA3\\main_phrases");
		txtCfaamainphrases.setColumns(10);
		
		JLabel lblMaximumLevelOf = new JLabel("Max level of text size");
		
		comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"auto", "4", "6", "8 ", "any"}));
		
		JLabel lblMaximumWordsFor = new JLabel("Max words for a phrases");
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"auto", "10", "15", "20", "any"}));
		comboBox_1.setEditable(true);
		
		JLabel lblFromPageNumber = new JLabel("From page number");
		
		comboBox_FromPage = new JComboBox();
		comboBox_FromPage.setModel(new DefaultComboBoxModel(new String[] {"auto", "1", "2", "3"}));
		comboBox_FromPage.setEditable(true);
		
		JLabel lblToPageNumber = new JLabel("To page number");
		
		comboBox_ToPage = new JComboBox();
		comboBox_ToPage.setModel(new DefaultComboBoxModel(new String[] {"auto", "5", "10", "15", "20", "30", "40", "50", "100", "all"}));
		comboBox_ToPage.setEditable(true);
		
		JLabel lblMaxCharacterOf = new JLabel("Max character of a phrase");
		lblMaxCharacterOf.setHorizontalAlignment(SwingConstants.LEFT);
		
		comboBox_MaxLength = new JComboBox();
		comboBox_MaxLength.setEditable(true);
		comboBox_MaxLength.setModel(new DefaultComboBoxModel(new String[] {"auto", "60", "80", "100", "120", "140", "any"}));
		
		chckbxRemoveNormalSize = new JCheckBox("Remove normal size text");
		chckbxRemoveNormalSize.setSelected(true);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(chckbxAlsoParseThe, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCfaamainphrases, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(lblMaximumLevelOf, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel_3.createSequentialGroup()
											.addComponent(comboBox_MaxLength, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(chckbxRemoveNormalSize, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_3.createSequentialGroup()
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblMaximumWordsFor, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(lblFromPageNumber, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(comboBox_FromPage, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addGap(24)
									.addComponent(lblToPageNumber, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(comboBox_ToPage, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)))
							.addGap(33))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblMaxCharacterOf, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxAlsoParseThe)
						.addComponent(txtCfaamainphrases, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFromPageNumber, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(3)
							.addComponent(comboBox_FromPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblToPageNumber, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_ToPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMaximumLevelOf, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMaximumWordsFor, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaxCharacterOf)
						.addComponent(comboBox_MaxLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxRemoveNormalSize))
					.addGap(20))
		);
		panel_3.setLayout(gl_panel_3);
		layeredPane.setLayout(gl_layeredPane);

		layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("LDA Topics estimation", null, layeredPane_1, null);

		JLabel lblNewLabel = new JLabel("Text data folder:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		txtTextdatafolder = new JTextField();
		
		txtTextdatafolder.setText("C:\\FAA3\\data_text_full");
		txtTextdatafolder.setColumns(10);

		JLabel lblNumberOfTopics = new JLabel("Number of topics:");
		lblNumberOfTopics.setHorizontalAlignment(SwingConstants.RIGHT);

		spinnerNumTopics = new JSpinner();
		spinnerNumTopics.setModel(new SpinnerNumberModel(5, 1, 5000, 1));

		JLabel lblParalleltopicmodel = new JLabel("Parallel Topic Model");
		lblParalleltopicmodel.setHorizontalAlignment(SwingConstants.CENTER);

		JSeparator separator_3 = new JSeparator();

		JSeparator separator_4 = new JSeparator();

		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);

		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);

		JSeparator separator_11 = new JSeparator();

		JLabel lblThreads = new JLabel("# threads:");
		lblThreads.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblThreads.setHorizontalAlignment(SwingConstants.RIGHT);

		spinnerNumThreards = new JSpinner();
		spinnerNumThreards.setToolTipText("Example, if we use 2 parallel samplers, which each look at one half the corpus and combine");
		spinnerNumThreards.setModel(new SpinnerNumberModel(1, 1, 20, 1));

		JLabel lblAlphasum = new JLabel("alphaSum:");
		lblAlphasum.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAlphasum.setHorizontalAlignment(SwingConstants.RIGHT);

		textAlphaSum = new JTextField();
		textAlphaSum.setToolTipText("parameter is passed as the sum over topics");
		textAlphaSum.setText("1.0");
		textAlphaSum.setColumns(10);

		JLabel lblBeta = new JLabel("beta:");
		lblBeta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblBeta.setHorizontalAlignment(SwingConstants.RIGHT);

		textBeta = new JTextField();
		textBeta.setToolTipText("the parameter for a single dimension of the Dirichlet prior");
		textBeta.setText("0.01");
		textBeta.setColumns(10);

		JLabel lblNumIterations = new JLabel("Numbe of iterations:");
		lblNumIterations.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumIterations.setHorizontalAlignment(SwingConstants.RIGHT);

		spinnerNumOfIterations = new JSpinner();
		
		spinnerNumOfIterations.setToolTipText("for testing only: you could use about 50 iterations , for real applications: use 1000 to 2000 iterations");
		spinnerNumOfIterations.setModel(new SpinnerNumberModel(200, 1, 10000, 1));

		btnEstimateTopics = new JButton("Estimate topics");

		btnEstimateTopics.setFont(new Font("Tahoma", Font.BOLD, 12));

		progressBarMallet = new JProgressBar();

		btnCancelMallet = new JButton("Cancel");

		try {
			//btnCancelMallet.setIcon(new ImageIcon(FAA3_GUI.class.getResource("/Resources/cancel6_16x16.png")));
			btnCancelMallet.setIcon(new ImageIcon("icons/cancel6_16x16.png","cancel"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		btnCancelMallet.setEnabled(false);

		JLabel lblNumberOfTop = new JLabel("Number of Top Words in a topic:");
		lblNumberOfTop.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNumberOfTop.setHorizontalAlignment(SwingConstants.RIGHT);

		spinnerNumWordsInTopic = new JSpinner();

		spinnerNumWordsInTopic.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(1)));

		JList list_1 = new JList();
		list_1.setVisibleRowCount(1);
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});

		scrollPane_4 = new JScrollPane();

		compositionTable = new DefaultTableModel(new Object[][] {

		}, new String[] { "#", "Composition of the InstanceList:", "Topic 0" });
		table = new JTable(compositionTable);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		scrollPane_4.setViewportView(table);

		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(1000);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setMinWidth(10);
		table.getColumnModel().getColumn(2).setMaxWidth(100);

		JScrollPane scrollPane_3 = new JScrollPane();

		JListOfTopics = new DefaultListModel();
		listTopics = new JList(JListOfTopics);

		listTopics.setSelectedIndex(0);
		scrollPane_3.setViewportView(listTopics);

		btnGetCompositionTable = new JButton("Get composition table from new folder");
		btnGetCompositionTable.setEnabled(false);
		
		btnAutoDetectNumber = new JButton("Auto detect");
		btnAutoDetectNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		textFieldCompositionTable = new JTextField();
		textFieldCompositionTable.setText("data_text");
		textFieldCompositionTable.setColumns(10);
		
		JLabel lblOptimizeinterval = new JLabel("optimize-interval");
		lblOptimizeinterval.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(10, 0, 1000, 1));
		spinner.setToolTipText("--optimize-interval [NUMBER]\u00A0This option turns on hyperparameter optimization, which allows the model to better fit the data by allowing some topics to be more prominent than others. Optimization every 10 iterations is reasonable.");
		
		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(20, 0, 2000, 1));
		spinner_1.setToolTipText("--optimize-burn-in [NUMBER]\u00A0The number of iterations before hyperparameter optimization begins. Default is twice the optimize interval.");
		
		JLabel lblBurnin = new JLabel("burn-in");
		lblBurnin.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton button_2 = new JButton("...");
		button_2.setToolTipText("Full range of possible parameters of train-topics ");
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lblSerialPipe = new JLabel("Serial Pipe");
		lblSerialPipe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSerialPipe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSerialPipe.setBounds(10, 0, 350, 24);
		panel_2.add(lblSerialPipe);
		
		JSeparator separator_30 = new JSeparator();
		separator_30.setBounds(10, 22, 350, 2);
		panel_2.add(separator_30);
		GroupLayout gl_layeredPane_1 = new GroupLayout(layeredPane_1);
		gl_layeredPane_1.setHorizontalGroup(
			gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane_1.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtTextdatafolder, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addComponent(lblNumberOfTopics, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(spinnerNumTopics, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnAutoDetectNumber, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblThreads, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
							.addGap(1)
							.addComponent(spinnerNumThreards, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(190)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(132)
									.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(14)
									.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(139)
									.addComponent(textBeta, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblAlphasum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(10)
									.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(218)
									.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addGap(169)
							.addComponent(lblBurnin, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_11, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addGap(221)
							.addComponent(lblBeta, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addGap(27)
							.addComponent(lblOptimizeinterval, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addGap(182)
							.addComponent(textAlphaSum, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addGap(10)
							.addComponent(lblParalleltopicmodel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addGap(131)
							.addComponent(spinnerNumOfIterations, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNumIterations, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEstimateTopics, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(117)
									.addComponent(lblNumberOfTop, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
							.addComponent(spinnerNumWordsInTopic, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE))
					.addGap(42))
				.addGroup(gl_layeredPane_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
					.addGap(21)
					.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_layeredPane_1.createSequentialGroup()
					.addComponent(progressBarMallet, GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
					.addComponent(btnCancelMallet, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_layeredPane_1.createSequentialGroup()
					.addGap(570)
					.addComponent(btnGetCompositionTable, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(368, Short.MAX_VALUE))
		);
		gl_layeredPane_1.setVerticalGroup(
			gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane_1.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel))
								.addComponent(txtTextdatafolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(4)
									.addComponent(lblNumberOfTopics))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(1)
									.addComponent(spinnerNumTopics, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnAutoDetectNumber))
							.addGap(2)
							.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(7)
									.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_layeredPane_1.createSequentialGroup()
											.addGap(18)
											.addComponent(lblThreads, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
										.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(22)
									.addComponent(spinnerNumThreards, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(7)
									.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_layeredPane_1.createSequentialGroup()
											.addGap(14)
											.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_layeredPane_1.createSequentialGroup()
											.addGap(41)
											.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_layeredPane_1.createSequentialGroup()
											.addGap(15)
											.addComponent(textBeta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_layeredPane_1.createSequentialGroup()
											.addGap(18)
											.addComponent(lblAlphasum, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_layeredPane_1.createSequentialGroup()
											.addGap(41)
											.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(50)
									.addComponent(lblBurnin))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(7)
									.addComponent(separator_11, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(25)
									.addComponent(lblBeta, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(50)
									.addComponent(lblOptimizeinterval))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(22)
									.addComponent(textAlphaSum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblParalleltopicmodel))
							.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(7)
									.addComponent(spinnerNumOfIterations, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(7)
									.addComponent(lblNumIterations, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEstimateTopics)
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(9)
									.addComponent(lblNumberOfTop, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addGap(1)
									.addComponent(spinnerNumWordsInTopic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_layeredPane_1.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layeredPane_1.createSequentialGroup()
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))))
					.addGap(9)
					.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
						.addComponent(progressBarMallet, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelMallet, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnGetCompositionTable))
		);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(10, 35, 314, 124);
		panel_2.add(scrollPane_8);
		
		JList list_2 = new JList();
		list_2.setModel(new AbstractListModel() {
			String[] values = new String[] {"Input2CharSequence(\"UTF-8\")", "CharSequence2TokenSequence(\"\\\\w+\")", "TokenSequenceLowercase()", "TokenSequenceRemoveStopwords( \"enlish_stopwords.txt\")", "TokenSequence2FeatureSequence()", "Target2Label()"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_8.setViewportView(list_2);
		list_2.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JButton button = new JButton("");
		button.setBounds(334, 35, 26, 50);
		panel_2.add(button);
		try {
			// button.setIcon(new ImageIcon(FAA3_GUI.class.getResource("/Resources/Up4.png")));
			button.setIcon(new ImageIcon("icons/Up4.png", "Up"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		JButton button_1 = new JButton("");
		button_1.setBounds(334, 109, 26, 50);
		panel_2.add(button_1);
		try {
			// button_1.setIcon(new ImageIcon(FAA3_GUI.class.getResource("/Resources/down4.png")));
			button_1.setIcon(new ImageIcon("icons/down4.png","down"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		panel_1.setLayout(null);
		
		JLabel lblTopicSearch = new JLabel("Topic Search:");
		lblTopicSearch.setBounds(6, 8, 121, 16);
		lblTopicSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTopicSearch.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblTopicSearch);
		
		btnSearchTopics = new JButton("Search topics in the Top Words List");
		btnSearchTopics.setEnabled(false);
		
		btnSearchTopics.setBounds(103, 5, 245, 23);
		btnSearchTopics.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(btnSearchTopics);
		
		txSearchTopic = new JTextField();
		txSearchTopic.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				btnGetScore.setEnabled(false);
				
			}
		});
		txSearchTopic.setHorizontalAlignment(SwingConstants.CENTER);
		txSearchTopic.setBounds(6, 33, 342, 22);
		txSearchTopic.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txSearchTopic.setText("weather condition");
		txSearchTopic.setColumns(32);
		panel_1.add(txSearchTopic);
		
		btnEstimateTopicsSimilar = new JButton("Estimate topics using LDA");
		
		btnEstimateTopicsSimilar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEstimateTopicsSimilar.setBounds(6, 66, 250, 23);
		panel_1.add(btnEstimateTopicsSimilar);
		
		btnGetScore = new JButton("Get Score");
		btnGetScore.setEnabled(false);
		
		btnGetScore.setBounds(255, 66, 93, 23);
		panel_1.add(btnGetScore);
		
		btnNewButton = new JButton("Find related topics using title phrases");
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(6, 91, 250, 23);
		panel_1.add(btnNewButton);
		panel.setLayout(null);
		
		JLabel label = new JLabel("N-gram");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(10, 0, 338, 24);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
		
		JRadioButton rdbtnUnigram = new JRadioButton("unigram");
		rdbtnUnigram.setBounds(6, 31, 83, 23);
		rdbtnUnigram.setSelected(true);
		buttonGroup.add(rdbtnUnigram);
		panel.add(rdbtnUnigram);
		
		JRadioButton rdbtnBigram = new JRadioButton("bigram");
		rdbtnBigram.setBounds(91, 31, 73, 23);
		buttonGroup.add(rdbtnBigram);
		panel.add(rdbtnBigram);
		
		JRadioButton rdbtnTrigram = new JRadioButton("trigram");
		rdbtnTrigram.setBounds(181, 31, 82, 23);
		buttonGroup.add(rdbtnTrigram);
		panel.add(rdbtnTrigram);
		
		JRadioButton rdbtngram = new JRadioButton("4-gram");
		rdbtngram.setBounds(265, 31, 83, 23);
		buttonGroup.add(rdbtngram);
		panel.add(rdbtngram);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(10, 22, 338, 2);
		panel.add(separator_7);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		scrollPane_4.setColumnHeaderView(popupMenu_1);
		
		menu = new JMenu("Print this table on the printer");
		
		popupMenu_1.add(menu);
		layeredPane_1.setLayout(gl_layeredPane_1);
		layeredPane_1.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { txtTextdatafolder,
				spinnerNumTopics, spinnerNumThreards, textAlphaSum, textBeta, spinnerNumOfIterations,
				spinnerNumWordsInTopic, btnEstimateTopics, btnCancelMallet, btnGetCompositionTable, list_1 }));

		JLayeredPane layeredPaneSearchTopic = new JLayeredPane();
		tabbedPane.addTab("Search Related Topics", null, layeredPaneSearchTopic, null);
		
		JPanel panel_5 = new JPanel();
		
		JPanel panel_6 = new JPanel();
		GroupLayout gl_layeredPaneSearchTopic = new GroupLayout(layeredPaneSearchTopic);
		gl_layeredPaneSearchTopic.setHorizontalGroup(
			gl_layeredPaneSearchTopic.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
				.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
		);
		gl_layeredPaneSearchTopic.setVerticalGroup(
			gl_layeredPaneSearchTopic.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPaneSearchTopic.createSequentialGroup()
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_10 = new JPanel();
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblSolrKnowledgeArchitecture = new JLabel("Solr Knowledge Architecture");
		lblSolrKnowledgeArchitecture.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnGetRelatedTopics = new JButton("Get Related Topics on Solr");
		
		
		JLabel label_7 = new JLabel("Solr HTML output");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtHTMLFile = new JTextField();
		txtHTMLFile.setText("weather condition.html");
		txtHTMLFile.setColumns(10);
		
		JLabel label_8 = new JLabel("Solr server");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"http://localhost:8983/solr/knowledgeArchitecture.html", "http://130.101.10.139:8983/solr/knowledgeArchitecture.html", "http://"}));
		comboBox_2.setEditable(true);
		
		
		listSolr = new DefaultListModel();
		JList list_4 = new JList(listSolr);
		JScrollPane scrollPane_10 = new JScrollPane(list_4);
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_9.createSequentialGroup()
							.addComponent(lblSolrKnowledgeArchitecture, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGetRelatedTopics, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
						.addGroup(gl_panel_9.createSequentialGroup()
							.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtHTMLFile, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
						.addGroup(gl_panel_9.createSequentialGroup()
							.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_2, 0, 297, Short.MAX_VALUE))))
				.addComponent(scrollPane_10, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSolrKnowledgeArchitecture)
						.addComponent(btnGetRelatedTopics))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(txtHTMLFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_8)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_10, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		
		
		panel_9.setLayout(gl_panel_9);
		
		JLabel lblEstimateTopicsUsing = new JLabel("Estimate topics using LDA");
		lblEstimateTopicsUsing.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnEstimateTopics_1 = new JButton("Start to Estimate Topics");
		btnEstimateTopics_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lDATopicsSearch.actionPerformed_EstimateTopicsSearch();
			}
		});
		
		JLabel label_4 = new JLabel("Related to:");
		
		textField_LDARelatedTopics = new JTextField();
		textField_LDARelatedTopics.setEditable(false);
		textField_LDARelatedTopics.setColumns(10);
		
		JLabel label_5 = new JLabel("List of words:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		textArea_LDATopicWords = new JTextArea();
		textArea_LDATopicWords.setLineWrap(true);
		
		JScrollPane scrollPane_9 = new JScrollPane(textArea_LDATopicWords);
		
		btnShowResults = new JButton("Show More Detail Results");
		btnShowResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LDATopicsSearch.suggestedDocumentsForATopic.setVisible(true);
			}
		});
		btnShowResults.setEnabled(false);
		
		
		listModel_LDATopicsSearch = new DefaultListModel();
		list_5 = new JList(listModel_LDATopicsSearch);
		scrollPane_11 = new JScrollPane(list_5);
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_11, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
						.addComponent(scrollPane_9, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_8.createSequentialGroup()
							.addComponent(lblEstimateTopicsUsing, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEstimateTopics_1, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_8.createSequentialGroup()
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_LDARelatedTopics, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_8.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(97)
							.addComponent(btnShowResults, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstimateTopicsUsing)
						.addComponent(btnEstimateTopics_1))
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addGap(7)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_8.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_LDARelatedTopics, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(btnShowResults))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_9, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_11, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		panel_8.setLayout(gl_panel_8);
		
		JLabel label_3 = new JLabel("Find Topics By Title Phrases");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		
		button_4 = new JButton("Find Topics");
		
		
		listPhrases =  new DefaultListModel();
		list_3 = new JList(listPhrases);
		
		
		JScrollPane scrollPane_5 = new JScrollPane(list_3);
		
		JLabel lblRelatedTo = new JLabel("Related to:");
		
		textField_RelatedPhrases = new JTextField();
		textField_RelatedPhrases.setEditable(false);
		textField_RelatedPhrases.setColumns(10);
		
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_4, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRelatedTo, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_RelatedPhrases, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(button_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRelatedTo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_RelatedPhrases, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
		
		panel_7.setLayout(gl_panel_7);
		
		JLabel label_2 = new JLabel("Topic Search");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBox_TopicSearch = new JComboBox();
		modelContent = new String[] {"weather condition", "Evaluate weather condition", "Nonradar terminal control", "Perform handoff", "Maintain separation", "VFR"};
		
		comboBox_TopicSearch.setModel(new DefaultComboBoxModel(modelContent));
		comboBox_TopicSearch.setEditable(true);
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_TopicSearch, 0, 1050, Short.MAX_VALUE))
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_TopicSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_10.setLayout(gl_panel_10);
		panel_5.setLayout(gl_panel_5);
		layeredPaneSearchTopic.setLayout(gl_layeredPaneSearchTopic);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Computational linguistics and natural language processing", null, layeredPane_2, null);

		JLabel lblGenerateTopicsUsing = new JLabel("Pre-process input text data using words stemming");
		lblGenerateTopicsUsing.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGenerateTopicsUsing.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblInputDataFolder = new JLabel("Input data folder:");
		lblInputDataFolder.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInputDataFolder.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField = new JTextField();
		textField.setText("data_text");
		textField.setColumns(10);
		
		JProgressBar progressBar_1 = new JProgressBar();
		
		JButton button_3 = new JButton("Cancel");
		try {
			//button_3.setIcon(new ImageIcon(FAA3_GUI.class.getResource("/Resources/cancel6_16x16.png")));
			button_3.setIcon(new ImageIcon("icons/cancel6_16x16.png","cancel"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		button_3.setEnabled(false);
		
		JButton btnExtractWordsSterming = new JButton("Extract words sterming");
		
		JLabel lblStemWordsIn = new JLabel("Stem words in topics");
		lblStemWordsIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblStemWordsIn.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel label_1 = new JLabel("Number of Top Words in a topic:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		spinnerNumWordTipicWordnet = new JSpinner();
		spinnerNumWordTipicWordnet.setModel(new SpinnerNumberModel(5, 1, 20, 1));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		
		JListOfTopicsWordnet = new DefaultListModel();
		
		listWordnet = new JList(JListOfTopicsWordnet);
		scrollPane_6.setViewportView(listWordnet);
		listWordnet.setSelectedIndex(0);
		
		btnGetStemWords = new JButton("Get stem words");
		
		JSeparator separator_13 = new JSeparator();
		
		JSeparator separator_19 = new JSeparator();
		separator_19.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_20 = new JSeparator();
		separator_20.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_21 = new JSeparator();
		
		JSeparator separator_22 = new JSeparator();
		
		JLabel lblOutputDataFolder = new JLabel("Output data folder:");
		lblOutputDataFolder.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOutputDataFolder.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtDatatextstem = new JTextField();
		txtDatatextstem.setText("data_text_stem");
		txtDatatextstem.setColumns(10);
		
		JSeparator separator_23 = new JSeparator();
		
		JSeparator separator_24 = new JSeparator();
		separator_24.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_25 = new JSeparator();
		
		JSeparator separator_26 = new JSeparator();
		
		JSeparator separator_27 = new JSeparator();
		separator_27.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lblFindTheCommon = new JLabel("Find the common parent in topics");
		lblFindTheCommon.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFindTheCommon.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblFindStemWords = new JLabel("Find stem words in topics");
		lblFindStemWords.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFindStemWords.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		
		JListOfTopicsWordnetParent = new DefaultListModel();
		listTopicsWordnetParent = new JList(JListOfTopicsWordnetParent);
		scrollPane_7.setViewportView(listTopicsWordnetParent);
		listTopicsWordnetParent.setSelectedIndex(0);
		
		JLabel lblParentLevel = new JLabel("Parent Up-level:");
		lblParentLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblParentLevel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		spinnerParentUpLevel = new JSpinner();
		spinnerParentUpLevel.setModel(new SpinnerNumberModel(1, 0, 20, 1));
		GroupLayout gl_layeredPane_2 = new GroupLayout(layeredPane_2);
		gl_layeredPane_2.setHorizontalGroup(
			gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane_2.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addComponent(separator_22, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(lblGenerateTopicsUsing, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
							.addComponent(separator_21, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInputDataFolder, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_layeredPane_2.createSequentialGroup()
									.addGap(98)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblOutputDataFolder, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_layeredPane_2.createSequentialGroup()
									.addGap(98)
									.addComponent(txtDatatextstem, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
							.addGap(9)
							.addComponent(btnExtractWordsSterming, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_19, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
					.addComponent(separator_20, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_layeredPane_2.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addComponent(separator_24, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
							.addGap(108)
							.addComponent(lblStemWordsIn, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_23, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(430)
							.addComponent(separator_27, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(184)
							.addComponent(spinnerNumWordTipicWordnet, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(10)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(239)
							.addComponent(btnGetStemWords, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(281)
							.addComponent(separator_26, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_13, GroupLayout.PREFERRED_SIZE, 667, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_layeredPane_2.createSequentialGroup()
					.addGap(20)
					.addComponent(separator_25, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_layeredPane_2.createSequentialGroup()
					.addGap(20)
					.addComponent(lblFindStemWords, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_layeredPane_2.createSequentialGroup()
							.addGap(449)
							.addComponent(lblParentLevel, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addComponent(lblFindTheCommon, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
							.addGap(71)))
					.addComponent(spinnerParentUpLevel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
				.addGroup(gl_layeredPane_2.createSequentialGroup()
					.addGap(20)
					.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(scrollPane_7, GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
					.addGap(28))
				.addGroup(gl_layeredPane_2.createSequentialGroup()
					.addComponent(progressBar_1, GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
					.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
		);
		gl_layeredPane_2.setVerticalGroup(
			gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane_2.createSequentialGroup()
					.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layeredPane_2.createSequentialGroup()
									.addGap(11)
									.addComponent(separator_22, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblGenerateTopicsUsing, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_layeredPane_2.createSequentialGroup()
									.addGap(11)
									.addComponent(separator_21, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
							.addGap(3)
							.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layeredPane_2.createSequentialGroup()
									.addGap(1)
									.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_layeredPane_2.createSequentialGroup()
											.addGap(3)
											.addComponent(lblInputDataFolder))
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_layeredPane_2.createSequentialGroup()
									.addGap(1)
									.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_layeredPane_2.createSequentialGroup()
											.addGap(3)
											.addComponent(lblOutputDataFolder))
										.addComponent(txtDatatextstem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(btnExtractWordsSterming)))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(11)
							.addComponent(separator_19, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(11)
							.addComponent(separator_20, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(26)
							.addComponent(separator_24, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(11)
							.addComponent(lblStemWordsIn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(26)
							.addComponent(separator_23, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(26)
							.addComponent(separator_27, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(38)
							.addComponent(spinnerNumWordTipicWordnet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(43)
							.addComponent(label_1))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(39)
							.addComponent(btnGetStemWords))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(26)
							.addComponent(separator_26, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_13, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addComponent(separator_25, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(2)
							.addComponent(lblFindStemWords, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layeredPane_2.createSequentialGroup()
									.addGap(3)
									.addComponent(lblParentLevel))
								.addComponent(lblFindTheCommon, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
						.addComponent(spinnerParentUpLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_layeredPane_2.createSequentialGroup()
							.addGap(2)
							.addComponent(scrollPane_7, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addGroup(gl_layeredPane_2.createParallelGroup(Alignment.LEADING)
						.addComponent(progressBar_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
		);
		layeredPane_2.setLayout(gl_layeredPane_2);
		contentPane.setLayout(gl_contentPane);
		
		
		// Application start initiating  value ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		NoConsoleOutput = false;
		//console.setVisible(false);
		
		autoTopic = new autoDetectNumTopics(NumberFileInTextFolder() );

		updateAutoDetectTopicsForm() ;
		tabbedPane.setSelectedIndex(2);
	}


	private static int NumberFileInTextFolder() {
		
		int NumberFile=0;
		String InputFolder = txtTextdatafolder.getText();
		File[] files = new File(InputFolder).listFiles(); 		// If this pathname does not denote a directory, then listFiles() returns null.
		if (files != null) {
			NumberFile = files.length;
		} 
		
		return NumberFile;
	}
	
	private static int CheckNumberFileInTextFolder() {
		int n= NumberFileInTextFolder();
		if ( n <= 0 ) {
			JOptionPane.showMessageDialog(new JPanel(), "Input data folder: '" + txtTextdatafolder.getText() + 
					"' is empty. Please copy text data or generate it on 'Extract documents' tab", "Error", JOptionPane.ERROR_MESSAGE);
		};
		return n;
	}
	public static ISynset getSynset(String word, POS pos) {
		IIndexWord indexWord = dict.getIndexWord(word, pos);
		if (indexWord != null) {
			IWordID wordID = indexWord.getWordIDs().get(0); // use first WordID
			return dict.getWord(wordID).getSynset();
		}
		return null;
	}

	public static List<ISynsetID> getAncestors(ISynset synset) {
		List<ISynsetID> list = new ArrayList<ISynsetID>();
		list.addAll(synset.getRelatedSynsets(Pointer.HYPERNYM));
		list.addAll(synset.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE));
		return list;
	}
	public static List<List<ISynset>> getPathsToRoot(ISynset synset) {
		List<List<ISynset>> pathsToRoot = null;
		List<ISynsetID> ancestors = getAncestors(synset);

		if (ancestors.isEmpty()) {
			pathsToRoot = new ArrayList<List<ISynset>>();
			List<ISynset> pathToRoot = new ArrayList<ISynset>();
			pathToRoot.add(synset);
			pathsToRoot.add(pathToRoot);

		} else if (ancestors.size() == 1) {
			pathsToRoot = getPathsToRoot(dict.getSynset(ancestors.get(0)));

			for (List<ISynset> pathToRoot : pathsToRoot) {
				pathToRoot.add(0, synset);
			}

		} else {
			pathsToRoot = new ArrayList<List<ISynset>>();
			for (ISynsetID ancestor : ancestors) {
				ISynset ancestorSynset = dict.getSynset(ancestor);
				List<List<ISynset>> pathsToRootLocal = getPathsToRoot(ancestorSynset);

				for (List<ISynset> pathToRoot : pathsToRootLocal) {
					pathToRoot.add(0, synset);
				}

				pathsToRoot.addAll(pathsToRootLocal);
			}
		}

		return pathsToRoot;
	}
	private static ISynset findClosestCommonParent(List<ISynset> pathToRoot1, List<ISynset> pathToRoot2) {
		int i = 0;
		int j = 0;

		if (pathToRoot1.size() > pathToRoot2.size()) {
			i = pathToRoot1.size() - pathToRoot2.size();
			j = 0;

		} else if (pathToRoot1.size() < pathToRoot2.size()) {
			i = 0;
			j = pathToRoot2.size() - pathToRoot1.size();
		}

		do {
			ISynset synset1 = pathToRoot1.get(i++);
			ISynset synset2 = pathToRoot2.get(j++);

			if (synset1.equals(synset2)) {
				return synset1;
			}

		} while (i < pathToRoot1.size());

		return null;
	}
	public static ISynset findClosestCommonParent(ISynset synset1, ISynset synset2) {
		if ((synset1 == null) || (synset2 == null)) {
			return null;
		}
		if (synset1.equals(synset2)) {
			return synset1;
		}

		List<List<ISynset>> pathsToRoot1 = getPathsToRoot(synset1);
		List<List<ISynset>> pathsToRoot2 = getPathsToRoot(synset2);
		ISynset resultSynset = null;
		int i = 0;

		for (List<ISynset> pathToRoot1 : pathsToRoot1) {
			for (List<ISynset> pathToRoot2 : pathsToRoot2) {

				ISynset synset = findClosestCommonParent(pathToRoot1, pathToRoot2);

				int j = pathToRoot1.size() - (pathToRoot1.indexOf(synset) + 1);
				if (j >= i) {
					i = j;
					resultSynset = synset;
				}
			}
		}

		return resultSynset;
	}

	/**
	 * maxDepth
	 * 
	 * @param synset
	 * @return The length of the longest hypernym path from this synset to the root.
	 */
	public static int maxDepth(ISynset synset) {
		if (synset == null) {
			return 0;
		}

		List<ISynsetID> ancestors = getAncestors(synset);
		if (ancestors.isEmpty()) {
			return 0;
		}

		int i = 0;
		for (ISynsetID ancestor : ancestors) {
			ISynset ancestorSynset = dict.getSynset(ancestor);
			int j = maxDepth(ancestorSynset);
			i = (i > j) ? i : j;
		}

		return i + 1;
	}
	
	public static Integer shortestPathDistance(ISynset synset1, ISynset synset2) {
		Integer distance = null;
		if (synset1.equals(synset2)) {
			return 0;
		}

		ISynset ccp = findClosestCommonParent(synset1, synset2);
		if (ccp != null) {
			distance = maxDepth(synset1) + maxDepth(synset2) - 2 * maxDepth(ccp);

			// Debug
			//String w1 = synset1.getWords().get(0).getLemma();
			//String w2 = synset2.getWords().get(0).getLemma();
			//String w3 = ccp.getWords().get(0).getLemma();
			//System.out.println("maxDepth(" + w1 + "): " + maxDepth(synset1));
			//System.out.println("maxDepth(" + w2 + "): " + maxDepth(synset2));
			//System.out.println("maxDepth(" + w3 + "): " + maxDepth(ccp));
			//System.out.println("distance(" + w1 + "," + w2 + "): " + distance);
		}
		return distance;
	}
	
	private void createEvents() {

		btnGetRelatedTopics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Solr_KnowledgeArchitectureHTMLFile sKA =  new Solr_KnowledgeArchitectureHTMLFile(txtHTMLFile.getText());
				sKA.displayListSolr(sKA.getRelatedTopics(), listSolr);
				
				
				// getHTML("http://localhost:8983/solr/knowledgeArchitecture.html?query=perform+handoff");
				HTML html;
				try {
					html = new HTML(comboBox.getSelectedItem().toString() + "?query=" + comboBox_1.getSelectedItem().toString());
					//displayListSolr(html.getRelatedTopics(),listSolr);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		list_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				findTopicsByTitlePhrases2.actionPerformed_mouseClicked();
			}
			
		});
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				findTopicsByTitlePhrases2.actionPerformed_FindTopicsButtonClicked();
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindTopicsByTitlePhrases topicsByTitlePhrases = new FindTopicsByTitlePhrases(txSearchTopic.getText(),FAA3_GUI.txtCfaamainphrases.getText());
				
			}
		});
		
		chckbxAlsoParseThe.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chckbxAlsoParseThe.isSelected()) {
					txtCfaamainphrases.setEnabled(true);
					comboBox_FromPage.setEnabled(true);
					comboBox.setEnabled(true);
					comboBox_ToPage.setEnabled(true);
					comboBox_1.setEnabled(true);
					comboBox_MaxLength.setEnabled(true);
					chckbxRemoveNormalSize.setEnabled(true);
				} else {
					txtCfaamainphrases.setEnabled(false);
					comboBox_FromPage.setEnabled(false);
					comboBox.setEnabled(false);
					comboBox_ToPage.setEnabled(false);
					comboBox_1.setEnabled(false);
					comboBox_MaxLength.setEnabled(false);
					chckbxRemoveNormalSize.setEnabled(false);
				}
			}
		});
		
		checkBox_LetterOnly.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (checkBox_LetterOnly.isSelected()) {
					spinner_fromCharacter.setEnabled(true);
					spinner_ToCharacter.setEnabled(true);
				} else {
					spinner_fromCharacter.setEnabled(false);
					spinner_ToCharacter.setEnabled(false);
				}
			}
		});
		
		btnGetScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LDATopicsSearch.removeTopicSearchFile(txtTextdatafolder.getText(),"P_N_Topic_Search.txt");
				LDATopicsSearch.getTopicDocsScore("P_N_Topic_Search.txt", generateTopics.instances,comboBox_TopicSearch.getSelectedItem().toString());
			}
		});
		
		btnEstimateTopicsSimilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//createTextFileFromTopic(topicSearch, folder, maxNumberOfWords, fileName);
				try {
					createTextFileFromTopic(txSearchTopic.getText(), txtTextdatafolder.getText(), 4000, "P_N_Topic_Search.txt");
				} catch (Exception e) {
					e.printStackTrace();
					Outln("Error: " + e.getMessage());
					return;
				}
				
				
				actionPerformed_EstimateTopics();
				btnGetScore.setEnabled(true);
				/*{
					//Wait 5 second
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				} while (!process_GenerateTopics.isDone());
				removeTopicSearchFile(txtTextdatafolder.getText(),"P_N_Topic_Search.txt");
				getTopicDocsScore("P_N_Topic_Search.txt", generateTopics.instances);*/
				
			}
		});
		
		btnSearchTopics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				searchTopics(txSearchTopic.getText(), generateTopics.model);
			}
		});
		
		mntmRestartThisApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					restartProgram.restart(null);
				} catch (IOException e1) {
					Outln(e1.toString());
					e1.printStackTrace();
				}
			}
		});

		mntmSaveAllText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String content = console.getText().trim();
				if (content.length()<= 0) return;
				
				String outputFolder = "./logs/";
				File dir = new File(outputFolder);
				dir.mkdir();
				
				String outputFileName ="console";
				String timeStamp = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
				outputFileName += timeStamp +  ".txt";
				
				try {
					FileOutputStream outputStream = new FileOutputStream(outputFolder + outputFileName);
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
					BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
		
					bufferedWriter.write(content);
					bufferedWriter.newLine();

					bufferedWriter.close();
					
					console.setText(""); // Clear the console content after saved to a file
					
				} catch (IOException e2) {
					e2.printStackTrace();
					Outln(e2.toString());
				}
				
			}
		});
		
		/////////////////////////////////////////////// Formulate topics using lexical database Wordnet /////////////////////////////////////////////////////////////////////////

		listTopicsWordnetParent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = listTopicsWordnetParent.getSelectedIndex();
				if (select<0) return;
				
				listWordnet.setSelectedIndex(select);
				listWordnet.ensureIndexIsVisible(select);
			}
		});

		listWordnet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = listWordnet.getSelectedIndex();
				if (select<0) return;
				
				listTopicsWordnetParent.setSelectedIndex(select);
				listTopicsWordnetParent.ensureIndexIsVisible(select);
			}
		});
		
		btnGetStemWords.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String forConsoleOutput="\nTopics list after steming:\n\n";
				Formatter outTopicsTextBox;
				
				// Delete the old value of this Topics list
				JListOfTopicsWordnet.clear();
				
				int NumWordTopicWordnet = (int) spinnerNumWordTipicWordnet.getValue();
				String [][] wordInTopicMatrix = new String [NumberOfTopic][NumWordTopicWordnet];
				POS[] stemmingPOS = { POS.NOUN, POS.VERB, POS.ADJECTIVE, POS.ADVERB };
				
				// Show top words in topics with proportions for the first document
				for (int topicID = 0; topicID < NumberOfTopic; topicID++) {
					Iterator<IDSorter> iterator = generateTopics.topicSortedWords.get(topicID).iterator();

					outTopicsTextBox = new Formatter(new StringBuilder(), Locale.US);
					outTopicsTextBox.format("%d: ", topicID);
					int rank = 0;
					
					
					while (iterator.hasNext() && rank < NumWordTopicWordnet) {
						IDSorter idCountPair = iterator.next();
						String word = (String) generateTopics.dataAlphabet.lookupObject(idCountPair.getID());
						wordInTopicMatrix[topicID][rank] = word;
						
						List<String> stemWord1 =  wordnetStemmer.findStems(word, stemmingPOS[0]) ;
						List<String> stemWord = new ArrayList<String>(stemWord1);
						try {
							for (int i = 1; i < stemmingPOS.length; i++) {
								List<String> stemWord2 = wordnetStemmer.findStems(word, stemmingPOS[i]);
								if (!stemWord2.isEmpty())
									for (String wordIterator : stemWord2) {
										if (!stemWord.contains(wordIterator))
											stemWord.add(wordIterator);
									}
							}
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						if (! stemWord.isEmpty()) { 
							int size = stemWord.size();
							if (size > 1)
								outTopicsTextBox.format("%s", "[");
							int i;
							for (i = 0; i < size-1; i++)
								outTopicsTextBox.format("%s. ", stemWord.get(i));
							outTopicsTextBox.format("%s", stemWord.get(i));
							if (size > 1)
								outTopicsTextBox.format("%s", "]");
							
						}
						else outTopicsTextBox.format("%s", "{" + word + "}" );
						if (rank < NumWordTopicWordnet-1)
							outTopicsTextBox.format("%s", ", ");
						
						rank++;
					}
					JListOfTopicsWordnet.add(topicID, outTopicsTextBox);
					forConsoleOutput += outTopicsTextBox + "\n";
				}
				listWordnet.repaint();
				forConsoleOutput += "\nTopic Distance Table Matrix:\n\n";
				
				// System.out.print("0->1:  \n " + topicDistance(wordInTopicMatrix, NumWordTopicWordnet, 0,1));	// for testing
				
				float [][] topicDistanceTable = new float [NumberOfTopic][NumberOfTopic];
				float minTopicDistance=100;
				int topic1Min=0, topic2Min=1;
				for (int topicID1 = 0; topicID1 < NumberOfTopic; topicID1++) {
					System.out.print("Distance from " + topicID1 + " : ");
					forConsoleOutput += "Distance from " + topicID1 + " : ";
					for (int topicID2 = topicID1+1; topicID2 < NumberOfTopic; topicID2++) {
						
						// Calculate the distance from topicID1 to topicID2
						topicDistanceTable[topicID1][topicID2] = topicDistance(wordInTopicMatrix, NumWordTopicWordnet, topicID1,topicID2);
						topicDistanceTable[topicID2][topicID1] = topicDistanceTable[topicID1][topicID2];
						
						if (minTopicDistance > topicDistanceTable[topicID1][topicID2]) {
							minTopicDistance = topicDistanceTable[topicID1][topicID2];
							topic1Min = topicID1;
							topic2Min = topicID2;
						}
						
						System.out.print(topicID2 + " ( " + topicDistanceTable[topicID1][topicID2] + " ) ");
						forConsoleOutput += topicID2 + " ( " + topicDistanceTable[topicID1][topicID2] + " ) ";
						
					}
					System.out.println();
					forConsoleOutput += "\n";
				}
				forConsoleOutput += "\nThe 2 most similar topics are: " + topic1Min + " and " + topic2Min + " with distance is: " + minTopicDistance;
				System.out.println("The 2 most similar topics are: " + topic1Min + " and " + topic2Min + " with distance is: " + minTopicDistance);
					
				///////////////////////////////////////// Find the common parent in topics  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// JListOfTopicsWordnetParent = new DefaultListModel();
				//listTopicsWordnetParent = new JList(JListOfTopicsWordnetParent);
				
				JListOfTopicsWordnetParent.clear();
				
				List<ISynset> topicISynset =new ArrayList<ISynset>();
				ISynset synset = null;
				String word;
				List<String> stemWord = new ArrayList<String>();
				for (int topicID = 0; topicID < NumberOfTopic; topicID++) {
					System.out.print("\nTopic "+topicID+" ISynset: ");
					topicISynset.clear();
					
					synset = null;
					for (int i = 0; i < NumWordTopicWordnet; i++) {
						word = wordInTopicMatrix[topicID][i];
						stemWord.clear();
						synset = null;
						stemWord = wordnetStemmer.findStems(word, POS.NOUN);
						if (stemWord.isEmpty()) {
							stemWord = wordnetStemmer.findStems(word, POS.VERB);
							if (stemWord.isEmpty()) {
								stemWord = wordnetStemmer.findStems(word, POS.ADJECTIVE);
								if (stemWord.isEmpty()) {
									stemWord = wordnetStemmer.findStems(word, POS.ADVERB);
									if (stemWord.isEmpty()) {
										
										// Cannot find the stem word
										System.out.print("{"+word+"}, ");
										
									} else
										synset = getSynset(stemWord.get(0), POS.ADVERB);
								} else
									synset = getSynset(stemWord.get(0), POS.ADJECTIVE);

							} else
								synset = getSynset(stemWord.get(0), POS.VERB);

						} else
							synset = getSynset(stemWord.get(0), POS.NOUN);

						if (synset != null) {
							topicISynset.add(synset);
							System.out.print("" + word + ", ");
						}

					}
					
					int parentUpLevel = (int) spinnerParentUpLevel.getValue();
					
					if (!topicISynset.isEmpty()) {
						// start finding the common parent in this topicISynset
						boolean keepDoing;
						ISynset parent;
						int i0,i1;
						do {
							keepDoing = false;
							outter: for (i0 = 0; i0 < topicISynset.size(); i0++)
								if (!keepDoing)
								for (i1 = i0 + 1; i1 < topicISynset.size(); i1++) {
										if (topicISynset.get(i0).getPOS().equals(topicISynset.get(i1).getPOS())) {
											parent = findClosestCommonParent(topicISynset.get(i0), topicISynset.get(i1));
											ISynset tempISynset0 = topicISynset.get(i0);
											ISynset tempISynset1 = topicISynset.get(i1);
											try {
												if ((shortestPathDistance(tempISynset0, parent) <= parentUpLevel)
														|| (shortestPathDistance(tempISynset1, parent) <= parentUpLevel)) {
													topicISynset.remove(i1);
													topicISynset.remove(i0);
													topicISynset.add(topicISynset.size(), parent);
													keepDoing = true;
													i0--;
													i1--;
													break outter;
												}
											} catch (Exception e1) {
												e1.printStackTrace();
											}
										}
								}
							
						} while (keepDoing);

						System.out.print("\n" + topicID + ": ");
						outTopicsTextBox = new Formatter(new StringBuilder(), Locale.US);
						outTopicsTextBox.format("%d: ", topicID);
						for (int i = 0; i < topicISynset.size(); i++) {
							int numWord = topicISynset.get(i).getWords().size();
							if (numWord>1) { 
								System.out.print("[");
								outTopicsTextBox.format("%s", "[");
							}
							int j;
							for (j=0; j<numWord-1; j++) {
								System.out.print(topicISynset.get(i).getWords().get(j).getLemma() + ". ");
								outTopicsTextBox.format("%s", topicISynset.get(i).getWords().get(j).getLemma() + ". ");
							}
							System.out.print(topicISynset.get(i).getWords().get(j).getLemma());
							outTopicsTextBox.format("%s", topicISynset.get(i).getWords().get(j).getLemma());
							if (numWord>1) { 
								System.out.print("]");
								outTopicsTextBox.format("%s", "]");
							}
							System.out.print(", ");
							if (i < topicISynset.size() -1)
								outTopicsTextBox.format("%s", ", ");
						}
						System.out.println();
						JListOfTopicsWordnetParent.add(topicID, outTopicsTextBox);

					}
				}
				listTopicsWordnetParent.repaint();
				Outln(forConsoleOutput);
			}

			private float topicDistance(String[][] wordInTopicMatrix, int numWordTopicWordnet, int topicID1, int topicID2) {
				float distance = 100.0f;
				int pair = 0;
				int totalDistance =0;
				int d;
				String word1,word2;
				ISynset synset1, synset2;
				for (int wordID1 = 0; wordID1 < numWordTopicWordnet; wordID1++) {
					for (int wordID2 = 0; wordID2 < numWordTopicWordnet; wordID2++) {
						word1 = wordInTopicMatrix[topicID1][wordID1];
						word2 =  wordInTopicMatrix[topicID2][wordID2];
						//System.out.println("Word1: " + word1 + ". Word2: " + word2);
						
						d = nounWordDistance(word1,word2);
						
						if (d<100) {
							totalDistance += d;
							pair++;
							//System.out.println("totalDistance: " + totalDistance + ". pair: " + pair);
						}
						
					}
				}
				if (pair >0) distance = (float) totalDistance / pair;
				return distance;
			}
			private int nounWordDistance(String word1, String word2) {
				if (word1.equals(word2)) return 0;
				int distance = 100;
				int d;
				ISynset synset1, synset2;
				//System.out.println("Word1: " + word1 + ". Word2: " + word2);

				List<String> stemWord1 = wordnetStemmer.findStems(word1, POS.NOUN);
				List<String> stemWord2 = wordnetStemmer.findStems(word2, POS.NOUN);
				if (stemWord1.isEmpty() || stemWord2.isEmpty())
					return distance;
				
				for (int i = 0; i < stemWord1.size(); i++) {
					synset1 = getSynset(stemWord1.get(i), POS.NOUN);
					if ((synset1 != null))
						for (int j = 0; j < stemWord2.size(); j++) {
							synset2 = getSynset(stemWord2.get(j), POS.NOUN);
							if ((synset2 != null)) {
								d = shortestPathDistance(synset1, synset2);
								if (d<distance) distance= d;
							}
						}
				}
				return distance;
			}
		});
		
		spinnerNumOfIterations.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateAutoDetectTopicsForm();
			}
		});
		
		txtTextdatafolder.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textFieldCompositionTable.setText(txtTextdatafolder.getText());
				
				updateAutoDetectTopicsForm();
			}
		});
		
		btnAutoDetectNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						// Check data in the "data_text" folder. If it is empty then display a error message.
						if ( CheckNumberFileInTextFolder() <=0) return;
						
						
						boolean consoleVisibleStatus = console.isVisible();
						console.setVisible(false);
						FAA3_GUI.NoConsoleOutput = true;
						
						autoTopic.setVisible(true);

						FAA3_GUI.NoConsoleOutput = false;
						console.setVisible(consoleVisibleStatus);
					}
				});
			}
		});

		mntmAutomaticallyDetectAppropriate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Check data in the "data_text" folder. If it is empty then display a error message.
				if ( CheckNumberFileInTextFolder() <=0) return;
				
				tabbedPane.setSelectedIndex(1);

				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						boolean consoleVisible = console.isVisible();
						console.setVisible(false);
						FAA3_GUI.NoConsoleOutput = true;

						autoTopic.setVisible(true);

						FAA3_GUI.NoConsoleOutput = false;
						console.setVisible(consoleVisible);
					}
				});

			}
		});
		
		mntmPrintTheList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tabbedPane.setSelectedIndex(1);

				if (JListOfTopics.size() <=1)  { 		 // JListOfTopics has never been created before. Return!
					Outln("ListOfTopics is emply ! Please run 'Estimate topics' to ganerate its list of data first ");
					return;
				}
				
				
				
				
			}
		});
		
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintCompositionTable();
			}
		});
		
		mntmPrintTheComposition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PrintCompositionTable();

			}
		});

		listTopics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				if ( ( NumberOfTopic <= 0) )
					return; // Not run generateTopics yet

				// Sort the table when click on column title
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
				table.setRowSorter(sorter);
				List<RowSorter.SortKey> sortKeys = new ArrayList<>(SizeOfInstance + 3);

				table.setCellSelectionEnabled(true);
				
				table.changeSelection(0,0 , false, false); table.requestFocus(); // focus on 0,0 first before focusing on other cell. Which help to get the better view and feeling.
				
				if (listTopics.getSelectedIndex() == 0) {
					sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
					table.changeSelection(0,0 , false, false);
				} else {
					sortKeys.add(new RowSorter.SortKey(listTopics.getSelectedIndex() + 1, SortOrder.DESCENDING));
					table.changeSelection(0,listTopics.getSelectedIndex() + 1 , false, false);
				}
				sorter.setSortKeys(sortKeys);

				// refresh the table to make sure the cell color take effect
				table.repaint();
				
				table.requestFocus();

			}
		});

		mntmGetCompositionTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
				actionPerformed_GetCompositionTable();
			}
		});

		mntmEstimateTopicsUsing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Check data in the "data_text" folder. If it is empty then display a error message.
				if ( CheckNumberFileInTextFolder() <=0) return;
				
				tabbedPane.setSelectedIndex(1);
				actionPerformed_EstimateTopics();
			}
		});

		btnGetCompositionTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_GetCompositionTable();
			}
		});

		spinnerNumWordsInTopic.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				if ((generateTopics.NumberOfWordsInATopic != (int) spinnerNumWordsInTopic.getValue())
						&& (generateTopics.topicSortedWords != null)) {
					generateTopics.NumberOfWordsInATopic = (int) spinnerNumWordsInTopic.getValue();

					generateTopics.printListOfTopics();
				}

			}
		});

		btnCancelMallet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelMallet.setEnabled(false);

				// if (processPrintCompositionTable_running)
				// processPrintCompositionTable.cancel(true);
				if (processPrintCompositionTable_running)
					processPrintCompositionTable_running = false;
				if (process_GenerateTopics_running)
					process_GenerateTopics.cancel(true);

			}
		});

		btnEstimateTopics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_EstimateTopics();
			}
		});

		layeredPane_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTextdatafolder.setText(txtDatatextfolder.getText());
			}
		});

		///////////////////////////////////////////////// Extract text data from
		///////////////////////////////////////////////// documents
		///////////////////////////////////////////////// tab/////////////////////////////////////////////////////////////////////////////
		mntmExtractTextContents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				actionPerformed_createTextDataUsingTika();
			}
		});

		txtDatatextfolder.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String s = txtDatatextfolder.getText();
				s = getFoulderNameOf(s);
				if (s == "")
					s = "data_text";
				txtDatatextfolder.setText(s);

			}
		});

		txtrCdata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if (!tglbtnUsedefaultdatatext.isSelected()) {
					// update text output folder name
					txtDatatextfolder.setText(getFoulderNameOf(txtrCdata.getText()) + "_text");
					txtDatatextfolder.setEditable(true);
				}
			}
		});

		txtrCdata.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!tglbtnUsedefaultdatatext.isSelected()) {
					// update text output folder name
					txtDatatextfolder.setText(getFoulderNameOf(txtrCdata.getText()) + "_text");
					txtDatatextfolder.setEditable(true);
				}
			}

		});

		tglbtnUsedefaultdatatext.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tglbtnUsedefaultdatatext.isSelected()) {
					// use defaule data_text folder
					txtDatatextfolder.setText("data_text");
					txtDatatextfolder.setEditable(false);
				} else {
					txtDatatextfolder.setText(getFoulderNameOf(txtrCdata.getText()) + "_text");
					txtDatatextfolder.setEditable(true);
				}
			}
		});

		btnGetFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_createTextDataUsingTika();
			}
		});

		btnXCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				process_createTextDataUsingTika.cancel(true);
				btnXCancel.setEnabled(false);
			}
		});

		btnExtractTextContents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_createTextDataUsingTika();
			}
		});

		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(1);
			}
		});

		mntmClearConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.setText("");
			}
		});
		mntmTurnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (console.isVisible()) {
					console.setVisible(false);
					mntmTurnOff.setText("Turn on");
				} else {
					console.setVisible(true);
					mntmTurnOff.setText("Turn off");
				}
			}
		});

	}
	

	protected static void createTextFileFromTopic(String topicSearch, String folder, int maxNumberOfWords, String fileName) throws Exception {
		//createTextFileFromTopic(topicSearch, folder, maxNumberOfWords, fileName);
		String s=""; 
		Character ch;
		for (int i = 0; i< topicSearch.length(); i++) {
			ch = topicSearch.charAt(i);
			if (Character.isLetterOrDigit(ch)) 
				s += ch;
			else s += " ";
		}
		String[] words = s.toLowerCase().split(" ");
		
		s = "";
		Set<String> wSet = new HashSet<String> ();
		for (String word : words) {
			if (!wSet.contains(word)) {
				wSet.add(word);
				s += word + " ";
			}
		}
		
		int k = wSet.size();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(folder+"\\"+fileName, true));

		// bufferedWriter.write("Hello World");
		// bufferedWriter.newLine();
		for (int i = 0; i < maxNumberOfWords / k; i++) {
			bufferedWriter.write(s);
			bufferedWriter.newLine();
		}

		bufferedWriter.close();
		
	}

	protected void searchTopics(String s, ParallelTopicModel model) {
		s = s.toLowerCase();
		//System.out.println("s = " + s);
		String[] words;
		Set<String> searchWords = new HashSet<String>();
		
		try {
			words = s.split(" ");
			if (words.length <=0) return;
			for (String w :words ) {
				searchWords.add(w);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		if (model == null) {
			actionPerformed_EstimateTopics();
			return;
		}

		Outln(" ------------------ \nSearch topic [ " + s.trim() + " ] in the TOP WORDS LIST:   " );
		int n= generateTopics.numTopics;
		
		IDSorter idCountPair;
		String topicWord;
		Alphabet alphabet = generateTopics.dataAlphabet;
		// Show top words in topics with proportions for the first document
		for (int topic = 0; topic < n; topic++) {
			Out("Topic " + topic + " : ");
			Map<String, Integer> topicMap = new HashMap<String, Integer>();
			Iterator<IDSorter> iterator = model.getSortedWords().get(topic).iterator();
			while (iterator.hasNext()) {
				idCountPair = iterator.next();
				//out.format("%s (%.0f) ", dataAlphabet.lookupObject(idCountPair.getID()), idCountPair.getWeight());
				topicWord = alphabet.lookupObject(idCountPair.getID()).toString();
				if (searchWords.contains(topicWord)) {
					topicMap.put(topicWord, (int) idCountPair.getWeight());
				}
			}
			for (Map.Entry<String, Integer> t : topicMap.entrySet()) {
				Out(t.getKey() + " (" + t.getValue() + "), ");
			}
			Outln("");
		}
		
		
	}

	protected void PrintCompositionTable() {
		tabbedPane.setSelectedIndex(1);
		
		if (compositionTable.getRowCount() <=0 ) {  // compositionTable has never been created before. Return!
			Outln("CompositionTable is emply ! Please run 'Get composition table' to create its data first ");
			return;
		}
		try {
			PrinterJob printer = PrinterJob.getPrinterJob();

			// Set 1/2 " margins and orientation
			PageFormat pf = printer.defaultPage();
			if (generateTopics.numTopics > 10)
				pf.setOrientation(PageFormat.LANDSCAPE);
			else
				pf.setOrientation(PageFormat.PORTRAIT);

			MessageFormat header = new MessageFormat(
					"Composition table of '" + txtTextdatafolder.getText() + "'. Page {0,number,integer}");
			Paper paper = new Paper();
			double margin = 36; // half inch
			paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2,
					paper.getHeight() - margin * 2);
			pf.setPaper(paper);

			Book book = new Book();

			// book.append(table.getPrintable(PrintMode.FIT_WIDTH, header, null), pf);

			// printJob.append(new ListPrinter(listTopics), pf);

			int totalPages = 0;
			// 1st table
			Printable p1 = table.getPrintable(PrintMode.FIT_WIDTH, header, null);
			PrintableWrapper pw1 = new PrintableWrapper(p1, totalPages);
			totalPages += pw1.getNumberOfPages(pf);
			book.append(pw1, pf, pw1.getNumberOfPages(pf));

			Printable p2 = table.getPrintable(PrintMode.FIT_WIDTH, null, null);
			PrintableWrapper pw2 = new PrintableWrapper(p2, totalPages);
			totalPages += pw2.getNumberOfPages(pf);
			book.append(pw2, pf, pw2.getNumberOfPages(pf));

			printer.setPageable(book);

			if (printer.printDialog()) {
				printer.print();
				Outln(book.getNumberOfPages() - 1 + " pages were sent to printer");
			} else
				Outln("User cancelled printing. No page was sent to printer");

		} catch (PrinterException e3) {
			Outln("Cannot print !" + e3.getMessage());
		}
		
	}
	class ListPrinter implements Printable {

		@SuppressWarnings("rawtypes")
		public ListPrinter(JList listTopics) {
			
		}

		public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

			// We have only one page, and 'page' is zero-based
			if (page > 0) {
				return NO_SUCH_PAGE;
			}

			// User (0,0) is typically outside the image-able area, so we must translate by
			// the X and Y values in the PageFormat to avoid clipping.
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pf.getImageableX(), pf.getImageableY());

			// Now we perform our rendering
			g.drawString("Hello world!", 100, 100);

			// tell the caller that this page is part of the printed document
			return PAGE_EXISTS;
		}
	}

	class PrintableWrapper implements Printable {
		private Printable delegate;
		private int offset;

		public PrintableWrapper(Printable delegate, int offset) {
			this.offset = offset;
			this.delegate = delegate;
		}

		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
			return delegate.print(graphics, pageFormat, pageIndex - offset);
		}

		public int getNumberOfPages(PageFormat pageFormat) throws PrinterException {
			Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).createGraphics();
			int numPages = 0;
			while (true) {
				int result = delegate.print(g, pageFormat, numPages);
				if (result == Printable.PAGE_EXISTS)
					++numPages;
				else
					break;
			}

			return numPages;
		}
	}

	
	@SuppressWarnings("static-access")
	protected static void updateAutoDetectTopicsForm() {
		// update number of iterations of Topword Loop
		autoDetectNumTopics.spinnerNumIterationTopword.setValue( 	Max (	(int) spinnerNumOfIterations.getValue() /2  , 100,  (int) autoDetectNumTopics.spinnerNumIteration.getValue() *2     ));
		
		// update maximum number of topics
		autoDetectNumTopics.spinnerMaxTopics
					.setValue(Max((int) autoDetectNumTopics.spinnerMinimum.getValue(), NumberFileInTextFolder()));
		
	}

	private static int Max(int value1, int value2) {
		if (value1>value2) return value1; else return value2;
	}
	private static int Max(int value1, int value2, int value3) {
		int tem = Max(value1, value2);
		if (tem>value3) return tem; else return value3;
	}

	public void actionPerformed_GetCompositionTable() {

		compositionTable.setRowCount(0);
		btnCancelMallet.setEnabled(true);
		processPrintCompositionTable = new printCompositionTable(textFieldCompositionTable.getText());
		try {
			btnGetCompositionTable.setEnabled(false);
			btnEstimateTopics.setEnabled(false);
			processPrintCompositionTable.execute();
		} catch (Exception e1) {
			Outln(e1.toString());
		}
	}

	public static void actionPerformed_EstimateTopics() {
	
		// Check data in the "data_text" folder. If it is empty then display a error message.
		if ( CheckNumberFileInTextFolder() <=0) return;
		
		btnCancelMallet.setEnabled(true);
		process_GenerateTopics = null;
		JListOfTopics.removeAllElements();
		try {
			spinnerNumOfIterations.commitEdit();
			spinnerNumTopics.commitEdit();
			spinnerNumThreards.commitEdit();
			spinnerNumWordsInTopic.commitEdit();
		} catch (ParseException e2) {
			Outln("Warning: " + e2.getMessage());
		}
		process_GenerateTopics = new generateTopics(
				txtTextdatafolder.getText(), (int) spinnerNumTopics.getValue(),
				Double.parseDouble(textAlphaSum.getText()), Double.parseDouble(textBeta.getText()),
				(int) spinnerNumThreards.getValue(), (int) spinnerNumOfIterations.getValue(),
				(int) spinnerNumWordsInTopic.getValue());
		try {
			btnEstimateTopics.setEnabled(false);
			btnGetCompositionTable.setEnabled(false);
			process_GenerateTopics.execute();
		} catch (Exception e1) {
			Outln("Error: " +e1.getMessage());
		}

	}

	public void actionPerformed_createTextDataUsingTika() {
		btnXCancel.setEnabled(true);
		process_createTextDataUsingTika = null;
		try {
			process_createTextDataUsingTika = new createTextDataUsingTika(getFoulderNameOf(txtrCdata.getText()),
					getFoulderNameOf(txtDatatextfolder.getText()));
		} catch (IOException e2) {
			e2.printStackTrace();
			Outln(e2.toString());
		} catch (SAXException e2) {
			e2.printStackTrace();
			Outln(e2.toString());
		} catch (TikaException e2) {
			e2.printStackTrace();
			Outln(e2.toString());
		}
		try {
			btnGetFiles.setEnabled(false);
			btnExtractTextContents.setEnabled(false);
			process_createTextDataUsingTika.execute();
		} catch (Exception e1) {
			e1.printStackTrace();
			Outln(e1.toString());
		}
	}

	protected String getFoulderNameOf(String text) {
		String s = text.trim().split("\n")[0];
		return s.trim();
	}

	public FAA3_GUI() throws IOException {
		initComponents();
		createEvents();
		findTopicsByTitlePhrases2 = new FindTopicsByTitlePhrases2();
		InitWordnet(".");
	}

	public static void Outln(String string) {
		//System.out.println(string);
		if (NoConsoleOutput) return;
		if (!console.isVisible()) return;
		console.setText(console.getText() + string + "\n" );
		console.setCaretPosition(console.getDocument().getLength());
	}

	public static void Out(String s) {
		//System.out.print(s);
		if (NoConsoleOutput) return;
		if (!console.isVisible()) return;
		console.setText(console.getText() + s);
		console.setCaretPosition(console.getDocument().getLength());
	}

	public int getNumberOfPages(Printable delegate, PageFormat pageFormat) throws PrinterException {
		Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).createGraphics();
		int numPages = 0;
		while (true) {
			int result = delegate.print(g, pageFormat, numPages);
			if (result == Printable.PAGE_EXISTS) {
				++numPages;
			} else {
				break;
			}
		}

		return numPages;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FAA3_GUI frame = new FAA3_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
