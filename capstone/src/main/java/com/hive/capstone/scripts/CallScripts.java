package com.hive.capstone.scripts;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class CallScripts {
    public static void main(String[] args) throws Exception{
        callPy(36.098104, -79.784872, 91);
    }

    //straight-up DOES work O.o
    public static void callPy(double lat, double lon, int causeID) throws Exception {
        ProcessBuilder pyProc = new ProcessBuilder(
            "python", 
            "capstone/src/main/java/com/hive/capstone/scripts/PledgeAPI.py", 
            String.valueOf(lat),
            String.valueOf(lon),
            String.valueOf(causeID)
        );

        pyProc.redirectErrorStream(true);

        Process process = pyProc.start();
        BufferedReader resultReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
        System.out.println(resultReader.readLine());
        while (resultReader.ready()) {
            System.out.println(resultReader.readLine());
        }
        int exitCode = process.waitFor();
        System.out.println("Exited with code " + exitCode);
        resultReader.close();
    }

}




