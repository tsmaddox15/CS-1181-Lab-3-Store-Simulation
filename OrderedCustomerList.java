package cs1181simulationproject;

/**
 * The OrderedCustomerList class stores customers in sorted order based on customer
 * event times. Only the first customer in the sorted list can be retrieved.
 *
 * @author rvolkers
 */
public class OrderedCustomerList {

    private class Node 
    {
	// NOTE: The member variables are public so the methods
        // of the OrderedCustomerList class have direct access to them
        public Customer customer;
        public double eventTime;
        public Node next;
        // Explicit value constructor for the Node class
        public Node(Customer customer, double time) 
        {
            this.customer = customer;
            eventTime = time;
            next = null;
        }
    }
    
    private Node first;
    /**
     * 
     * @return false if the list is empty
     */
    public boolean isEmpty()
    {
        return first == null;
    }
    /**
     * This method will insert the customer into the list in ascending order 
     * based on the customer's next event time.
     * @param customer a customer object. If null, nothing is inserted.
     */
    public void insert(Customer customer) 
    {
        if(customer == null)
            return;
        // Get the next significant event time for this customer
        double key = customer.getNextEventTime();
        
        Node node = new Node(customer, key);
        
        if(first == null || key < first.eventTime)
        {
            node.next = first;
            first = node;
        }
        else
        {
            Node temp = first;
            while(temp.next != null && key >= temp.next.eventTime)
            {
                temp = temp.next;
            }
            node.next = temp.next;
            temp.next = node;
        }
    }
    /**
     * Get the first customer from the list.
     * @return the first customer in the list is removed and returned
     * @throws Exception if the list is empty
     */
    public Customer getNext() throws Exception
    {
        if (first == null)
            throw new Exception("Cannot getNext from an empty list");
        Customer item = first.customer;
        first = first.next;
        return item;
    }
    /**
     * This method simply outputs the contents of the list, one item per line.
     */
    public void listDump()
    {
        Node tmp = first;
        while(tmp != null)
        {
            System.out.println(tmp.customer);
            tmp = tmp.next;
        }
    }
}
