/**
 * 
 */
package phong.FAA_GUI;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class SuggestedDocumentsForATopic extends JFrame{
	private JPanel contentPane;
	
	public SuggestedDocumentsForATopic() {
		
		initComponents();
		createEvents();
	}

	private void createEvents() {
		// TODO Auto-generated method stub
		
	}

	private void initComponents() {
		setTitle("Suggested Documents For A Topic");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(autoDetectNumTopics.class.getResource("/Resources/icon2.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
		setBounds(100, 100, 533, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
