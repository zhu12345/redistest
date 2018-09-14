package cn.hwsservice.dao;

import cn.hwsservice.bo.AccountMeta;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountMetaMapper {
    int deleteByPrimaryKey(String id);

    int insert(AccountMeta record);

    int insertSelective(AccountMeta record);

    AccountMeta selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccountMeta record);

    int updateByPrimaryKey(AccountMeta record);
}