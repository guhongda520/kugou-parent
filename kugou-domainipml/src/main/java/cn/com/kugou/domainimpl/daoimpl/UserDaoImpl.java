package cn.com.kugou.domainimpl.daoimpl;

import cn.com.kugou.domain.dao.UserDao;
import cn.com.kugou.domain.entity.User;
import cn.com.kugou.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/4
 */
@Repository
public class UserDaoImpl implements UserDao{
    @Autowired(required = false)
    private UserMapper mapper;

    @Override
    public User getUser() {
        return mapper.getUser();
    }
}
