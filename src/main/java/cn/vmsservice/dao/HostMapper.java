package cn.vmsservice.dao;

import org.springframework.transaction.annotation.Transactional;

import cn.vmsservice.bo.Host;

@Transactional
public interface HostMapper {
    int deleteByPrimaryKey(String id);

    int insert(Host record);

    int insertSelective(Host record);

    Host selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Host record);

    int updateByPrimaryKey(Host record);
}