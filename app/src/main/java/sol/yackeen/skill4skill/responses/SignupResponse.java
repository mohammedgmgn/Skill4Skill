package sol.yackeen.skill4skill.responses;

/**
 * Created by gmgn on 10/18/2016.
 */

public class SignupResponse {
    private int UserId;
    private int StatusCode;
    private String Message;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
