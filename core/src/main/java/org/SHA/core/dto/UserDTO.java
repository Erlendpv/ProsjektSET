package org.SHA.core.dto;

import java.util.List;

public class UserDTO {
    private String userId;
    private String username;
    private String email;
    private List<String> deviceIds;

    public UserDTO(String userId, String username, String email, List<String> deviceIds) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.deviceIds = deviceIds;
    }

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