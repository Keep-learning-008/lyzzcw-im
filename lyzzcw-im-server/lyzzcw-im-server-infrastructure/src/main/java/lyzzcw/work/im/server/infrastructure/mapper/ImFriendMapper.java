package lyzzcw.work.im.server.infrastructure.mapper;

import lyzzcw.work.im.server.infrastructure.entity.ImFriend;

/**
* Created by Mybatis Generator on 2023/12/08
*/
public interface ImFriendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImFriend record);

    int insertSelective(ImFriend record);

    ImFriend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImFriend record);

    int updateByPrimaryKey(ImFriend record);
}