package dj.com.mybatis.service.impl;

import dj.com.mybatis.entity.User;
import dj.com.mybatis.mapper.UserDao;
import dj.com.mybatis.service.IUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiandong 2024-06-05 create
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserDao userDao;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public String selectAllAndDeal() {
        return "over";
    }
}
