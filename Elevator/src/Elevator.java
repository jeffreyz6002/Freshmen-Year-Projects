public class Elevator {
    static final int IDLE = 0; // Variable that illustrates an idle elevator
    static final int TO_SOURCE = 1; // Variable that illustrates an elevator going to source
    static final int TO_DESTINATION = 2; // Variable that illustrates an elevator going to destination
    private int currentFloor; // number of current floor
    private int elevatorState; // number that represents elevator state
    Request request; // current request being performed by elevator

    /**
     * Default constructor
     * sets request to none due to no requests
     * starting floor is 1
     * sets elevatorState to IDLE
     */
    public Elevator(){
        request = null;
        currentFloor = 1;
        elevatorState = IDLE;
    }

    /**
     * Sets current floor
     * @param currentFloor current floor
     */
    public void setCurrentFloor(int currentFloor){ this.currentFloor = currentFloor; }

    /**
     * Sets elevator state
     * @param elevatorState elevator state
     */
    public void setElevatorState(int elevatorState){ this.elevatorState = elevatorState; }

    /**
     * Sets request for elevator
     * @param request request for elevator to follow
     */
    public void setRequest(Request request){ this.request = request; }

    /**
     * Gets request that elevator is following
     * @return request that elevator is currently following
     */
    public Request getRequest(){ return request; }

    /**
     * Gets current floor elevator is on
     * @return current floor elevator is on
     */
    public int getCurrentFloor(){ return currentFloor; }

    /**
     * Gets current elevator state
     * @return current elevator state
     */
    public int getElevatorState() { return elevatorState; }

}
