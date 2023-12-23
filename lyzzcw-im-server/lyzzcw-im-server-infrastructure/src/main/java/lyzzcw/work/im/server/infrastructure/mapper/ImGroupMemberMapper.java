package lyzzcw.work.im.server.infrastructure.mapper;

import lyzzcw.work.im.server.infrastructure.entity.ImGroupMember;

/**
* Created by Mybatis Generator on 2023/12/08
*/
public interface ImGroupMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImGroupMember record);

    int insertSelective(ImGroupMember record);

    ImGroupMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImGroupMember record);

    int updateByPrimaryKey(ImGroupMember record);
}