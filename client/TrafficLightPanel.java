package client;

import javax.swing.JPanel;

import common.TrafficLightState;

import java.awt.Graphics;
import java.awt.Color;

public class TrafficLightPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Color go;
	Color attention;
	Color stop;
	
	public TrafficLightPanel() {
		super();
		go = Color.gray;
		attention = Color.gray;
		stop = Color.gray;
	}
		
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.black);
		g.fillRect(130, 10, 150, 250);
		
		g.setColor(Color.black);
		g.drawRect(130, 10, 150, 250);
		
		g.setColor(stop);
		g.fillOval(180, 40, 50, 50);
		
		g.setColor(attention);
		g.fillOval(180, 110, 50, 50);
		
		g.setColor(go);
		g.fillOval(180, 180, 50, 50);
	}
	
	public void changeColor(TrafficLightState state) {
		stop = Color.gray;
		attention = Color.gray;
		go = Color.gray;
		
		if(state == TrafficLightState.RED) {
			stop = Color.red;
		}
		else if (state == TrafficLightState.GREEN) {
			go = Color.green;
		}
		else if(state == TrafficLightState.YELLOW) {
			attention = Color.yellow;
		}
		
		repaint();
	}
}
