package cn.hwsservice.service.impl;

import cn.hwsservice.bo.CdrFileInfo;
import cn.hwsservice.dao.CdrFileInfoMapper;
import cn.hwsservice.service.CdrFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CdrFileServiceImpl implements CdrFileService {

    @Resource
    private CdrFileInfoMapper cdrFileInfoMapper;

    @Override
    public void saveCdrFile(String zipFileName, Double size, Date date, String resourceTypes) {
        CdrFileInfo cdrFileInfo = new CdrFileInfo();
        cdrFileInfo.setZipFilename(zipFileName);
        cdrFileInfo.setFileSize(size);
        cdrFileInfo.setAccessDate(date);
        cdrFileInfo.setResourceTypes(resourceTypes);
        cdrFileInfoMapper.insert(cdrFileInfo);
    }
}
