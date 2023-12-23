package lyzzcw.work.im.server.infrastructure.mapper;

import lyzzcw.work.im.server.infrastructure.entity.ImUser;

/**
* Created by Mybatis Generator on 2023/12/08
*/
public interface ImUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImUser record);

    int insertSelective(ImUser record);

    ImUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImUser record);

    int updateByPrimaryKey(ImUser record);
}