package sol.yackeen.skill4skill.backend.signinapi;

import com.google.gson.annotations.SerializedName;

import sol.yackeen.skill4skill.models.User;

/**
 * Created by gmgn on 10/16/2016.
 */

public class RegisterRequestBody {
    @SerializedName("User")
    public User User;
}
