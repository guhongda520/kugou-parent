package cn.com.kugou.domain.mapper;

import cn.com.kugou.domain.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/4
 */
@Mapper
public interface UserMapper extends Mapper{

    @Select("SELECT * FROM T_USER")
    User getUser();
    @Delete("delete from T_USER")
    Integer deleteUser();
    @Delete("delete from T_USER")
    Integer deleteUser2();
}
