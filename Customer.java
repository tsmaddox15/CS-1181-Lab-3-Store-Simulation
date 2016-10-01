package cs1181simulationproject;

/**
 * The Customer class collects all the information pertaining to a 
 * customer and their experience shopping in a store.
 * 
 * @author rvolkers
 */
public class Customer 
{
    private int orderSize;
    private double itemSelectionTime;
    private double arrivalTime;
    private double shoppingDone;
    private double checkOutDone = 0;
    private int checkOutLine = -1;
    private double orderCheckoutTime;
    /**
     * Certain information must be provided to create a customer.
     * All values must be greater than 0.
     * @param orderSize the number of items the customer is going to shop for 
     * @param itemSelectionTime the time in minutes to select each item
     * @param arrivalTime the time in minutes the customer arrives in the store after the store opens
     * @throws Exception if any of the parameters are less than or equal to 0
     */
    public Customer(int orderSize, double itemSelectionTime, double arrivalTime) throws Exception
    {
        if(orderSize <= 0 || itemSelectionTime <= 0 || arrivalTime < 0)
            throw new Exception("Invalid customer parameters");
        this.orderSize = orderSize;
        this.itemSelectionTime = itemSelectionTime;
        this.arrivalTime = arrivalTime;
        shoppingDone = arrivalTime + orderSize * itemSelectionTime;
    }
    /**
     * 
     * @return the number of items in the customer order
     */
    public int getOrderSize() {
        return orderSize;
    }
    /**
     * 
     * @return the time the customer finishes shopping
     */
    public double getShoppingDone() {
        return shoppingDone;
    }
    /**
     * 
     * @return the time the customer finishes checking out and leaves the store
     */
    public double getCheckOutDone() {
        return checkOutDone;
    }
    /**
     * 
     * @param checkOutDone the time the customer finishes checking out and leaves the store
     * @throws Exception if the parameter is less than or equal to the shopping done time
     */
    public void setCheckOutDone(double checkOutDone) throws Exception
    {
        if(checkOutDone <= shoppingDone)
            throw new Exception("Invalid time for check out done");
        this.checkOutDone = checkOutDone;
    }
    /**
     * 
     * @return the number of the check out line the customer is assigned to, -1 if not assigned yet
     */
    public int getCheckOutLine() {
        return checkOutLine;
    }
    /**
     * 
     * @param checkOutLine the number of the check out line the customer is assigned to
     * @throws Exception if the check out line number is less than 0
     */
    public void setCheckOutLine(int checkOutLine) throws Exception
    {
        if(checkOutLine < 0)
            throw new Exception("Invalid checkout line number");
        this.checkOutLine = checkOutLine;
    }
    /**
     * 
     * @param orderCheckoutTime the time needed to checkout this customer's order
     * @throws Exception if the parameter is less than or equal to 0
     */
    public void setOrderCheckoutTime(double orderCheckoutTime) throws Exception
    {
        if(orderCheckoutTime <= 0)
            throw new Exception("Invalid order checkout time");
        this.orderCheckoutTime = orderCheckoutTime;
    }   
    /**
     * This method calculates the amount of time the customer was waiting in a checkout time.
     * It uses the formula check_out_done_time - shopping_done_time - order_processing_time
     * @return the time spent waiting in line from the above formula
     * @throws Exception if the checkout done time has not been set
     */
    public double getCustomerWaitTime() throws Exception
    {
        if(checkOutDone == 0)
            throw new Exception("Customer wait time not available until checkout is done");
        return checkOutDone - shoppingDone - orderCheckoutTime;
    }    
    /**
     * 
     * @return the time of this customer's next event, either when the customer finishes shopping
     * or when the customer completes checking out and leaves the store
     */
    public double getNextEventTime()
    {
        return checkOutDone == 0 ? shoppingDone : checkOutDone;
    }
    /**
     * 
     * @param expressLaneLimit returns true if customer's order size is less than or equal to the lane limit
     * @return true if the customer's order size is less than or equal to the parameter value
     */
    public boolean useExpressLane(int expressLaneLimit)
    {
        return orderSize <= expressLaneLimit;
    }
    /**
     * 
     * @return a formatted string containing all customer information
     */
    @Override
    public String toString() {
        return String.format("Customer{ oSize = %2d, itemTime = %3.1f, arrivedAt: %6.2f, shoppingDone: %7.2f, checkOutDone: %7.2f", 
                orderSize, itemSelectionTime, arrivalTime, shoppingDone, checkOutDone);
    }

}
