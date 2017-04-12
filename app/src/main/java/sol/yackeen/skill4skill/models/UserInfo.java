package sol.yackeen.skill4skill.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gmgn on 10/18/2016.
 */

public class UserInfo implements Parcelable {
    private int userid;
    private String name;
    private String password;
    private int KnowesAbout;
    private int WantToKnow;
    private int History;
    private String About;
    private String Avatar;
    private int Rate;

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public int getKnowesAbout() {
        return KnowesAbout;
    }

    public void setKnowesAbout(int knowesAbout) {
        KnowesAbout = knowesAbout;
    }

    public int getWantToKnow() {
        return WantToKnow;
    }

    public void setWantToKnow(int wantToKnow) {
        WantToKnow = wantToKnow;
    }

    public int getHistory() {
        return History;
    }

    public void setHistory(int history) {
        History = history;
    }

    public Boolean getFollowing() {
        return following;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }

    private Boolean following;


    public List<sol.yackeen.skill4skill.backend.availabletimeapi.AvailableTime> getAvailableTime() {
        return AvailableTimelist;
    }

    public void setAvailableTime(List<sol.yackeen.skill4skill.backend.availabletimeapi.AvailableTime> availableTime) {
        AvailableTimelist = availableTime;
    }

    public static Creator<UserInfo> getCREATOR() {
        return CREATOR;
    }

    public List<sol.yackeen.skill4skill.backend.availabletimeapi.AvailableTime> AvailableTimelist=new ArrayList<>();


    public UserInfo() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userid);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(About);
        parcel.writeString(Avatar);
        parcel.writeInt(Rate);


//        parcel.writeByte((byte) (following ? 1 : 0));       //parcel.writeList(AvailableTimelist);
    }
    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public UserInfo(Parcel in) {
        userid = in.readInt();

        name = in.readString();
        About = in.readString();
        Avatar = in.readString();
        Rate=in.readInt();
        password = in.readString();
        email = in.readString();
        in.readList(AvailableTimelist,null);

     //   following = in.readByte() != 0;     //myBoolean == true if byte != 0


    }

}
