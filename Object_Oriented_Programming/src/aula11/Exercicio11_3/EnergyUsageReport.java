package aula11.Exercicio11_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class EnergyUsageReport {
    TreeMap<Integer, List<Double>> custumersMap = new TreeMap<Integer,   List<Double>>();
    public void load(String file) throws FileNotFoundException{
        Scanner input = new Scanner(new FileReader(file));
        while(input.hasNextLine()){
            String[] dataline = input.nextLine().split("\\|");
            List<Double> nums = new ArrayList<Double>();
            for(int i = 1; i<dataline.length; i++){
                nums.add(Double.parseDouble(dataline[i]));
            }
            custumersMap.put(Integer.parseInt(dataline[0]), nums);
        }
    }
    public void addCustomer(Customer customer){
        custumersMap.put(customer.getCustomerId(), customer.getMeterReadings());
    }
    public void removeCustomer(int user){

    }
    public Customer getCustomer(int user){
        Customer customer = new Customer(user, custumersMap.get(user));
        return customer;
    }
    public Double calculateTotalUsage(int user){
        Double usage = 0.0;
        for(Double data: custumersMap.get(user)){
            usage += data;
        }
        return usage;
    }
    public void generateReport(String newfile){
        File file = new File(newfile);

        try {
            PrintWriter writer = new PrintWriter(file);

            for (java.util.Map.Entry<Integer, List<Double>> set : custumersMap.entrySet()) {
                writer.println(set.getKey()+"|"+calculateTotalUsage(set.getKey()));
            }
            writer.close();

            System.out.println("Report created successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
