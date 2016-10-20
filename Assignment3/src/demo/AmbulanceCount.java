/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author send2ocean
 */
public class AmbulanceCount implements Runnable {

    private Ambulance ambulance;
    private MyFrame jframe;
    private Point targetPoint;
    private Date statusTime;
    private static final String[] s = new String[]{"At Station", "Responding", "At Scene", "Transporting", "At Destination", "Returning"};

    public AmbulanceCount(Ambulance abmulance) {
        this.ambulance = abmulance;
    }

    public AmbulanceCount(Ambulance abmulance, MyFrame jframe) {
        this.ambulance = abmulance;
        this.jframe = jframe;
    }

    private int getStatusCode() {
        int code = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(ambulance.getStatus())) {
                code = i;
            }
        }
        return code;
    }

    @Override
    public void run() {
        while (true) {
            if (jframe.isStart) {
                String tname = Thread.currentThread().getName();
                int code = getStatusCode();

                switch (code) {
                    case 0://At station
                        //‘At station’: check if there is a new patient to pick up,
                        //if so, assign the closest unassigned patient to the 
                        //ambulance and change the status to ‘Responding’. 
                        //Otherwise, do nothing.
                        if (ambulance.getPatient() != null) {
                            ambulance.setStatus(s[getStatusCode() + 1]);
                            targetPoint = SimulatioinUtil.getMovedPoint(
                                    ambulance.getLocalPoint(),
                                    ambulance.getPatient().getLocalPoint(),
                                    4);
                            targetPoint.setColor(Ambulance.AMBULANCE_DEFAULT_COLOR);
                            ambulance.setLocalPoint(targetPoint);
                        }
                        break;
                    case 1: //Responding 
                        targetPoint = SimulatioinUtil.getMovedPoint(
                                ambulance.getLocalPoint(),
                                ambulance.getPatient().getLocalPoint(),
                                4);
                        targetPoint.setColor(Ambulance.AMBULANCE_DEFAULT_COLOR);
                        if (SimulatioinUtil.isSamePoint(ambulance.getLocalPoint(), ambulance.getPatient().getLocalPoint())) {
                            //arrivd patient
                            ambulance.setStatus(s[getStatusCode() + 1]);
                            //todo count time begin
                        } else {
                            ambulance.setLocalPoint(targetPoint);
                        }
                        break;
                    case 2: //At scene
                    //todo leave time > 4s end
                    {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(AmbulanceCount.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    ambulance.setStatus(s[getStatusCode() + 1]);

                    break;
                    case 3://Transporting
                        targetPoint = SimulatioinUtil.getMovedPoint(
                                ambulance.getLocalPoint(),
                                Application.HostpitalPoint,
                                3);
                        targetPoint.setColor(Ambulance.AMBULANCE_DEFAULT_COLOR);
                        Patient p = ambulance.getPatient();
                        if (SimulatioinUtil.isSamePoint(ambulance.getLocalPoint(), Application.HostpitalPoint)) {
                            //arrivd hostpital
                            ambulance.setStatus(s[getStatusCode() + 1]);

                            //todo count time begin
                        } else {
                            ambulance.setLocalPoint(targetPoint);
                            p.setLocalPoint(targetPoint);
                            p.setStatus("Transporting");
                        }
                        break;
                    case 4://At destination
                    //todo leave time > 2s end
                    {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(AmbulanceCount.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    ambulance.setStatus(s[getStatusCode() + 1]);
                    ambulance.getPatient().setStatus("Completed");
                    break;
                    case 5://Returning
                        if (ambulance.getPatient() != null) {
                            ambulance.setPatient(null);
                        }
                        //
                        if (ambulance.getStationPoint() != null) {
                            Point stationPoint = ambulance.getStationPoint();
                            targetPoint = SimulatioinUtil.getMovedPoint(
                                    ambulance.getLocalPoint(),
                                    stationPoint,
                                    3);
                            targetPoint.setColor(Ambulance.AMBULANCE_DEFAULT_COLOR);
                            if (SimulatioinUtil.isSamePoint(ambulance.getLocalPoint(), stationPoint)) {
                                //arrivd station
                                ambulance.setStatus(s[0]);
                                ambulance.setStationPoint(null);
                            } else {
                                ambulance.setLocalPoint(targetPoint);
                            }
                        }

                        break;

                }
                jframe.updateAmbulance(ambulance);
                if (ambulance.getPatient() != null) {
                    jframe.updatePatient(ambulance.getPatient());
                }
                System.out.println(tname + code + ambulance.getId() + "--" + ambulance.getStatus());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AmbulanceCount.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

}
