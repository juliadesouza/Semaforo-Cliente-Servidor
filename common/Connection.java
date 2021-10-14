package common;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;

public final class Connection {
	private static final String hostname = "localhost";
	private static final int serverPort = 17;
	private static final int timer = 3000;
	private static final int bufferSize = 1024;
	
	public static String getHostname() {
		return hostname;
	}

	public static int getServerPort() {
		return serverPort;
	}

	public static int getTimer() {
		return timer;
	}
	
	public static int getBufferSize() {
		return bufferSize;
	}
	
	public static Object deserialize(DatagramPacket incomingPkt) throws ClassNotFoundException, IOException {
		return (new ObjectInputStream(new ByteArrayInputStream(incomingPkt.getData()))).readObject();
	}
	
	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		(new ObjectOutputStream(byteArrayOutputStream)).writeObject(obj);
		return (byteArrayOutputStream.toByteArray());
	}
	
}
