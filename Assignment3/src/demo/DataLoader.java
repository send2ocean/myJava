/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * a simple csv file loder
 * @author send2ocean
 */
public class DataLoader {

    public static List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<Patient>();
        Path patientPath = Paths.get("patients.csv");
//        String s = patientPath.toAbsolutePath().toString();
//        System.out.println("Looking for patients.csv in " + s);
        List<String> patientLines;
        try {
            patientLines = Files.readAllLines(patientPath);
            for (int loop = 1; loop < patientLines.size(); loop++) {
                String line = patientLines.get(loop);
                if ((line != null) && (line != "")) {
                    Patient patient = new Patient(line);
                    patients.add(patient);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return patients;
    }

    public static List<Ambulance> loadAbmulances()   {
        List<Ambulance> ambulances = new ArrayList<Ambulance>();
        Path ambulancePath = Paths.get("ambulances.csv");
//        String s = patientPath.toAbsolutePath().toString();
//        System.out.println("Looking for patients.csv in " + s);
        List<String> ambulanceLines;
        try {
            ambulanceLines = Files.readAllLines(ambulancePath);
            for (int loop = 1; loop < ambulanceLines.size(); loop++) {
            String line = ambulanceLines.get(loop);
            if ((line != null) && (line != "")) {
                Ambulance ambulance = new Ambulance(line);
                ambulances.add(ambulance);
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ambulances;
    }
}
