package server;

import java.net.InetAddress;
import common.NetWrapper;
import common.TrafficLightState;

public class ClientPacket {
	private InetAddress clientAddress;
	private int clientPort;
	private NetWrapper wrapper;
	
	public ClientPacket(InetAddress address, int port, NetWrapper wrapper) {
		this.clientAddress = address;
		this.clientPort = port;
		this.wrapper = wrapper;
	}
	
	public int getClientPort() {
		return this.clientPort;
	}
	
	public InetAddress getClientAddress() {
		return this.clientAddress;
	}
	
	public NetWrapper getWrapper() {
		return this.wrapper;
	}
	
	public void changeState() {
		TrafficLightState currentState = wrapper.getState();
		wrapper.setState(TrafficLightState.nextState(currentState));
	}

	@Override
	public String toString() {
		return "[" + clientAddress.toString() + ":" + Integer.toString(clientPort) + "]" + " >> " + wrapper.toString() + "\n";
	}
}
