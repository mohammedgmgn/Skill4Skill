package sol.yackeen.skill4skill.backend.availabletimeapi;

/**
 * Created by gmgn on 10/16/2016.
 */

public class AvailableTime {
    private int DayId;
    private int TimeFrom;
    private int TimeTo;
   private String daystring;
    private String fromstring;
    private String tostring;

    public String getDaystring() {
        return daystring;
    }

    public void setFromstring(String fromstring) {
        this.fromstring = fromstring;
    }

    public void setDaystring(String daystring) {
        this.daystring = daystring;
    }

    public void setTostring(String tostring) {
        this.tostring = tostring;
    }

    public int getDayId() {
        return DayId;
    }

    public void setDayId(int dayId) {
        DayId = dayId;
    }

    public int getTimeFrom() {
        return TimeFrom;
    }

    public void setTimeFrom(int timeFrom) {
        TimeFrom = timeFrom;
    }

    public int getTimeTo() {
        return TimeTo;
    }

    public void setTimeTo(int timeTo) {
        TimeTo = timeTo;
    }
    public String getFromstring() {
return fromstring;
    }
    public String getTostring() {
return tostring;
    }


}
