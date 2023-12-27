package lyzzcw.work.im.platform.infrastructure.mapper;

import lyzzcw.work.im.platform.domain.entity.ImUser;

/**
* Created by Mybatis Generator on 2023/12/27
*/
public interface ImUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImUser record);

    int insertSelective(ImUser record);

    ImUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImUser record);

    int updateByPrimaryKey(ImUser record);
}