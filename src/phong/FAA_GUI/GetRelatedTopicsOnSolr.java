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

/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class GetRelatedTopicsOnSolr extends JFrame{
	private JTextField textField;
	private JTextField txtWeatherCondition;
	private JButton button;
	private JTextField textField_1;

	/**
	 * 
	 */
	public GetRelatedTopicsOnSolr() {
		
		initComponents();
		createEvents();
	}

	private void createEvents() {
		// TODO Auto-generated method stub
		
	}

	private void initComponents() {
		setTitle("Get Related Topics on Solr");
		setResizable(false);
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
		
		textField = new JTextField();
		textField.setText("http://localhost:8983/solr/knowledgeArchitecture.html");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(10);
		
		JLabel label = new JLabel("Knowledge Architecture results on Solr");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		
		button = new JButton("Get Related Topics on Solr");
		
		JLabel lblTopicSearch = new JLabel("Topic Search");
		lblTopicSearch.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtWeatherCondition = new JTextField();
		txtWeatherCondition.setText("weather condition");
		txtWeatherCondition.setHorizontalAlignment(SwingConstants.LEFT);
		txtWeatherCondition.setColumns(10);
		JList list = new JList();
		list.setVisibleRowCount(10);
		
		JScrollPane scrollPane = new JScrollPane(list);
		JList list_1 = new JList();
		list_1.setVisibleRowCount(10);
		
		JScrollPane scrollPane_1 = new JScrollPane(list_1);
		
		JLabel lblKaSolrServer = new JLabel("Solr address: ");
		lblKaSolrServer.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblLda = new JLabel("LDA");
		lblLda.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel label_1 = new JLabel("Topic Search");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		textField_1 = new JTextField();
		textField_1.setText("weather condition");
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setColumns(10);
		
		JLabel lblListOfWords = new JLabel("List of words:");
		lblListOfWords.setHorizontalAlignment(SwingConstants.LEFT);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JButton btnFilter = new JButton("Filter");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblTopicSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblKaSolrServer, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtWeatherCondition)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLda, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblListOfWords, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))))
							.addGap(16))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(771, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 472, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
							.addGap(16))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(lblLda))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblKaSolrServer))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTopicSearch)
								.addComponent(txtWeatherCondition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblListOfWords))
							.addGap(13)
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnFilter)
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
					.addGap(232))
		);
		
		
		
		
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
