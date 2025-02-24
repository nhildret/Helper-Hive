// package com.hive.capstone.scripts;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.nio.charset.StandardCharsets;
// import java.nio.file.Files;
// import java.nio.file.Paths;

// import javax.script.*;
// import org.graalvm.polyglot.*;
// import org.graalvm.polyglot.proxy.*;
// import java.util.*;
// import java.io.BufferedReader;
// import java.io.FileInputStream;
// import java.io.FileReader;
// import java.io.InputStreamReader;
// import java.nio.charset.StandardCharsets;

// import javax.script.Invocable;
// import javax.script.ScriptEngine;
// import javax.script.ScriptEngineManager;



// public class CallScripts {
//     public static void main(String[] args) throws Exception{
//         // 1 - search by coords
//         // 2 - search by query
//         //getOrgs(1, String.valueOf(36.098104), String.valueOf(-79.784872), String.valueOf(91)); //example coord search
//         //getOrgs(2, "Red Cross");
//         callJS();
//     }

//     public static String getCoords() {
//         try {
//             ScriptEngineManager manager = new ScriptEngineManager();
//             ScriptEngine engine = manager.getEngineByName("nashorn");
//             // read script file
//             engine.eval(Files.newBufferedReader(Paths.get(Paths.get("").toAbsolutePath() + "/src/main/resources/static/js/coords.js"), StandardCharsets.UTF_8));
            
//             Invocable invoke = (Invocable) engine;
//             // call function from script file
//             Object result = invoke.invokeFunction("getCoords");
//             System.out.println(result.toString());
//             return result.toString();
//         } catch(Exception e) {
//             return e.toString();
//         }
//     }

//     //can call regular client-side JS, but not server-side nodeJS
//     public static String callJS() {
//         String jsCode;
//         try {
//             //Reading scripts
//             FileInputStream stream = new FileInputStream(Paths.get("").toAbsolutePath() + "/src/main/resources/static/js/coords.js");
//             System.out.println("script acquired");
//             BufferedReader jsReader = new BufferedReader(new InputStreamReader(stream));
//             jsCode = jsReader.readLine();
//             while (jsReader.ready()) {
//                 jsCode += jsReader.readLine();
//             }
//             System.out.println(jsCode);
//             //Execute scripts
//         try(Context context = Context.create()) {
//             Value value = context.eval("js", jsCode);
//             Value results = value.execute();
//             System.out.println(results.asString());
//             jsReader.close();
//             return results.asString();
//         } catch (Exception e) {
//             jsReader.close();
//             System.out.println("Run exception: " + e.toString());
//             return e.toString();
//         }
        
//         } catch (Exception e) {
//             return "Read exception: " + e.toString();
//         }

        
//     }

//     public static String getOrgs(int searchID, String... args) {
//         try {
//             String argString = "";
//             for (int i = 0; i < args.length - 1; i++) {
//                 argString += args[i] + "; ";
//             }
//             argString += args[args.length - 1];
//             ProcessBuilder pyProc = new ProcessBuilder(
//                 "python", 
//                 "capstone/src/main/java/com/hive/capstone/scripts/PledgeAPI.py", 
//                 String.valueOf(searchID),
//                 argString
//             );

//             pyProc.redirectErrorStream(true);

//             Process process = pyProc.start();
//             BufferedReader resultReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
//             String results = resultReader.readLine();
//             while (resultReader.ready()) {
//                 results += resultReader.readLine();
//             }
//             int exitCode = process.waitFor();
//             System.out.println("Exited with code " + exitCode);
//             resultReader.close();
//             return results;
//         } catch (Exception e) {
//             return e.toString();
//         }
//     }

//     public static void getOrgByName(String name) throws Exception{
//         ProcessBuilder pyProc = new ProcessBuilder(
//             "python", 
//             "capstone/src/main/java/com/hive/capstone/scripts/PledgeAPI.py", 
//             name
//         );

//         pyProc.redirectErrorStream(true);

//         Process process = pyProc.start();
//         BufferedReader resultReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
//         System.out.println(resultReader.readLine());
//         while (resultReader.ready()) {
//             System.out.println(resultReader.readLine());
//         }
//         int exitCode = process.waitFor();
//         System.out.println("Exited with code " + exitCode);
//         resultReader.close();
//     }
// }




