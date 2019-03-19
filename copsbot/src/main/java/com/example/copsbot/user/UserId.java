package com.example.copsbot.user;

import com.example.copsbot.orm.AbstractEntityId;

import java.util.UUID;

public class UserId extends AbstractEntityId<UUID> {

    protected UserId() {
    }

    public UserId(UUID id) {
        super(id);
    }
}
