package com.muyuan.system.infrastructure.config.mybatis;

import com.muyuan.common.mybatis.id.IdGenerator;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.common.mybatis.jdbc.crud.SqlHelper;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;
import com.muyuan.common.mybatis.jdbc.mybatis.JdbcBaseMapper;
import com.muyuan.common.mybatis.util.StatementUtil;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@DataSource(SystemJdbcConfig.DATASOURCE_NAME)
public interface SystemBaseMapper<T> extends JdbcBaseMapper<T> {

    @SelectProvider(value = CrudSqlProvider.class,method = "selectOne")
    T selectOne(Map params);

    @SelectProvider(value = CrudSqlProvider.class,method = "selectList")
    List<T> selectList(Map params);

    @IdGenerator()
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @InsertProvider(value = CrudSqlProvider.class,method = "insert")
    Integer insert(T dataObject);

    /**
     * 默认根据 id 更新
     * @param entity
     * @return
     */
    @UpdateProvider(value = CrudSqlProvider.class,method = "updateById")
    Integer updateById(T entity);

    /**
     * 更加指定字段更新
     * @param entity
     * @param column
     * @return
     */
    @UpdateProvider(value = CrudSqlProvider.class,method = "updateBy")
    Integer updateBy(T entity,String... column);

    /**
     * 根据ID删除记录
     * @param ids
     * @return
     */
    @DeleteProvider(value = CrudSqlProvider.class,method = "deleteByIds")
    Integer deleteByIds(String... ids);


    @Transactional(rollbackFor = Exception.class)
    @IdGenerator
    default int batchInsert(List<T> list,Class mapper) {
        return batchInsert(list,mapper,DEFAULT_BATCH_SIZE);
    }

    @Transactional(rollbackFor = Exception.class)
    @IdGenerator
    default int batchInsert(List<T> list,Class mapper,int batchSize)  {
        if (list.isEmpty()) {
            return 0;
        }
        String mapperInterFaceName = StatementUtil.getMapperName(this);
        SqlHelper.executeBatch(list,batchSize,((sqlSession, entity) -> {
            sqlSession.insert( mapperInterFaceName+".insert",entity);
        }));

        return 0;
    }

    default int batchUpdate() {
        return 0;
    }
}
