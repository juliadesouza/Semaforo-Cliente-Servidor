package common;

public enum TrafficLightState {
	RED, YELLOW, GREEN, OFF, ON;
	
	public static TrafficLightState nextState(TrafficLightState current) {
		TrafficLightState newState = TrafficLightState.OFF;
		
		switch (current) {
		case GREEN:
			newState = TrafficLightState.YELLOW;
			break;
		case OFF:
			newState = TrafficLightState.OFF;
			break;
		case ON:
			newState = TrafficLightState.RED;
			break;
		case RED:
			newState = TrafficLightState.GREEN;
			break;
		case YELLOW:
			newState = TrafficLightState.RED;
			break;
		default:
			newState = TrafficLightState.OFF;
			break;
		}
		
		return (newState);
	}
}
