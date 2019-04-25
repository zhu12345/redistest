package cn.temp.controller;

import cn.temp.SyncResouceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class SyncDomainHandler {
    @Resource
    private SyncResouceService syncResouceService;

    @RequestMapping(value = "/syncDomain", method = RequestMethod.GET)
    @ResponseBody
    public void syncDomain(@RequestParam(value = "readxlsFileString")String readxlsFileString) {
        syncResouceService.getHost(readxlsFileString);
    }
}
