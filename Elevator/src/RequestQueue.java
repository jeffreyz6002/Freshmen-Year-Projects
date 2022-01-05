import java.util.*;
public class RequestQueue {
    private int front; // Front of queue, the first to leave
    private int rear; // Rear of queue, the last to leave
    ArrayList<Request> q; // Creates queue

    /**
     * Basic constructor
     * gets queue to be empty
     * initializes queue
     */
    public RequestQueue(){
        rear = -1;
        front = -1;
        q = new ArrayList<>();
    }

    /**
     * Adds parameter to the rear of the queue
     * @param newRequest request that is being added to the back of queue
     */
    // Index 0 of ArrayList == Front
    public void enqueue(Request newRequest){
        if(front == -1){ // Sets size of queue to one
            front = 0;
            rear = 0;
        }
        else{ // Increase rear to increase size of queue
            rear++;
        }
        q.add(newRequest);
    }

    /**
     * Removes front element of queue
     * @return request that was the first to be entered into queue
     * @throws EmptyQueueException exception that is thrown when queue is empty
     */
    public Request dequeue() throws EmptyQueueException {
        try {
            Request output;
            if (front == -1) { // Checks for empty queue
                throw new EmptyQueueException();
            }
            output = q.get(front);
            q.remove(front); // Removes oldest element in queue
            if (front == rear) { // Sets queue to empty if current size is 1
                front = -1;
                rear = -1;
            } else { // Pushes front back one to second oldest element
                front = 0;
                rear--;
            }
            return output;
        } catch (EmptyQueueException e) { // throws exception
            System.out.println("Queue is empty. No queue to remove.");
            return null;
        }
    }

    /**
     * Method used to obtain size of queue/arraylist
     * @return size of queue
     */
    public int size(){ return q.size(); }

    /**
     * Checks if queue/arrayList is empty
     * @return true or false depending if arrayList/queue is empty
     */
    public boolean isEmpty(){ return (front == -1); }

}
