package jsonexer;

import java.util.Date;

public class Attendance {
    private boolean attended;
    private Date dayDate;

    public Attendance(Date dayDate, boolean attended){
        this.attended = attended;
        this.dayDate = dayDate;
    }

    public Attendance(){
        this(null,false);
    }

    public void setAttended(boolean attended){
        this.attended = attended;
    }

    public boolean getAttended(){
        return attended;
    }

    public void setDayDate(Date dayDate){
        this.dayDate = dayDate;
    }

    public Date getDayDate(){
        return dayDate;
    }

    @Override
    public String toString() {
        return "[" + this.dayDate + "-"  + this.attended +"]";
    }
}
