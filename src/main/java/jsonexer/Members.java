package jsonexer;

import org.json.JSONArray;
import org.json.JSONObject;
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
            String id = (String) membersJSONArray.getJSONObject(i).get("id");
            String name = (String) membersJSONArray.getJSONObject(i).get("name");
            members.put(id,new Member(id,name));
        }
    }

    public String toJSONString(){
        JSONObject membersJSONObject = new JSONObject();
        JSONArray membersJSONArray = new JSONArray(new ArrayList<Member>(members.values()));
        membersJSONObject.put("members",membersJSONArray);
        return membersJSONObject.toString();
    }
}
