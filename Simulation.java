package cs1181simulationproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author tsmad_000
 */
public class Simulation {

    private CheckOutLane[] normalLane;
    private CheckOutLane[] expressLane;
    private OrderedCustomerList list;
    private OrderedCustomerList list2 = new OrderedCustomerList();

    /**
     * Constructs a simulation of the store base on two txt files you give it.
     *
     * @param customerTxt The file with the customer info
     * @param checkOutLaneTxt The file with the info for checkout lanes.
     * @throws Exception
     */
    Simulation(File customerTxt, File checkOutLaneTxt) throws Exception {
        readCustomer(customerTxt);
        readLane(checkOutLaneTxt);

    }

    /**
     * Reads the customer txt file and creates customer objects and adds them to a list.
     * @param file the file containing the customer info.
     * @return list, contains list of customers.
     * @throws Exception
     */
    public OrderedCustomerList readCustomer(File file) throws Exception {
        list = new OrderedCustomerList();
        try {
            Scanner customerFile = new Scanner(file);
            double arrivalTime;
            int items;
            double itemSelectionTime;

            while (customerFile.hasNextDouble()) {
                arrivalTime = customerFile.nextDouble();
                items = customerFile.nextInt();
                itemSelectionTime = customerFile.nextDouble();
                Customer c = new Customer(items, itemSelectionTime, arrivalTime);
                list.insert(c);

            }
            customerFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("No File to read.");
        }

        return list;
    }

    /**
     * Reads the file containing the lane info and separates them into regular and express lanes/
     * @param file file containing info about the lanes.
     */
    public void readLane(File file) {
        //list2 = new OrderedCustomerList();

        try {
            Scanner laneReader = new Scanner(file);
            //index for normal lanes
            int numOfLanes;
            //index for express lanes
            int numOfLanes2;
            double checkOutTime;
            double paymentTime;

            numOfLanes = laneReader.nextInt();
            normalLane = new CheckOutLane[numOfLanes];
            for (int i = 0; i < normalLane.length; i++) {
                checkOutTime = laneReader.nextDouble();
                paymentTime = laneReader.nextDouble();
                normalLane[i] = new CheckOutLane(checkOutTime, paymentTime);
                laneReader.nextLine();

            }

            numOfLanes2 = laneReader.nextInt();
            expressLane = new CheckOutLane[numOfLanes2];
            for (int i = 0; i < expressLane.length; i++) {
                checkOutTime = laneReader.nextDouble();
                paymentTime = laneReader.nextDouble();
                expressLane[i] = new CheckOutLane(checkOutTime, paymentTime);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No lane file");
        }
    }

    /**
     * Print out the list of customer info and the info about the lanes.
     */
    public void dumpData() {
        //Gets the Customer info
        list.listDump();
        System.out.println("Lanes   Customers Served    Max Line Length     Average Line Length     Average Cust Wait ");
       
        //Gets the regular lane info
        for (int i = 0; i < normalLane.length; i++) {

            System.out.println("Reg Lane:    " + normalLane[i].toString());

        }
        
        //Gets the express lane info
        for (CheckOutLane e : expressLane) {
            // System.out.println("Exp Lanes:");
            System.out.println("Exp Lanes:   " + e.toString());
        }
    }

    /**
     * Either puts a customer in a lane or removes them from it based on if there checkout done is complete.
     * @throws Exception
     */
    public void shoppingDone() throws Exception {

        while (!list.isEmpty()) {
            Customer tmp = list.getNext();
            int num = 0;
            int num2 = 0;
            //If checks if checkoutdone is 0 which will determine need to be put in a lane depending on there item size
            //There is an else statment that will assume checkoutdone != zero which will call the getLane and the 
            // remove customer method based on which lane they were in.
            if (tmp.getCheckOutDone() == 0) {
                if (tmp.useExpressLane(12)) {
                    for (int i = 0; i < expressLane.length; i++) {
                        if (expressLane[i].getCustomersWatiting() < expressLane[num].getCustomersWatiting()) {
                            num = i;
                        }
                    }
                    expressLane[num].addCustomer(tmp);
                    tmp.setCheckOutLine(num);

                } else {
                    for (int j = 0; j < normalLane.length; j++) {
                        if (normalLane[j].getCustomersWatiting() < normalLane[num2].getCustomersWatiting()) {
                            num2 = j;
                        }
                    }

                    normalLane[num2].addCustomer(tmp);
                    tmp.setCheckOutLine(num2);

                }
                list.insert(tmp);
            } else {
                if (tmp.useExpressLane(12)) {
                    expressLane[tmp.getCheckOutLine()].removeCustomer(tmp);

                } else {
                    normalLane[tmp.getCheckOutLine()].removeCustomer(tmp);

                }
            }
        }

    }
}
