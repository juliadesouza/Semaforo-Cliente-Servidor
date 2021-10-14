package server;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import common.Information;
import gui.MessageScreen;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class TrafficLightServerWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Server server;
	
	protected JTextArea textArea;

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmExit;
	private JMenu mnHelp;
	private JMenuItem mntmHelp;
	private JMenuItem mntmAbout;
	private JMenuItem mntmDisclaimer;
	private JScrollPane scrollPane;


	public TrafficLightServerWindow(Server server) {
		this.server = server;
		
		setupMainPanel();
		setupMenu();
		
		mntmHelp.addActionListener(this);
		mntmDisclaimer.addActionListener(this);
		mntmAbout.addActionListener(this);
		mntmExit.addActionListener(this);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				exit();
			}
		});
	}
	
	private void setupMainPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 410);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();		
		formatTextArea();
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(textArea);
	}
	
	private void setupMenu() {
		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Arial", Font.PLAIN, 16));
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		mnFile.setFont(new Font("Arial", Font.PLAIN, 16));
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Arial", Font.PLAIN, 14));
		mnFile.add(mntmExit);
		
		mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Arial", Font.PLAIN, 16));
		menuBar.add(mnHelp);
		
		mntmHelp = new JMenuItem("Help");
		mntmHelp.setFont(new Font("Arial", Font.PLAIN, 14));
		mnHelp.add(mntmHelp);
		
		mntmAbout = new JMenuItem("About");
		mntmAbout.setFont(new Font("Arial", Font.PLAIN, 14));
		mnHelp.add(mntmAbout);
		
		mntmDisclaimer = new JMenuItem("Disclaimer");
		mntmDisclaimer.setFont(new Font("Arial", Font.PLAIN, 14));
		mnHelp.add(mntmDisclaimer);
	}
	
	 private void formatTextArea() {
	     textArea.setForeground(Color.black);
	     textArea.setBackground(Color.white);
	     textArea.setEditable(false);
	     textArea.setFocusable(true);
	     textArea.setLineWrap(true);
	     textArea.setWrapStyleWord(true);
	     textArea.setMargin(new Insets(10, 20, 15, 20));
	     textArea.setFont(new Font("Arial", Font.BOLD, 15));
	     textArea.setCaretPosition(0);
     }
	 
	 private void exit() {
		 try {
			server.turnOffClients();
		 } 
		 catch (IOException e) {
			System.err.println("Error turning off the clients: " + e.getMessage());
		 }
	 }
	 
	 @Override
	 public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == this.mntmHelp) {
			 new MessageScreen(this,"Help",Information.getHelpServerText());
		 }
		 if(e.getSource() == this.mntmDisclaimer) {
			 new MessageScreen(this,"Disclaimer",Information.getDisclaimerText());
		 }
		 if(e.getSource() == this.mntmAbout) {
			 new MessageScreen(this,"About",Information.getAboutText());
		 }
		 if(e.getSource() == this.mntmExit) {
			 exit();
			 System.exit(0);
		 }
	 }

}
