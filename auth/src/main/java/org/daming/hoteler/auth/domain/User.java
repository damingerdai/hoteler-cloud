package org.daming.hoteler.auth.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author gming001
 * @version 2022-12-04 20:08
 */
public class User {

    private int id;

    private String username;

    private String firstName;

    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String passwordType;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Role> roles;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Permission> permissions;

    private int failedLoginAttempts;

    private boolean accountNonLocked;

    private LocalDateTime lockTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public LocalDateTime getLockTime() {
        return lockTime;
    }

    public void setLockTime(LocalDateTime lockTime) {
        this.lockTime = lockTime;
    }

    public User(int id, String username, String firstName, String lastName, String password, String passwordType) {
        super();
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.passwordType = passwordType;
    }

    public User(int id, String username, String firstName, String lastName, String password, String passwordType, List<Role> roles) {
        super();
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.passwordType = passwordType;
        this.roles = roles;
    }

    public User(int id, String username, String firstName, String lastName, String password, String passwordType, List<Role> roles, List<Permission> permissions) {
        super();
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.passwordType = passwordType;
        this.roles = roles;
        this.permissions = permissions;
    }

    public User(LocalDateTime lockTime, boolean accountNonLocked, int failedLoginAttempts, List<Permission> permissions, List<Role> roles, String passwordType, String password, String lastName, String firstName, String username, int id) {
        super();
        this.lockTime = lockTime;
        this.accountNonLocked = accountNonLocked;
        this.failedLoginAttempts = failedLoginAttempts;
        this.permissions = permissions;
        this.roles = roles;
        this.passwordType = passwordType;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.id = id;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", passwordType='" + passwordType + '\'' +
                ", roles=" + roles +
                ", permissions=" + permissions +
                ", failedLoginAttempts=" + failedLoginAttempts +
                ", accountNonLocked=" + accountNonLocked +
                ", lockTime=" + lockTime +
                '}';
    }
}
