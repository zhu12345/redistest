package cn.hwsservice.dao;

import cn.hwsservice.bo.CdrFileInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CdrFileInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CdrFileInfo record);

    int insertSelective(CdrFileInfo record);

    CdrFileInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CdrFileInfo record);

    int updateByPrimaryKeyWithBLOBs(CdrFileInfo record);

    int updateByPrimaryKey(CdrFileInfo record);
}