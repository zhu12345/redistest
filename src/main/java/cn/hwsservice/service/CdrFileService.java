package cn.hwsservice.service;

import java.util.Date;

public interface CdrFileService {
    void saveCdrFile(String zipFileName, Double size, Date date, String resourceTypes);
}
