package lyzzcw.work.im.platform.common.infrastructure.mapper;

import lyzzcw.work.im.platform.common.domain.entity.ImPrivateMessage;

/**
* Created by Mybatis Generator on 2023/12/29
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