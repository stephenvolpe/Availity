package Availity;

import Enrollees.Enrollees;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class AvailityTwo {
    //Key is Company:UserId
    Map<Pair<String, String>, Enrollees> records = new TreeMap<>();
    public static void main (String [] args) {

        String input = "TomUno,Tom,Uno,1,CompanyA\n";
        input += "TimDos,Tim,Dos,1,CompanyB\n";
        input += "JimTres,Tim,Tres,1,CompanyC\n";
        input += "TomUno,Tom,Uno,2,CompanyA\n";
        readAfileAndSortByLastAndFirstNameAscendingAndRemoveDuplicateUserIDsForTheSameInsuranceCompany(input);
    }

    public static void readAfileAndSortByLastAndFirstNameAscendingAndRemoveDuplicateUserIDsForTheSameInsuranceCompany (String input) {
        Map<Pair<String, String>, Enrollees> CompanyAndUserIdTOEnrollees = new HashMap<>();
        Map<String, PriorityQueue<Enrollees>> companyToEnrollees = new HashMap<>();

        int lengthOfEnrollee = 5;
            Scanner scanner = new Scanner(input);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                //There are libraries to read this with java, i'd prefer to use Guava Splitter as I think it's cleaner but I am trying to stay with STD lib as I think that's what the question is asking for...
                String [] tokens = line.trim().split(",");
                if (tokens.length != lengthOfEnrollee) {
                    System.out.println("Length of fields mismatch - expecting : " + lengthOfEnrollee + " while getting: " + tokens.length);
                    continue;
                }
                int version;
                try {
                    version = Integer.parseInt(tokens[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Found: " + tokens[3] + " when expecting an integer.");
                    continue;
                }


                //Lastly, if there are duplicate User Ids for the same Insurance Company, then only the record with the highest version should be included. (Doesn't talk about ties?)
                Enrollees enrollee = new Enrollees(tokens[0], tokens[1], tokens[2], version, tokens[4]);  //read the content of the file and separate enrollees by insurance company in its own file.



                //Additionally, sort the contents of each file by last and first name (ascending) //PriorityQueue with the key of Company
                if (companyToEnrollees.containsKey(enrollee.getInsuranceCompany())) {
                    PriorityQueue<Enrollees> pQueue = companyToEnrollees.get(enrollee.getInsuranceCompany());
                    if (pQueue.contains(enrollee)) {
                        //This is not what a priorityQueue is designed for (indexing)
                        for (Enrollees enr : pQueue) {
                            if (!enr.equals(enrollee)) {
                                continue;
                            }

                            //if the incoming enrollee version is higher, then it replaces the other one.
                            if (enrollee.getVersion() > enr.getVersion()) {
                                pQueue.remove(enr);
                                pQueue.add(enrollee);
                                break;
                            }
                        }

                    } else {
                        pQueue.add(enrollee);
                    }
                    //PriorityQueue<Enrollees> thisPQ = companyToEnrollees.get(enrollee.getInsuranceCompany());
                    //thisPQ.add(enrollee);
                    //companyToEnrollees.put(enrollee.getInsuranceCompany(), thisPQ);
                } else { // Need to stand up the pQueue
                    PriorityQueue<Enrollees> pQueue = new PriorityQueue<Enrollees>(new Comparator<Enrollees>() {
                        @Override
                        public int compare(Enrollees e1, Enrollees e2) {
                            return Enrollees.compareLastAndFirstName(e1,e2);
                        }
                    });
                    companyToEnrollees.put(enrollee.getInsuranceCompany(), pQueue);
                    pQueue.add(enrollee);
                }
            }
            scanner.close();

            System.out.println();
            System.out.println();
            System.out.println();

            for (Map.Entry<String, PriorityQueue<Enrollees>> kvp : companyToEnrollees.entrySet()) {
                for (Enrollees e : kvp.getValue()) {
                    System.out.println(e.formatForFile());
                }
            }
    }
































    public static File readAfileAndSortByLastAndFirstNameAscendingAndRemoveDuplicateUserIDsForTheSameInsuranceCompany (File file) {
        Map<Pair<String, String>, Enrollees> CompanyAndUserIdTOEnrollees = new HashMap<>();
        Map<String, PriorityQueue<Enrollees>> companyToEnrollees = new HashMap<>();

        int lengthOfEnrollee = Enrollees.class.getFields().length;
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //There are libraries to read this with java, i'd prefer to use Guava Splitter as I think it's cleaner but I am trying to stay with STD lib as I think that's what the question is asking for...
                String [] tokens = line.trim().split(",");
                if (tokens.length != lengthOfEnrollee) {
                    System.out.println("Length of fields mismatch - expecting : " + lengthOfEnrollee + " while getting: " + tokens.length);
                    continue;
                }
                int version;
                try {
                    version = Integer.parseInt(tokens[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Found: " + tokens[3] + " when expecting an integer.");
                    continue;
                }


                //Lastly, if there are duplicate User Ids for the same Insurance Company, then only the record with the highest version should be included. (Doesn't talk about ties?)
                Enrollees enrollee = new Enrollees(tokens[0], tokens[1], tokens[2], version,tokens[4]);  //read the content of the file and separate enrollees by insurance company in its own file.



                //Additionally, sort the contents of each file by last and first name (ascending) //PriorityQueue with the key of Company
                if (companyToEnrollees.containsKey(enrollee.getInsuranceCompany())) {
                    PriorityQueue<Enrollees> pQueue = companyToEnrollees.get(enrollee.getInsuranceCompany());
                    if (pQueue.contains(enrollee.getUserID())) {
                        //This is not what a priorityQueue is designed for (indexing)
                        for (Enrollees enr : pQueue) {
                            if (!enr.equals(enrollee)) {
                                continue;
                            }

                            //if the incoming enrollee version is higher, then it replaces the other one.
                            if (enrollee.getVersion() > enr.getVersion()) {
                                pQueue.remove(enr);
                                pQueue.add(enrollee);
                                break;
                            }
                        }

                    } else {
                        pQueue.add(enrollee);
                    }
                    PriorityQueue<Enrollees> thisPQ = companyToEnrollees.get(enrollee.getInsuranceCompany());
                    thisPQ.add(enrollee);
                    companyToEnrollees.put(enrollee.getInsuranceCompany(), thisPQ);
                } else { // Need to stand up the pQueue
                    PriorityQueue<Enrollees> pQueue = new PriorityQueue<Enrollees>( new Comparator<Enrollees>() {
                        @Override
                        public int compare(Enrollees e1, Enrollees e2) {
                            return Enrollees.compareLastAndFirstName(e1,e2);
                        }
                    });
                    companyToEnrollees.put(enrollee.getInsuranceCompany(), pQueue);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new File("C:\\Ok.txt");
    }




























    //I am sure there is some better way to do this with optional or something but I'm not sure and overloading this seems to be "acceptable" to me.
    public static Map<Pair<String, String>, Enrollees> processCSV(File file) {
        return processCSV(file, new HashMap<>());
    }

    public static Map<Pair<String, String>, Enrollees> processCSV(File file, HashMap<Pair<String, String>, Enrollees> inputRecords) {
        List<String> lines;
        try {
            lines = readFileIntoList(file);
        } catch (FileNotCSVException e) {
            e.printStackTrace();
            return inputRecords;
        }
        return inputRecords;
    }





    public static List<String> readFileIntoList(File file) throws FileNotCSVException {
        List<String> lines = Collections.emptyList();
        if (!file.getPath().endsWith(".csv")) {
            throw new FileNotCSVException();
        }
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }


    private static class FileNotCSVException extends Exception {
        //this is awful but i don't think this is what we care about...
        public static void main(String [] args) {
            System.out.println("This isn't a CSV file.");
        }
    }
}
