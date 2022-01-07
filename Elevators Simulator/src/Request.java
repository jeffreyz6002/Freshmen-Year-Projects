public class Request {
    private int sourceFloor; // where the customer of the request is being picked up
    private int destinationFloor; // where the customer of the request is heading to
    private int timeEntered; // when the request was first ordered

    /**
     * Sets the basic information about the request, including time it was made and number of floors the requested building has
     * also generates random locations of source and destination of request
     * @param timeEntered time when request was made
     * @param numFloors number of floors made request has in the building
     */
    public Request(int timeEntered, int numFloors) {
        this.timeEntered = timeEntered;
        sourceFloor = (int) (Math.random() * numFloors) + 1;
        destinationFloor = (int) (Math.random() * numFloors) + 1;
    }

    /**
     * Sets source floor of request
     * @param sourceFloor source floor of request
     */
    public void setSourceFloor(int sourceFloor){
        this.sourceFloor = sourceFloor;
    }

    /**
     * Sets destination floor of request
     * @param destinationFloor destination floor of request
     */
    public void setDestinationFloor(int destinationFloor){
        this.destinationFloor = destinationFloor;
    }

    /**
     * Sets time when request was first made
     * @param timeEntered tune when request was made
     */
    public void setTimeEntered(int timeEntered){
        this.timeEntered = timeEntered;
    }

    /**
     * Gets source floor of request
     * @return source floor of request
     */
    public int getSourceFloor(){
        return sourceFloor;
    }

    /**
     * Gets destination floor of request
     * @return destination floor of request
     */
    public int getDestinationFloor(){
        return destinationFloor;
    }

    /**
     * Gets time when request was made
     * @return time when request was made
     */
    public int getTimeEntered(){
        return timeEntered;
    }
}
