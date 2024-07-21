
package dj.com.mybatis.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * @author jiandong 2023-09-20 create
 */
@MappedJdbcTypes({JdbcType.VARCHAR})
@MappedTypes({CardholderInfo.class})
public class AssetCardholderInfoHandler extends AbstractObjectTypeHandler<CardholderInfo>{
}
