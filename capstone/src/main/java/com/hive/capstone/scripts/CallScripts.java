package com.hive.capstone.scripts;

import javax.script.*;
//import org.graalvm.polyglot.*;
//import org.graalvm.polyglot.proxy.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class CallScripts {
    public static void main(String[] args) throws Exception{
        callPy(36.098104, -79.784872, 91);
    }

    //straight-up doesn't work
    public static void callPy(double lat, double lon, int causeID) throws Exception {
        String args = "dict(lat = " + lat + ", lon = " + lon + ", cause_id = " + causeID + ")";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("python");
        InputStreamReader reader = new InputStreamReader(new FileInputStream("capstone/src/main/java/com/hive/capstone/scripts/PledgeAPI.py"), StandardCharsets.UTF_8);

        engine.eval(reader);
    }

    //can call regular client-side JS, but not server-side nodeJS
    /*public static void callJS() throws Exception {
        //Reading scripts
        FileInputStream stream = new FileInputStream("capstone/src/main/java/com/hive/capstone/scripts/GMapsAPI.js");
        System.out.println("script acquired");
        BufferedReader jsReader = new BufferedReader(new InputStreamReader(stream));
        String jsCode = jsReader.readLine();
        while (jsReader.ready()) {
            jsCode += jsReader.readLine();
        }
        System.out.println(jsCode);

        //Execute scripts
        try(Context context = Context.create()) {
            Value value = context.eval("js", jsCode);
            value.execute();
        }
        jsReader.close();
    }*/

}




