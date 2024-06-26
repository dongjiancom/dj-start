package dj.com.mybatis.controller;

import dj.com.mybatis.entity.User;
import dj.com.mybatis.mapper.UserDao;
import dj.com.mybatis.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by riger on 2021/1/24
 */
@RestController
public class UserController {

    // 这里为演示,直接调的dao,实际开发应当使用service

    @Autowired
    private UserDao userDao;

    @Resource
    IUserService userService;


    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userDao.selectByPrimaryKey(id);
    }
    @GetMapping("/user/selectAllAndDeal")
    public String selectAllAndDeal() {
        return userService.selectAllAndDeal();
    }


}
