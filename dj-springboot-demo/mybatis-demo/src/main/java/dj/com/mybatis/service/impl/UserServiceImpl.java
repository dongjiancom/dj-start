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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
    PlatformTransactionManager transactionManager;

    @Resource
    UserDao userDao;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public String selectAllAndDeal() {
        return "over";
    }

    /**
     * 编程式事务：https://blog.csdn.net/weixin_45433031/article/details/134575933
     * 为什么使用编程式事务：https://www.yuque.com/hollis666/hgtuok/sscz8razzyxltzhe
     */
    public boolean testTransaction(){
        TransactionStatus status;
        // 手动开启事务初始化
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            //操作

            // 操作无异常：提交事务
            transactionManager.commit(status);
            log.debug("操作xxxx成功");
            return true;
        } catch (Exception e) {
            log.debug("操作xxxx成功出错,正在回滚,错误信息为:"+e.getMessage());
            // 捕获异常, 事务回滚
            transactionManager.rollback(status);
            log.debug("操作xxxx已回滚");
            return false;
        }
    }

}
