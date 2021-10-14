package client;
import common.Information;
import gui.MessageScreen;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class TrafficLightClientWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private ClientNetworkHandler client;
	
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenuItem mntmExit;
	private JMenuItem mntmHelp;
	private JMenuItem mntmAbout;
	private JMenuItem mntmDisclaimer;
	
	protected TrafficLightPanel pnTrafficLight;
		
	public TrafficLightClientWindow(ClientNetworkHandler client){
		this.client = client;
		
		setupMainPanel();
		setupMenu();
		setupTrafficLight();
		
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
		setBounds(100, 100, 450, 353);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pnTrafficLight = new TrafficLightPanel();
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
	
	private void setupTrafficLight() {
		pnTrafficLight.setBackground(new Color(173, 216, 230));
		pnTrafficLight.setPreferredSize(new Dimension(160, 260));
		contentPane.add(pnTrafficLight, BorderLayout.CENTER);
	}
	
	private void exit() {
		try {
			client.sendOff();
		} 
		catch (IOException e) {
			System.err.println("Error sending 'OFF' to server: " + e.getMessage());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == this.mntmHelp) {
			 new MessageScreen(this,"Help",Information.getHelpClientText());
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
