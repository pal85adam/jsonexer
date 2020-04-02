package jsonexer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class Members {
    private HashMap<String,Member> members;

    public Members(JSONObject membersJSONObject){
        membersImporter(membersJSONObject);
    }

    public Set<String> getMembersID(){
        return members.keySet();
    }

    public String getMemberName(String key){
        return members.get(key).getName();
    }

    public void addAttended(String key,boolean attended,Date dayDate){
        members.get(key).addAttendance(new Attendance(dayDate,attended));
    }

    private void membersImporter(JSONObject membersJSONObject){
        JSONArray membersJSONArray = membersJSONObject.getJSONArray("members");
        members = new HashMap<>();
        for (int i = 0; i < membersJSONArray.length(); i++) {
            JSONObject thisMemberJObject = membersJSONArray.getJSONObject(i);
            String id = (String) thisMemberJObject.get("id");
            String name = (String) thisMemberJObject.get("name");
            members.put(id,new Member(id,name));
            if(!thisMemberJObject.isNull("attendance")){
                JSONArray thisMemberAttendanceJArray = thisMemberJObject.getJSONArray("attendance");
                for (int j=0; j < thisMemberAttendanceJArray.length(); j++){
                    JSONObject thisDayAttendance = thisMemberAttendanceJArray.getJSONObject(j);
                    try {
                        addAttended(id,
                                thisDayAttendance.getBoolean("attended"),
                                new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy")
                                        .parse((String) thisDayAttendance.get("dayDate")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public String toJSONString(){
        JSONObject membersJSONObject = new JSONObject();
        JSONArray membersJSONArray = new JSONArray(new ArrayList<Member>(members.values()));
        membersJSONObject.put("members",membersJSONArray);
        return membersJSONObject.toString();
    }
}
