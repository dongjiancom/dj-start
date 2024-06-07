package dj.com.mybatisplus.generate.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dj.com.mybatisplus.generate.entity.User;
import dj.com.mybatisplus.generate.mapper.UserMapper;
import dj.com.mybatisplus.generate.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiandong 2024-06-05 create
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Resource
    UserMapper userMapper;

    @Override
    public void streamQuery(){
        // 结合分页，按批次从数据库拉取数据出来跑批，例如从数据库获取10万记录，做数据处理
        Page<User> page = new Page<>(1, 2);
        userMapper.selectList(page, Wrappers.emptyWrapper(), new ResultHandler<User>() {
            int count = 0;
            @Override
            public void handleResult(ResultContext<? extends User> resultContext) {
                User h2User = resultContext.getResultObject();
                System.out.println("当前处理第" + (++count) + "条记录: " + h2User);
                // 在这里进行你的业务处理，比如分发任务
            }
        });

        // 从数据库获取表所有记录，做数据处理
        userMapper.selectList(Wrappers.emptyWrapper(), new ResultHandler<User>() {
            int count = 0;
            @Override
            public void handleResult(ResultContext<? extends User> resultContext) {
                User h2User = resultContext.getResultObject();
                System.out.println("当前处理第" + (++count) + "条记录: " + h2User);
                // 在这里进行你的业务处理，比如分发任务
            }
        });
    }
}
