import java.util.ArrayList;

public class Simulator {
    /**
     * Simulates elevator when elevator can fit only one person at a time
     * @param probability probability of new request
     * @param numFloors number of floors in simulation
     * @param numElevator number of elevators in simulation
     * @param simulations number of simulation rounds to run
     */
    public static void Simulator(double probability, int numFloors, int numElevator, int simulations){

        int waitTime = 0; // total time waited for elevator to receive request
        int numRequest = 0; // total number of request completed
        boolean newRequestOccurs; // true/false if new request occurs
        RequestQueue requestQueue = new RequestQueue(); // Creates queue
        Request requestAssignment; // request that elevator will follow
        BooleanSource booleanSource; // Determines new request or not

        Request newRequest = null; // variable for new request

        ArrayList <Elevator> elevatorsList = new ArrayList<Elevator>(); // Creates x number of elevators and puts them into an array
        for(int i = 0; i < numElevator; i++){
            elevatorsList.add(new Elevator());
        }

        for(int i = 0; i < simulations; i++) { // Running x simulations
            // Step 1: Check for Passenger to enter
            booleanSource = new BooleanSource(probability);
            newRequestOccurs = booleanSource.occurs();
            if(newRequestOccurs){
                newRequest = new Request(i,numFloors); // Adds new request to queue
                requestQueue.enqueue(newRequest);
            }

            // Step 2: Assign Passenger to elevator
            for(int j = 0; j < numElevator; j++){
                if(requestQueue.size() != 0 && elevatorsList.get(j).getElevatorState() == Elevator.IDLE){
                    requestAssignment = requestQueue.dequeue();
                    elevatorsList.get(j).setRequest(requestAssignment);
                    elevatorsList.get(j).setElevatorState(Elevator.TO_SOURCE);
                    elevatorsList.get(j).getRequest().setTimeEntered(i);
                    elevatorsList.get(j).getRequest().setSourceFloor(requestAssignment.getSourceFloor());
                    elevatorsList.get(j).getRequest().setDestinationFloor(requestAssignment.getDestinationFloor());
                }
            }

            // Step 3: Increment/Decrement floors on elevator
            for(int k = 0; k < numElevator; k++) {
                    // Part a: to source, elevator below source
                    if (elevatorsList.get(k).getElevatorState() == Elevator.TO_SOURCE &&
                            elevatorsList.get(k).getCurrentFloor() < elevatorsList.get(k).getRequest().getSourceFloor()) {
                        elevatorsList.get(k).setCurrentFloor(elevatorsList.get(k).getCurrentFloor() + 1);
                    }
                    // Part b: to source, elevator above source
                    if (elevatorsList.get(k).getElevatorState() == Elevator.TO_SOURCE &&
                            elevatorsList.get(k).getCurrentFloor() > elevatorsList.get(k).getRequest().getSourceFloor()) {
                        elevatorsList.get(k).setCurrentFloor(elevatorsList.get(k).getCurrentFloor() - 1);
                    }
                    // Part c: to dest, elevator below dest
                    if (elevatorsList.get(k).getElevatorState() == Elevator.TO_DESTINATION &&
                            elevatorsList.get(k).getCurrentFloor() < elevatorsList.get(k).getRequest().getDestinationFloor()) {
                        elevatorsList.get(k).setCurrentFloor(elevatorsList.get(k).getCurrentFloor() + 1);
                    }
                    // Part d: to dest, elevator above dest
                    if (elevatorsList.get(k).getElevatorState() == Elevator.TO_DESTINATION &&
                            elevatorsList.get(k).getCurrentFloor() > elevatorsList.get(k).getRequest().getDestinationFloor()) {
                        elevatorsList.get(k).setCurrentFloor(elevatorsList.get(k).getCurrentFloor() - 1);
                }
            }
            // Step 4: Change priorities of elevator if floor == source
                for(int j = 0; j < numElevator; j++) {
                        if (elevatorsList.get(j).getElevatorState() == Elevator.TO_SOURCE &&
                                elevatorsList.get(j).getCurrentFloor() == elevatorsList.get(j).getRequest().getSourceFloor()) {
                            waitTime += (i - elevatorsList.get(j).getRequest().getTimeEntered());
                            numRequest++;
                            elevatorsList.get(j).setElevatorState(Elevator.TO_DESTINATION);
                    }
                }
            // Step 5: if elevator is at destination, state == idle
            for(int j = 0; j < numElevator; j++) {
                    if (elevatorsList.get(j).getElevatorState() == Elevator.TO_DESTINATION &&
                            elevatorsList.get(j).getCurrentFloor() == elevatorsList.get(j).getRequest().getDestinationFloor()) {
                        elevatorsList.get(j).setElevatorState(Elevator.IDLE);
                    }
            }
        }

        // Outputs basic information
        System.out.println("\nTotal Wait Time: " +waitTime);
        System.out.println("Total Requests: " +numRequest);
        if(numRequest != 0) {
            System.out.printf("Average Wait Time: %.2f", (double) waitTime / numRequest);
        }
        else {
            System.out.println("Average Wait Time: 0");
        }

    }
}