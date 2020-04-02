package jsonexer;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import org.json.JSONObject;

public class Main {
    private static Members members;

    public static void main(String[] args) throws ParseException {

        members = new Members(readJSON("./example-member-list.json"));
        attendanceMainMenuHandler();
        //System.out.println(members.toJSONString());
        
    }

    private static String printAttendanceMainMenu() {
        return "Attendance Registration App\n"
                +"Choose an option:\n"
                +"(1) To start taking attendance.\n"
                +"(2) Save and Exit.";
    }

    private static void attendanceMainMenuHandler() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (true){
            System.out.println(printAttendanceMainMenu());
            option = Integer.parseInt(scanner.next());
            switch (option) {
                case 1:
                    takeAttendance();
                    break;
                case 2:
                    saveToFile();
                    break;
                default:
                    System.out.println("Invalid Option: Please Try Again!");
            }
            if(option == 2) break;
        }
    }

    private static void takeAttendance(){
        Scanner scanner = new Scanner(System.in);
        for(String key: members.getMembersID()){
            System.out.println(members.getMemberName(key) + " is attended today? ( Y: Yes , Others: No )" );
            String answer = scanner.next().trim();
            boolean attended = answer.equals("Y") || answer.equals("y") ? true : false;
            members.addAttended(key,attended,new Date());
        }
    }

    private static void saveToFile(){
        writeJSON("./example-member-list.json", members.toJSONString());
    }
    
    private static JSONObject readJSON(String filePathName) {
        File file = new File(filePathName);
        String content = null;
        try {
            content = FileUtils.readFileToString(file, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert JSON string to JSONObject
        return new JSONObject(content);
    }

    private static void writeJSON(String filePathName, String content) {
        File file = new File(filePathName);
        try {
            FileUtils.writeStringToFile(file, content,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
