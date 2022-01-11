package com.projekt.pluk.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, PLUK, ECILOP,SHT,PG,FAB;

    @Override
    public String getAuthority() {
        return name();
    }
}