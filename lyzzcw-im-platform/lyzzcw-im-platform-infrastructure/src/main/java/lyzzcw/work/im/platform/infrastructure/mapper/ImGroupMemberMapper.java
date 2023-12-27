package lyzzcw.work.im.platform.infrastructure.mapper;

import lyzzcw.work.im.platform.domain.entity.ImGroupMember;

/**
* Created by Mybatis Generator on 2023/12/27
*/
public interface ImGroupMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImGroupMember record);

    int insertSelective(ImGroupMember record);

    ImGroupMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImGroupMember record);

    int updateByPrimaryKey(ImGroupMember record);
}