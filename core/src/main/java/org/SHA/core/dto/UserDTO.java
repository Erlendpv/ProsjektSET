package org.SHA.core.dto;

import java.util.List;

//Bruker data og enhets-IDer som skal kommuniseres
public class UserDTO {
    private String userId;
    private String username;
    private String email;
    private List<String> deviceIds;

    //For å lage userDTO-instans
    public UserDTO(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.deviceIds = deviceIds;
    }

    //For å kunne endre og oppdatere data i userDTO
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }
}