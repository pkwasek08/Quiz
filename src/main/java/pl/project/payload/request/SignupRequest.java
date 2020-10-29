package pl.project.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignupRequest {
    @JsonProperty
    private String name;
    @JsonProperty
    private String login;
    @JsonProperty
    private String lastname;
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;
    @JsonProperty
    private String degree;
    @JsonProperty
    private String department;
    @JsonProperty
    private String major;
    @JsonProperty
    private Integer groupId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public SignupRequest(String name, String login, String lastname, String email, String password, String degree, String department, String major, Integer groupId) {
        this.name = name;
        this.login = login;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.degree = degree;
        this.department = department;
        this.major = major;
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
