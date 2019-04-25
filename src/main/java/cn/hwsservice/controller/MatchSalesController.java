package cn.hwsservice.controller;

import cn.hwsservice.service.MatchSales;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


//@Controller
public class MatchSalesController {

    @Resource
    private MatchSales matchSales;

//    @RequestMapping(value = "/hwsservice/matchSales", method = RequestMethod.GET )
//    @ResponseBody
    public int matchSales() {
        matchSales.matchSalesByHwProductCode();
        return 1;
    }
}
