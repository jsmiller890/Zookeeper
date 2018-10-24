/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zookeepermonitorsystem;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author cerul
 */
public class MonitorSystem {
    public static void RunMonitor(){
        
    String category = "none";
        Scanner scnr = new Scanner(System.in);
        
        while (!category.equals("exit")) {
            System.out.println("Do you want to monitor an Animal, Habitat or Exit?");
            category = scnr.next();
            category = category.toLowerCase();
            if (category.equals("animal")) {
                try {
                    ReportingSystem.ReadFile(category);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (category.equals("habitat")) {
                try {
                    ReportingSystem.ReadFile(category);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (category.equals("exit")) {
                continue;
            }
            else {
                System.out.println("Incorrect Input");
                continue;
            }
        }
        System.out.println("Closing Program...");
        System.out.println("Goodbye");
    }
}
