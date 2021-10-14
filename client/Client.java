package client;
import java.net.DatagramSocket;
import java.net.SocketException;

import common.NetWrapper;
import common.TrafficLightState;

public class Client {
	 private DatagramSocket socket;
	 private NetWrapper wrapper;
	 
	 public Client() throws SocketException{
    	socket = new DatagramSocket();
    	wrapper = new NetWrapper(TrafficLightState.ON);
	 }

	public DatagramSocket getSocket() {
		return this.socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	public NetWrapper getWrapper() {
		return this.wrapper;
	}

	public void setWrapper(NetWrapper wrapper) {
		this.wrapper = wrapper;
	}
}
