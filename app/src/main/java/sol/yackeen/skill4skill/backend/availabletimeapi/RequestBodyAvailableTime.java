package sol.yackeen.skill4skill.backend.availabletimeapi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gmgn on 10/16/2016.
 */

public class RequestBodyAvailableTime {

    public int UserId;
    @SerializedName("AvailableTime")
    public List<AvailableTime>AvailableTime=new ArrayList<>();

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public List<sol.yackeen.skill4skill.backend.availabletimeapi.AvailableTime> getAvailableTime() {
        return AvailableTime;
    }

    public void setAvailableTime(List<sol.yackeen.skill4skill.backend.availabletimeapi.AvailableTime> availableTime) {
        AvailableTime = availableTime;
    }
}
