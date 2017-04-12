package sol.yackeen.skill4skill.models;

/**
 * Created by gmgn on 8/3/2016.
 */
public class User  {

  private String Name;
  private String Email;
  private String Password;
   private String About;
    private String Avatar;
    private float Rate;

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

    public float getRate() {
        return Rate;
    }

    public void setRate(float rate) {
        Rate = rate;
    }

    // @Expose(serialize = false,deserialize = false)
   private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User() {

    }


/*
    public void setAVTIMELIST(List<availabletimeclass> AVTIMELIST) {
        this.AVTIMELIST = AVTIMELIST;
    }

    private List<availabletimeclass> AVTIMELIST = new ArrayList<>();

    public List<WhatIknow> getWIKLIST() {
        return WIKLIST;
    }

    public void setWIKLIST(List<WhatIknow> WIKLIST) {
        this.WIKLIST = WIKLIST;
    }

    private List<WhatIknow> WIKLIST = new ArrayList<>();

    private String Name;
    private String Email;

    public List<availabletimeclass> getAVTIMELIST() {
        return AVTIMELIST;
    }

    private String imageurl;
    private String Password;

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    private String accesstoken;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public User() {

    }


    private String Fbid;

    public String getFbid() {
        return Fbid;
    }

    public void setFbid(String fbid) {
        Fbid = fbid;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
*/


}
