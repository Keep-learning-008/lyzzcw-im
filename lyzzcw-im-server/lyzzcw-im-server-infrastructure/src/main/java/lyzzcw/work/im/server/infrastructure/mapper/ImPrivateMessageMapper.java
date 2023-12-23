package lyzzcw.work.im.server.infrastructure.mapper;

import lyzzcw.work.im.server.infrastructure.entity.ImPrivateMessage;

/**
* Created by Mybatis Generator on 2023/12/08
*/
public interface ImPrivateMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImPrivateMessage record);

    int insertSelective(ImPrivateMessage record);

    ImPrivateMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImPrivateMessage record);

    int updateByPrimaryKeyWithBLOBs(ImPrivateMessage record);

    int updateByPrimaryKey(ImPrivateMessage record);
}