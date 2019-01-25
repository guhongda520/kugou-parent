package cn.com.kugou.domain.dao;

import cn.com.kugou.domain.entity.User;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/4
 */
public interface UserDao {
    User getUser();

    Integer deleteUser();

    Integer deleteUser2();
}
