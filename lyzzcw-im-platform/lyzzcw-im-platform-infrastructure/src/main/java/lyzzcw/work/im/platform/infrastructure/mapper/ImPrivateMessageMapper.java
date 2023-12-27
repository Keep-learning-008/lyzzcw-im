package lyzzcw.work.im.platform.infrastructure.mapper;

import lyzzcw.work.im.platform.domain.entity.ImPrivateMessage;

/**
* Created by Mybatis Generator on 2023/12/27
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