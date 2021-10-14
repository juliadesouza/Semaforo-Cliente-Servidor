package gui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Insets;

public class MessageScreen extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JPanel panelLogo;
	private JPanel panelButton;

	public MessageScreen(JFrame owner,String title, String message) {
		super(owner,title);
		setSize(800,320);
		setResizable(false);
		setLocationRelativeTo(owner.getParent());
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setModalityType(MessageScreen.ModalityType.APPLICATION_MODAL);
		getContentPane().setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "B7 GROUP", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
			
		textArea = new JTextArea();
		formatTextArea();
		textArea.setText(message);
		scrollPane.setViewportView(textArea);
			
		panelLogo = new LogoPanel();
		panelLogo.setBackground(Color.WHITE);
		panelLogo.setPreferredSize(new Dimension(200,220) );
		panelLogo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "TRAFFIC LIGHT", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panelLogo, BorderLayout.WEST);
		
		panelButton = new JPanel();
		getContentPane().add(panelButton, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);		
	}
	
	private void formatTextArea() {
		textArea.setEditable(false);
		textArea.setForeground(Color.black);
		textArea.setBackground(Color.white);
		textArea.setFocusable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setMargin(new Insets(10, 20, 15, 20));
		textArea.setCaretPosition(0);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
	}

}
