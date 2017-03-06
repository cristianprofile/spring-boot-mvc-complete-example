package com.mylab.cromero.repository.dto;

/**
 * <h1>BaseRequest</h1>
 * BaseRequest dto request
 * <p>
 * <b>BaseRequest</b> BaseRequest data transfer object
 * for sevice layer
 *
 * @author Cristian Romero Matesanz
 */
public class UserRequest {

    private String user;

    private String password;

    private String name;

    private String surname;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    private String rol;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        String builder = "UserRequest [user=" +
                user +
                ", name=" +
                name +
                ", surname=" +
                surname +
                ", accountNonExpired=" +
                accountNonExpired +
                ", accountNonLocked=" +
                accountNonLocked +
                ", credentialsNonExpired=" +
                credentialsNonExpired +
                ", enabled=" +
                enabled +
                "]";
        return builder;
    }


}
