package com.hive.capstone.scripts;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class CallScripts {
    public static void main(String[] args) throws Exception{
        // 1 - search by coords
        // 2 - search by query
        //getOrgs(1, String.valueOf(36.098104), String.valueOf(-79.784872), String.valueOf(91)); //example coord search
        //getOrgs(2, "Red Cross");
    }

    public static void getOrgs(int searchID, String... args) throws Exception {
        String argString = "";
        for (int i = 0; i < args.length - 1; i++) {
            argString += args[i] + "; ";
        }
        argString += args[args.length - 1];
        ProcessBuilder pyProc = new ProcessBuilder(
            "python", 
            "capstone/src/main/java/com/hive/capstone/scripts/PledgeAPI.py", 
            String.valueOf(searchID),
            argString
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

    public static void getOrgByName(String name) throws Exception{
        ProcessBuilder pyProc = new ProcessBuilder(
            "python", 
            "capstone/src/main/java/com/hive/capstone/scripts/PledgeAPI.py", 
            name
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




