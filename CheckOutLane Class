package cs1181simulationproject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tsmad_000
 */
public class CheckOutLane {

    private double checkOutTime = 0.0; //Time per item
    private double paymentTime = 0.0; //Time for payment process
    private int customersWaiting; // Time customer is waiting 
    private int customersServed; // Number of customers served
    private int maxWaitLine;    // longest lane there has been
    private double runningTotalCust; // running total of customers
    private double runningWaitTime; // running total of customers wait time 
    private double lastCustTime; // time when last customer completed checkout.

    /**
     * Constructs a lane.
     * @param checkOutTime Time it takes to check out each item.
     * @param paymentTime Time to process the payment
     */
    CheckOutLane(double checkOutTime, double paymentTime) {
        this.checkOutTime = checkOutTime;
        this.paymentTime = paymentTime;
    }

    /**
     * Adds a customer to the a lane and determines the time the customer waited in line and the process of getting
     * checked out.
     * @param tmp Customer object.
     * @throws Exception
     */
    public void addCustomer(Customer tmp) throws Exception {
        runningWaitTime = tmp.getCheckOutDone() - tmp.getShoppingDone();

        if (customersWaiting > 1) {
            runningTotalCust = runningTotalCust + customersWaiting;
            //customersWaiting++
        }
        customersWaiting++;

        if (customersWaiting > maxWaitLine) {
            maxWaitLine = customersWaiting;
        }

        if (customersWaiting == 1) {
            lastCustTime = (tmp.getOrderSize() * checkOutTime) + paymentTime + lastCustTime + tmp.getShoppingDone();
        } else {
            lastCustTime += (tmp.getOrderSize() * checkOutTime) + paymentTime;
        }
        //  tmp.setCheckOutDone(lastCustTime);
        // double customerWait = tmp.getCheckOutDone() - tmp.getShoppingDone() - paymentTime - (tmp.getOrderSize() * checkOutTime);
        tmp.setCheckOutDone(lastCustTime);

    }

    /**
     * Removes a customer from the list once the are done being checked out. It adds
     * @param tmp Customer object.
     */
    public void removeCustomer(Customer tmp) throws Exception {
        customersWaiting--;
        customersServed++;
        runningWaitTime = runningWaitTime + tmp.getCustomerWaitTime();
        System.out.println(tmp);
    }

    /**
     * Determines the average lane length of the lane object.
     * @return Average length of the line.
     */
    public double averageLine() {
        double averageWaiting = runningTotalCust / customersServed;
        return averageWaiting;
    }

    /**
     * Determines the average lane length of the lane object
     * @return averageCustWaitTime, Average time a customer waited in line.
     * @throws Exception
     */
    public double averageCustWaitTime() throws Exception {
        double averageWaitTime = runningWaitTime / customersServed;
        return averageWaitTime;
    }

    /**
     * Gets the customer waiting value
     * @return getCustomersWatiting, Amount of customers waiting. in the line.
     */
    public double getCustomersWatiting() {

        return customersWaiting;
    }

    /**
     * Gets the amount of customers that have been served.
     * @return getCustomersServed, Amount of customers served in that lane.
     */
    public double getCustomersServed() {

        return customersServed;
    }

    /**
     * Get the max length of the lane.
     * @return maxWaitLine, Max amount of people waiting in line
     */
    public int getmaxWaitLine() {

        return maxWaitLine;
    }

    /**
     * To string that displays info about the lane.
     * @return 
     */
    @Override
    public String toString() {
        try {
            return String.format("%5d%18d%23.2f%26.2f", customersServed, maxWaitLine, this.averageLine(), this.averageCustWaitTime());
        } catch (Exception ex) {
            Logger.getLogger(CheckOutLane.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
