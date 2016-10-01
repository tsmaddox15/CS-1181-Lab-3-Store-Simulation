package cs1181simulationproject;

import java.io.File;

/**
 *
 * @author Taylor Maddox Instructor: Rick Volker TA: Sai Polamarasetty
 */
public class CS1181SimulationProject {

    public static void main(String[] args) throws Exception {
        File customerFile = new File("CustomerArrivals.txt");
        File LaneFile = new File("CheckoutLanes.txt");
        Simulation sim = new Simulation(customerFile, LaneFile);
        sim.shoppingDone();
        sim.dumpData();
    }

}
