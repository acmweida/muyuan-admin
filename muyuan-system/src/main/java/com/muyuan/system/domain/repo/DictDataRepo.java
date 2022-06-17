package com.muyuan.system.domain.repo;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.interfaces.dto.DictDataDTO;

import java.util.List;

/**
 * 字典数据REPO
 */
public interface DictDataRepo {

    List<DictData> select(DictDataDTO dictDataDTO);

    List<DictData> select(DictDataDTO dictDataDTO, Page page);

    List<DictData> selectByDataType(String dataType);

    DictData selectOne(DictData dictDataDTO);

    void insert(DictData dictData);

    void update(DictData dictData);

    void delete(String... ids);

    void refreshCache(String dataDictType);
}
