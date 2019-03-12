/**
 * 
 */
package phong.FAA_GUI;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class GetRelatedTopicsOnSolr extends JFrame{
	private JButton button;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JList list;
	private JComboBox comboBox_2;
	private JTextArea textArea;
	private JList list_1;
	private JButton button_1;

	DefaultListModel listSolr;
	private JComboBox comboBox_HtmlFile;
	
	/**
	 * 
	 */
	public GetRelatedTopicsOnSolr() {
		
		initComponents();
		createEvents();
	}

	private void createEvents() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Solr_KnowledgeArchitectureHTMLFile sKA =  new Solr_KnowledgeArchitectureHTMLFile(comboBox_HtmlFile.getSelectedItem().toString());
				displayListSolr(sKA.getRelatedTopics());
				
				
				// getHTML("http://localhost:8983/solr/knowledgeArchitecture.html?query=perform+handoff");
				HTML html;
				try {
					html = new HTML(comboBox.getSelectedItem().toString() + "?query=" + comboBox_1.getSelectedItem().toString());
					displayListSolr(html.getRelatedTopics());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
	}

	protected void displayListSolr(List<StringIntegerPair> relatedTopics) {
		for (StringIntegerPair relatedTopic : relatedTopics) {
			listSolr.addElement(relatedTopic.Topic + " (" + relatedTopic.Frequency + ") ");
		}
	}

	private void initComponents() {
		setTitle("Get Related Topics on Solr");
		this.setVisible(true);
		try {
			//setIconImage(Toolkit.getDefaultToolkit().getImage(autoDetectNumTopics.class.getResource("/Resources/icon2.png")));
			setIconImage(Toolkit.getDefaultToolkit().getImage("icons/icon2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		setBounds(300, 100, 1000, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		list_1 = new JList();
		JScrollPane scrollPane_1 = new JScrollPane(list_1);
		
		JLabel label_3 = new JLabel("List of words:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel label_4 = new JLabel("Topic Search");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel label_5 = new JLabel("LDA");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		
		button_1 = new JButton("Filter");
		
		textArea = new JTextArea();
		
		JScrollPane scrollPane_2 = new JScrollPane(textArea);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Nonradar terminal control", "Perform handoff", "Evaluate weather condition", "Maintain separation", "VFR"}));
		comboBox_2.setEditable(true);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(comboBox_2, 0, 387, Short.MAX_VALUE))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addGap(4))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_5)
							.addGap(9)
							.addComponent(label_4))
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(9)
							.addComponent(label_3)
							.addGap(87)
							.addComponent(button_1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(209, Short.MAX_VALUE))
		);
		
		
		
		
		panel_1.setLayout(gl_panel_1);
		
		button = new JButton("Get Related Topics on Solr");
		

		

		listSolr = new DefaultListModel();
		list = new JList(listSolr);
		
		JScrollPane scrollPane = new JScrollPane(list);
		
		JLabel label = new JLabel("Knowledge Architecture results on Solr");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel label_1 = new JLabel("Topic Search");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblSolrServer = new JLabel("Solr server");
		lblSolrServer.setHorizontalAlignment(SwingConstants.LEFT);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"http://localhost:8983/solr/knowledgeArchitecture.html", "http://130.101.10.139:8983/solr/knowledgeArchitecture.html", "http://"}));
		comboBox.setEditable(true);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"weather condition", "Evaluate weather condition", "Nonradar terminal control", "Perform handoff", "Maintain separation", "VFR"}));
		comboBox_1.setEditable(true);
		
		JLabel lblSolrHtmlOutput = new JLabel("Solr HTML output");
		lblSolrHtmlOutput.setHorizontalAlignment(SwingConstants.LEFT);
		
		comboBox_HtmlFile = new JComboBox();
		comboBox_HtmlFile.setModel(new DefaultComboBoxModel(new String[] {"weather condition.html"}));
		comboBox_HtmlFile.setEditable(true);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSolrServer, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, 0, 352, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, 0, 352, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSolrHtmlOutput, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_HtmlFile, 0, 352, Short.MAX_VALUE))
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblSolrServer))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(14)
							.addComponent(lblSolrHtmlOutput))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox_HtmlFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(button)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(217, Short.MAX_VALUE))
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
					GetRelatedTopicsOnSolr frame = new GetRelatedTopicsOnSolr( );
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}