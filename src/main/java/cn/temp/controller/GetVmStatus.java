package cn.temp.controller;

import cn.temp.GetVMMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Set;

@Controller
public class GetVmStatus {

    @Resource
    private GetVMMsg getVMMsg;

    @RequestMapping(value = "/getVmStatus", method = RequestMethod.GET)
    @ResponseBody
    public void selectKeybyVague(@RequestParam(value = "readxlsFileString")String readxlsFileString,
                                 @RequestParam(value = "writetxtFileString")String writetxtFileString) {
        getVMMsg.getHost(readxlsFileString, writetxtFileString);
    }
}
