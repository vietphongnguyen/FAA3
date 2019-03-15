/**
 * 
 */
package phong.FAA_GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class FindTopicsByTitlePhrases extends JFrame{
	private JButton btnFindTopics;
	private JComboBox comboBox_topic;
	private JList list;
	private JScrollPane scrollPane;
	
	DefaultListModel listPhrases;
	
	String topics;
	Set<String> phrasesSet = new LinkedHashSet<String>();
	TreeMap<Integer, String> phrasesLevel = new TreeMap<>((Collections.reverseOrder()));
	String[] modelContent;
	String inputFolderS;
	String stopwordTopicSearch="\n";
	
	
	/**
	 * 
	 */
	public FindTopicsByTitlePhrases(String topics, String inputFolderS) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initComponents();
		createEvents();
		this.inputFolderS = inputFolderS;
		topics = topics.trim().toLowerCase();
		this.topics = topics;
		updateModelContent(topics);
		initPhrasesSet();
		initStopWordsList("topicsSearch_stopwords.txt");
		actionPerformed_FindTopicsButtonClicked();
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

	private void updateModelContent(String topics) {
		
		topics = topics.trim().toLowerCase();
		for (int i = 0 ;i < modelContent.length; i++) 
			modelContent[i] = modelContent[i].trim().toLowerCase();
		Set<String> topicsSearchSet = new LinkedHashSet<String>(); 
		topicsSearchSet.add(topics);
		topicsSearchSet.addAll(Arrays.asList(modelContent));
		modelContent = new String[topicsSearchSet.size()];
		int i=0;
		for (String s : topicsSearchSet) {
			modelContent[i]=s;
			i++;
		}
		comboBox_topic.setModel(new DefaultComboBoxModel(modelContent));
		comboBox_topic.repaint();
	}

	private void initPhrasesSet() {
		
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

	public void actionPerformed_FindTopicsButtonClicked() {
		topics = comboBox_topic.getSelectedItem().toString().trim().toLowerCase();
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
		
		phrasesLevel.clear();
		for (String phrase: phrasesSet) {
			int numberWordMatch = CountWordMatch(topicsSet,phrase);
			if (numberWordMatch > 0) {
				if (phrasesLevel.containsKey(numberWordMatch))
					phrasesLevel.put(numberWordMatch, phrasesLevel.get(numberWordMatch) + "\n" + phrase);
				else 
					phrasesLevel.put(numberWordMatch, phrase);
			}
		}
		
		listPhrases.clear();
		
		for (Entry<Integer,String> entry : phrasesLevel.entrySet()) {
			String value = entry.getValue();
			for (String s: value.split("\n"))
				listPhrases.addElement("(" +entry.getKey() + ") " + s );
		}
		
		list.ensureIndexIsVisible(0);
		
	}

	private void createEvents() {
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = list.getSelectedIndex();
				String topic = listPhrases.getElementAt(index).toString();
				topic = topic.substring(topic.lastIndexOf(')') + 1 ).trim();
				updateModelContent(topic);
				actionPerformed_FindTopicsButtonClicked();
			}
		});
		
		btnFindTopics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_FindTopicsButtonClicked();
				
			}
		});
		
	}

	protected int CountWordMatch(Set<String> topicsSet, String phrase) {
		int count = 0;
		phrase = " " + phrase + " ";
		for (String topic:topicsSet)
			if (phrase.indexOf(" " + topic.trim() + " ") >= 0 ) 
				count++;
		return count;
	}

	private void initComponents() {
		setTitle("Find Topics By Title Phrases");
		this.setVisible(true);
		try {
			//setIconImage(Toolkit.getDefaultToolkit().getImage(autoDetectNumTopics.class.getResource("/Resources/icon2.png")));
			setIconImage(Toolkit.getDefaultToolkit().getImage("icons/icon2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setBounds(600, 300, 500, 600);
		
		try {
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - getWidth())/2 );
			if (x<0)  x =0;
			int y = (int) ((dimension.getHeight() - getHeight()) ) - 50; // 50 is about the height of the window start bar
			if (y<0)  y =0;
			setLocation(x,y);
		} catch (HeadlessException e2) {}
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblFindTopicsBy = new JLabel("Find Topics By Title Phrases");
		lblFindTopicsBy.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel label_2 = new JLabel("Topic Search");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		
		comboBox_topic = new JComboBox();
		
		modelContent = new String[] {"weather condition", "Evaluate weather condition", "Nonradar terminal control", "Perform handoff", "Maintain separation", "VFR"};
		//DefaultComboBoxModel model = new DefaultComboBoxModel(modelContent);
		comboBox_topic.setModel(new DefaultComboBoxModel(modelContent));
		comboBox_topic.setEditable(true);
		
		btnFindTopics = new JButton("Find Topics");
		
		
		listPhrases = new DefaultListModel();
		list = new JList(listPhrases);
		
		scrollPane = new JScrollPane(list);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
						.addComponent(lblFindTopicsBy, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_topic, 0, 352, Short.MAX_VALUE))
						.addComponent(btnFindTopics, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFindTopicsBy)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(comboBox_topic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnFindTopics)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addGap(27))
		);
		
		
		panel.setLayout(gl_panel);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindTopicsByTitlePhrases frame = new FindTopicsByTitlePhrases("estimate weather condition","C:\\FAA3\\main_phrases");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
