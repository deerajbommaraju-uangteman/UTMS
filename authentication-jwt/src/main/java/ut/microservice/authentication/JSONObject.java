package ut.microservice.authentication;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONObject implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @JsonProperty(value = "id", required = true)
    private String userid;


    @JsonProperty(value = "password", required = true)
    private String password;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + userid + "\",\"password\":\"" + password + "\"}";
    }

}
