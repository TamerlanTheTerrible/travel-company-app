package me.timur.travelcompanyapp.security.auth;

/**
 * Created by Temurbek Ismoilov on 28/01/22.
 */

public enum ApplicationUserPermission {
    TO_READ,
    TO_WRITE;

    public String getPermission() {
        return this.name();
    }
}
