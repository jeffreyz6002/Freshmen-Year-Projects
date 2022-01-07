public class BooleanSource { //Note : Took from class powerpoint
    private double probability; // Variable for probability of customer to arrive


    /**
     * Constructor to set probability to parameter
     * @param p a number between 0.0 and 1.0 inclusive that determines if customer arrives
     * @throws IllegalArgumentException exception is hit when parameter is not within stated range
     */
    public BooleanSource(double p) throws IllegalArgumentException {
        if( p < 0 || p > 1){
            throw new IllegalArgumentException("Probability input is not between 0.0 and 1.0.");
        }
        probability = p;
    }

    /**
     * Sets probability of customer arrival
     * @param p probability of customer arrival
     */
    public void setProbability(double p){
        probability = p;
    }

    /**
     * Gets probability of customer arrival
     * @return probability of customer arrival
     */
    public double getProbability(){
        return probability;
    }

    /**
     * Randomizes event of customer arrival based off probability
     * @return change of customer arriving
     */
    public  boolean occurs(){
        return (Math.random() < probability);
    }
}
