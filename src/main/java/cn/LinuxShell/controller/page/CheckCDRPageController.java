package cn.LinuxShell.controller.page;

import cn.redis.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CheckCDRPageController {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    @RequestMapping(value = "/checkCDR.do")
    public ModelAndView checkCDR() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("linux/checkCDR");
        return mv;
    }
}
