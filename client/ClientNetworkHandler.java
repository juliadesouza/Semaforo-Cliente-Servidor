package client;
import java.io.*;
import java.net.*;
import common.Connection;
import common.NetWrapper;
import common.TrafficLightState;

public class ClientNetworkHandler {
	private Client client;
	private int serverPort;
    private InetAddress address;
    private TrafficLightClientWindow frame;
    private int size;
    
    public static void main(String[] args){
		try {
			ClientNetworkHandler clientNetwork = new ClientNetworkHandler();
			clientNetwork.init();
		} 
		catch(SocketException e) {
			System.err.println("Client socket error: " + e.getMessage());
		}
		catch(UnknownHostException e) {
			System.err.println("Host error: the input host is unknown. " + e.getMessage());
		}
		catch(ClassNotFoundException e) {
			System.err.println("Error deserializing the received packet: " + e.getMessage());
		}
		catch(IOException e) {
			System.err.println("Errorsending/receiving the received packet: " + e.getMessage());
		}
    }
    
    public ClientNetworkHandler() throws SocketException, UnknownHostException {
    	client = new Client();
		serverPort = Connection.getServerPort();
    	address = InetAddress.getByName(Connection.getHostname());
    	size = Connection.getBufferSize();
    	
    	frame = new TrafficLightClientWindow(this);
		frame.setVisible(true);
    }
    
    public void init() throws IOException, ClassNotFoundException {
    	while(true) {
        	send();
            receive();
    	}
    }
		
	private void send() throws IOException {
		byte[] sendData = Connection.serialize(client.getWrapper());	
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, serverPort);
        client.getSocket().send(sendPacket);
	}
	
	private void receive() throws IOException, ClassNotFoundException {
		byte[] receiveData = new byte[size];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
 		client.getSocket().receive(receivePacket);
 		client.setWrapper((NetWrapper) Connection.deserialize(receivePacket));
 		validateReceivePacket();
	}
	
	private void validateReceivePacket() {
		if(client.getWrapper().getState() == TrafficLightState.OFF) {
			System.exit(0);
		}
		else {
			frame.pnTrafficLight.changeColor(client.getWrapper().getState());
		}
	}
	
	public void sendOff() throws IOException {
		client.getWrapper().setState(TrafficLightState.OFF);
		send();
	}
}