package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import common.Connection;
import common.NetWrapper;
import common.TrafficLightState;

public class Server extends Thread {
	private DatagramSocket socket;
	private Thread send, receive;
	private boolean running;
	private int timer;
	private int size;
	
	private ArrayList<ClientPacket> clients;
	private static TrafficLightServerWindow frame;
		
	public Server(int port) throws SocketException {
		socket = new DatagramSocket(port);
		clients = new ArrayList<ClientPacket>();
		running = true;
		timer = Connection.getTimer();
		size = Connection.getBufferSize();
	}
	
	public static void main(String[] args) {
		try {
			int port = Connection.getServerPort();
			Server server;
			server = new Server(port);
			
			frame = new TrafficLightServerWindow(server);
			frame.setVisible(true);
			
			server.init();
		} 
		catch (SocketException e) {
			System.err.println("Server socket error: " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void init() {
		frame.textArea.append("Waiting for clients connect...\n");
		running = true;
		receive();
		send();
	}
		
	private void send() {
		send = new Thread("Send") {
			public void run() {
				while (running) {
					sendToAll();
				}
			}
		};
		send.start();
	}

	private void receive() {
		receive = new Thread("Receive") {
			public void run() {
				while (running) {
					try {
						byte[] receiveData = new byte[size];
						DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
						socket.receive(receivePacket);
						
						NetWrapper wrapper = (NetWrapper) Connection.deserialize(receivePacket);
						ClientPacket clientPacket = new ClientPacket(receivePacket.getAddress(), receivePacket.getPort(), wrapper);
						
				        if(wrapper.getState() == TrafficLightState.ON) {
				        	clients.add(clientPacket);
						}
				        else if(wrapper.getState() == TrafficLightState.OFF) {
				        	frame.textArea.append("\nRECEIVED MESSAGE 'OFF': " + clientPacket.toString());
				        	removeClient(receivePacket);
						}
					} 
					catch (SocketException e) {
						System.err.println("Server socket error: " + e.getMessage());
					} 
					catch (IOException e) {
						System.err.println("Error receiving the data from client: " + e.getMessage());
					} 
					catch (ClassNotFoundException e) {
						System.err.println("Error processing the package: " + e.getMessage());
					} 
				}
			}
		};
		receive.start();
	}

	private synchronized void sendToAll() {
		try {
			if(clients.size() > 0) {
				frame.textArea.append("\nActive Clients (" + Integer.toString(clients.size()) + "):\n");
		
				for (ClientPacket client:clients) {
					client.changeState();
					frame.textArea.append(client.toString());
					byte[] sendData = Connection.serialize(client.getWrapper());
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.getClientAddress(), client.getClientPort());
					socket.send(sendPacket);
				}
				
				Thread.sleep(timer);
			}
		}
		catch (InterruptedException e) {
			System.err.println("Thread.sleep() error: " + e.getMessage());
		} 
		catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
	}
	
	private void removeClient(DatagramPacket receivePacket) {
		int index = -1;
		
    	for(ClientPacket client: clients) {
    		if(client.getClientPort() == receivePacket.getPort()) {
    			client.getWrapper().setState(TrafficLightState.OFF);
    			index = clients.indexOf(client);
    			break;
    		}
    	}
    	
    	if(index > -1) {
    		String text = "CLIENT REMOVED: " + clients.get(index).toString();
        		    		
    		frame.textArea.append(text);
    		clients.remove(index);
    		
    		if(clients.isEmpty()) {
        		running = false;
        		socket.close();
        		System.exit(0);
        	}
    	}
	}
	
	public void turnOffClients() throws IOException {
		for(ClientPacket client: clients) {
    		client.getWrapper().setState(TrafficLightState.OFF);
    		byte[] sendData = Connection.serialize(client.getWrapper());
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.getClientAddress(), client.getClientPort());
			socket.send(sendPacket);
    	}
	}
	
}