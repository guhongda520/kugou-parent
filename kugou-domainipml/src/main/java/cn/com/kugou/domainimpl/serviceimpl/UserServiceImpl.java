package cn.com.kugou.domainimpl.serviceimpl;

import cn.com.kugou.domain.dao.UserDao;
import cn.com.kugou.domain.entity.User;
import cn.com.kugou.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/4
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(value = "user", key = "'user'")
    public User getUser() {
        return userDao.getUser();
    }
}
