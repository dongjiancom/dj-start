package dj.com.mybatis.service;

import dj.com.mybatis.entity.User;
import org.apache.ibatis.cursor.Cursor;

/**
 * @author jiandong 2024-06-05 create
 */
public interface IUserService {
    String selectAllAndDeal();
}
