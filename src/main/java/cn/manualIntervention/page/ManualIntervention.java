package cn.manualIntervention.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManualIntervention {
	@RequestMapping(value = "/repairAndJumppage.do")
    public ModelAndView value() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manHandle/repairAndJump");
        return mv;
    }
}
