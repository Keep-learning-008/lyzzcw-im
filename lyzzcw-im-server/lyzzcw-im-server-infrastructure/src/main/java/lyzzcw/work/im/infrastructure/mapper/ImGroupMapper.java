package lyzzcw.work.im.infrastructure.mapper;

import lyzzcw.work.im.infrastructure.entity.ImGroup;

/**
* Created by Mybatis Generator on 2023/12/08
*/
public interface ImGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImGroup record);

    int insertSelective(ImGroup record);

    ImGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImGroup record);

    int updateByPrimaryKey(ImGroup record);
}