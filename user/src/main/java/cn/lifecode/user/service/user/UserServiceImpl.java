package cn.lifecode.user.service.user;

import cn.lifecode.user.entity.User;
import cn.lifecode.user.mapper.user.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author luolin
 * @date 2021-01-14 18:44:29
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(User user) {
        user.setUpdateTime(new Date());
        userMapper.addUser(user);
    }
}
