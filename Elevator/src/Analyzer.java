import java.util.*;
public class Analyzer {
    public static void main(String [] args){
        Scanner stdin = new Scanner(System.in);

        double probability; // probability of request to appear
        int numFloors; // number of floors in building that simulation is ran in
        int numElevators; // number of elevators in building that simulation is ran in
        int simulations; // total number of simulations that will run

        System.out.println("Welcome to the Elevator simulator!");

        System.out.print("\nPlease enter the probability of arrival for Requests: "); // Takes in probability
        probability = stdin.nextDouble();
        while(probability <= 0 || probability > 1){ // Loops probability input if probability input is not between 0 and 1
            System.out.print("Please enter the probability of arrival again. Previous input is not valid. ");
            probability = stdin.nextDouble();
        }

        System.out.print("Please enter the number of floors: "); // Takes in number of floors in building of simulation
        numFloors = stdin.nextInt();
        while( numFloors < 2){ // Loops number of floor input if number of floors is below 2
            System.out.print("Please enter the number of floors again. Previous input is not valid. ");
            numFloors = stdin.nextInt();
        }

        System.out.print("Please enter the number of elevators: "); // Takes in number of elevators
        numElevators = stdin.nextInt();
        while(numElevators <= 0){ // Loops number of elevators input if number of elevators is less than 0
            System.out.print("Please enter the number of elevators again. Previous input is not valid. ");
            numElevators = stdin.nextInt();
        }

        System.out.print("Please enter the length of the simulation (in time units): "); // Takes in number of simulations
        simulations = stdin.nextInt();
        while(simulations <= 0){ // Loops number of simulations input if simulation is less than 0
            System.out.println("Please enter the length of simulation (in time units) again. Previous input is not valid. ");
            simulations = stdin.nextInt();
        }

        Simulator.Simulator(probability,numFloors,numElevators,simulations);
    }
}
