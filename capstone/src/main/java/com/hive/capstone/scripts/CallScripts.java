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
        getOrgs("page=5");
    }

    public static JSONArray getOrgs(String args) {
        try {
            ProcessBuilder pyProc = new ProcessBuilder(
                "python", 
                "src/main/java/com/hive/capstone/scripts/PledgeAPI.py", //use when server is running
                // "capstone/src/main/java/com/hive/capstone/scripts/PledgeAPI.py", //use for testing this file
                args
            );

            pyProc.redirectErrorStream(true);

            Process process = pyProc.start();
            BufferedReader resultReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            // read results into a string
            String r = resultReader.readLine();
            while (resultReader.ready()) {
                    r += resultReader.readLine();
            }
            System.out.println(r);

            // create a JSONArray of the results
            JSONArray results;
            if (r!=null) {
                results = new JSONArray(r);
            } else {
                results = new JSONArray("[]");
            }
            
            // print exit code and return results
            int exitCode = process.waitFor();
            System.out.println("Exited with code " + exitCode);
            resultReader.close();
            System.out.println("CallScripts finished");
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray("[]");
        }
    }

    public static JSONObject getOrgDetails(String id) {
        try {
            ProcessBuilder pyProc = new ProcessBuilder(
                "python", 
                "src/main/java/com/hive/capstone/scripts/PledgeAPI.py", //use when server is running
                // "capstone/src/main/java/com/hive/capstone/scripts/PledgeAPI.py", //use for testing this file
                id
            );

            pyProc.redirectErrorStream(true);

            Process process = pyProc.start();
            BufferedReader resultReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            // read results into a string
            String r = resultReader.readLine();
            while (resultReader.ready()) {
                    r += resultReader.readLine();
            }
            System.out.println(r);
            

            // turn results into JSONObject
            JSONObject results;
            if (r!=null) {
                System.out.println("r is not null");
                
                results = new JSONObject(r);
            } else {
                results = new JSONObject("{}");
            }
            
            // print exit code and return results
            int exitCode = process.waitFor();
            System.out.println("Exited with code " + exitCode);
            resultReader.close();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject("{}");
        }
    }
}