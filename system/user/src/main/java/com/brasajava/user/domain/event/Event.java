package com.brasajava.user.domain.event;

import com.brasajava.user.domain.entity.Entity;
import com.brasajava.user.domain.entity.User;

public interface Event <T extends Entity> {
    T getEntity();
}
