package lyzzcw.work.im.platform.common.infrastructure.mapper;

import lyzzcw.work.im.platform.common.domain.entity.ImFriend;

/**
* Created by Mybatis Generator on 2023/12/29
*/
public interface ImFriendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImFriend record);

    int insertSelective(ImFriend record);

    ImFriend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImFriend record);

    int updateByPrimaryKey(ImFriend record);
}