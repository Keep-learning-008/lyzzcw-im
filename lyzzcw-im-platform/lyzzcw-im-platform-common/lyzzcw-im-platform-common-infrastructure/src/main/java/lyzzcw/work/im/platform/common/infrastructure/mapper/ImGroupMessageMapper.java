package lyzzcw.work.im.platform.common.infrastructure.mapper;

import lyzzcw.work.im.platform.common.domain.entity.ImGroupMessage;

/**
* Created by Mybatis Generator on 2023/12/29
*/
public interface ImGroupMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImGroupMessage record);

    int insertSelective(ImGroupMessage record);

    ImGroupMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImGroupMessage record);

    int updateByPrimaryKeyWithBLOBs(ImGroupMessage record);

    int updateByPrimaryKey(ImGroupMessage record);
}