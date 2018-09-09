package cn.LinuxShell.controller;

import cn.LinuxShell.CheckCDRNoService;
import cn.util.string.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class CheckCDRController {

    @Resource
    private CheckCDRNoService checkCDRNoService;

    /**
     *
     * @param date 20180604
     * @param region cn-gz1
     * @return String
     */
    @RequestMapping(value = "/checkCDR", method = RequestMethod.GET)
    @ResponseBody
    public String checkByDateAndRegion(@RequestParam(value = "date")String date,
            @RequestParam(value = "region")String region) {
        if (StringUtils.pdNULL(date) && StringUtils.pdNULL(region))
            return null;
        StringBuffer stringBuffer = new StringBuffer();
        for (String s:
            region.split(",")) {
            stringBuffer.append(checkCDRNoService.checkByDateAndRegion(s, date).toString());
        }
        return stringBuffer.toString();
    }
}
