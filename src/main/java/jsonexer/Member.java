package jsonexer;

import java.util.ArrayList;

public class Member {
    private String id;
    private String name;
    private ArrayList<Attendance> attendance;

    public Member(String id, String name){
        this.attendance = new ArrayList<>();
        this.id = id;
        this.name = name;
    }

    public Member(){
        this("","");
        attendance = new ArrayList<>();
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAttendance(ArrayList<Attendance> attendance){
        this.attendance = attendance;
    }

    public void addAttendance(Attendance attendance){
        this.attendance.add(attendance);
    }

    public ArrayList<Attendance> getAttendance(){
        return attendance;
    }

}
