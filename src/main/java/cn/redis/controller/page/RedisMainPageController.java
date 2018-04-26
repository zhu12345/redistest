package cn.redis.controller.page;

import cn.redis.service.RedisService;
import cn.redis.model.redisreturn.RedisReturnValue;
import cn.redis.utils.RedisUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.DataType;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
public class RedisMainPageController {
	private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    @Resource
    private RedisService redisService;

    /*@RequestMapping(value = "/redismain.do")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redismain");
        mv.addObject("msg", "hello!ni hao!");
        return mv;
    }*/

    @RequestMapping(value = "/redisvalue.do")
    public ModelAndView value(@RequestParam(value = "index")int index,
                              @RequestParam(value = "key")String key) {
        ModelAndView mv = new ModelAndView();
        RedisReturnValue redisReturnValue;
        try {
            redisReturnValue = redisService.getValueByKey(index, key);
            if (redisReturnValue.getDateType().equals(DataType.STRING)) {
                mv.setViewName("STRINGVALUE");
            } else if (redisReturnValue.getDateType().equals(DataType.LIST)) {
                mv.setViewName("LISTVALUE");
            } else if (redisReturnValue.getDateType().equals(DataType.SET) ||
                    redisReturnValue.getDateType().equals(DataType.ZSET)) {
                mv.setViewName("SETVALUE");
            } else if (redisReturnValue.getDateType().equals(DataType.HASH)) {
                mv.setViewName("HASHVALUE");
            } else {
                mv.setViewName("NONE");
            }
            mv.addObject("redisReturnValue", redisReturnValue);
            mv.addObject("key", key);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return mv;
    }

   /* @RequestMapping(value = "/STRINGVALUE.do")
    public ModelAndView stringValue() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("STRINGVALUE");
        return mv;
    }

    @RequestMapping(value = "/LISTVALUE.do")
    public ModelAndView listValue() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("LISTVALUE");
        return mv;
    }

    @RequestMapping(value = "/SETVALUE.do")
    public ModelAndView setValue() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("SETVALUE");
        return mv;
    }

    @RequestMapping(value = "/HASHVALUE.do")
    public ModelAndView hashValue() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("HASHVALUE");
        return mv;
    }*/
    
    /**
     * 
     * @return
     */
    @RequestMapping(value = "/search.do")
    public ModelAndView search() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("search");
        return mv;
    }
    @RequestMapping(value = "/test.do")
    public ModelAndView delete() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test/test");
        return mv;
    }
}
