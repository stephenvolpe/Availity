package Availity;

import Enrollees.Enrollees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class AvailityTwo {
    public static final String inputPath = "C:\\Users\\sjv\\Availity\\inputs\\";
    public static final String oututPath = "C:\\Users\\sjv\\Availity\\outputs\\";

    public static void main (String [] args) {

        String input = "TomUno,Tom,Uno,1,CompanyA\n";
        input += "TimDos,Tim,Dos,1,CompanyB\n";
        input += "JimTres,Tim,Tres,1,CompanyC\n";
        input += "TomUno,Tom,Uno,2,CompanyA\n";
        //readAfileAndSortByLastAndFirstNameAscendingAndRemoveDuplicateUserIDsForTheSameInsuranceCompany(input);
        readAfileAndSortByLastAndFirstNameAscendingAndRemoveDuplicateUserIDsForTheSameInsuranceCompany(new File (inputPath + "input.txt"));
    }

    public static void readAfileAndSortByLastAndFirstNameAscendingAndRemoveDuplicateUserIDsForTheSameInsuranceCompany (String input) {
        Map<String, PriorityQueue<Enrollees>> companyToEnrollees = new TreeMap<>((Comparator<String>) (s1, s2) -> s1.compareTo(s2));

        int lengthOfEnrollee = 5;
            Scanner scanner = new Scanner(input);
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
                } else { // Need to stand up the pQueue
                    PriorityQueue<Enrollees> pQueue = new PriorityQueue<Enrollees>((Comparator<Enrollees>) (e1, e2) -> Enrollees.compareLastAndFirstName(e1,e2));
                    companyToEnrollees.put(enrollee.getInsuranceCompany(), pQueue);
                    pQueue.add(enrollee);
                }
            }
            scanner.close(); //Honestly can't remember if this should be in a finally as the try catch is very specific in the code (I didnt want this entire code block in a try/catch
            for (Map.Entry<String, PriorityQueue<Enrollees>> kvp : companyToEnrollees.entrySet()) {
                for (Enrollees e : kvp.getValue()) {
                    System.out.println(e.formatForFile());
                }
            }
    }



    public static void readAfileAndSortByLastAndFirstNameAscendingAndRemoveDuplicateUserIDsForTheSameInsuranceCompany (File file) {
        Map<String, PriorityQueue<Enrollees>> companyToEnrollees = new TreeMap<>((Comparator<String>) (s1, s2) -> s1.compareTo(s2));
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Can\'t find file: " + file.getAbsolutePath() + " to read.");
            return;
        }
        int lengthOfEnrollee = 5;
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
                System.out.println("Found: " + tokens[3] + " when expecting an integer.  Skipping this line.");
                continue;
            }


            Enrollees enrollee = new Enrollees(tokens[0], tokens[1], tokens[2], version, tokens[4]);  //read the content of the file and separate enrollees by insurance company in its own file.


            //separate enrollees by insurance company in its own file (The key is the Company name, which will be used for the fileName, and then all of the things in the PriorityQueue are in the sorted order we want so just print it linearly (no sorting)
            //Additionally, sort the contents of each file by last and first name (ascending) //PriorityQueue with the key of Company
            if (companyToEnrollees.containsKey(enrollee.getInsuranceCompany())) {
                PriorityQueue<Enrollees> pQueue = companyToEnrollees.get(enrollee.getInsuranceCompany());
                if (pQueue.contains(enrollee)) {
                    //This is not what a priorityQueue is designed for (indexing)
                    for (Enrollees enr : pQueue) {
                        if (!enr.equals(enrollee)) {
                            continue;
                        }

                        //if the incoming enrollee version is higher, then it replaces the other one. Lastly, if there are duplicate User Ids for the same Insurance
                        // Company, then only the record with the highest version should be included. (Doesn't talk about ties?) (Since first and last name don't have to be the same)
                        if (enrollee.getVersion() > enr.getVersion()) {
                            pQueue.remove(enr);
                            pQueue.add(enrollee);
                            break;
                        }
                    }

                } else {
                    pQueue.add(enrollee);
                }
            } else { // Need to stand up the pQueue as this is the first element in the pQueue
                PriorityQueue<Enrollees> pQueue = new PriorityQueue<Enrollees>((Comparator<Enrollees>) (e1, e2) -> Enrollees.compareLastAndFirstName(e1,e2));
                companyToEnrollees.put(enrollee.getInsuranceCompany(), pQueue);
                pQueue.add(enrollee);
            }
        }
        scanner.close(); //Honestly can't remember if this should be in a finally as the try catch is very specific in the code (I didnt want this entire code block in a try/catch

        for (Map.Entry<String, PriorityQueue<Enrollees>> kvp : companyToEnrollees.entrySet()) {
            String newPath = oututPath + kvp.getKey() + ".txt";
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(newPath);
                for (Enrollees e : kvp.getValue()) {
                    fileWriter.write(e.formatForFile());
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error trying to write to file: " + newPath);
            }
        }

    }
}
