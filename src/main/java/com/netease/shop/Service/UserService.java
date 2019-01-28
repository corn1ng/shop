package com.netease.shop.Service;

import com.netease.shop.Entity.User;

public interface UserService {

    User selectByNameAndPassword(User user);
}
