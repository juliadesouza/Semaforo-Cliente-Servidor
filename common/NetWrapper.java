package common;
import java.io.Serializable;

public class NetWrapper implements Serializable{
	private static final long serialVersionUID = 1L;
	private TrafficLightState state;
	
	public NetWrapper(TrafficLightState state) {
		super();
		this.state = state;
	}
	
	public final TrafficLightState getState() {
		return state;
	}
	
	public final void setState(TrafficLightState state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return state.toString();
	}
}
