package com.hive.capstone.scripts;
import javax.script.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// haven't tested this guy yet, but hopefully should work. theoretically at least
// if running the file causes errors, check file directory string passed to gMaps
public class CallScripts {
    public static void main(String[] args) throws Exception{
        //Initializing engine(s) to run script languages
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine jsEngine = manager.getEngineByName("JavaScript");

        //Reading scripts
        FileInputStream gMaps = new FileInputStream("./GMapsAPI");
        System.out.println("gmaps acquired");
        BufferedReader gMapsReader = new BufferedReader(new InputStreamReader(gMaps));

        //Evaluate and invoke method (run script)
        jsEngine.eval(gMapsReader);
        Invocable mapsInvoke = (Invocable)jsEngine;

        //Print results
        System.out.println("MapsAPI called. Results:\n" + mapsInvoke);
    }

}

