package com.hive.capstone.scripts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.*;
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;



public class CallScripts {
    // included for testing purposes
    public static void main(String[] args) {
        getOrgs(0);
    }

    public static JSONArray getOrgs(int searchID, String... args) {
        try {
        String argString = "";
        if (args.length > 0) {
            for (int i = 0; i < args.length - 2; i++) {
                argString += args[i] + "; ";
            }
            argString += args[args.length - 1];
        }
        ProcessBuilder pyProc = new ProcessBuilder(
            "python", 
            "src/main/java/com/hive/capstone/scripts/PledgeAPI.py", 
            String.valueOf(searchID),
            argString
        ); //in testing, add "capstone/" to the beginning of the URL string

        pyProc.redirectErrorStream(true);

        Process process = pyProc.start();
        BufferedReader resultReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
        String r = resultReader.readLine();
        while (resultReader.ready()) {
                r += resultReader.readLine();
        }
        
        JSONArray results = new JSONArray(r);
        
        int exitCode = process.waitFor();
        System.out.println("Exited with code " + exitCode);
        resultReader.close();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray("");
        }
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




