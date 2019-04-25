package cn.hwsservice.service.impl;

import cn.hwsservice.bo.ProductMapping;
import cn.hwsservice.dao.ProductMappingMapper;
import cn.hwsservice.service.MatchSales;
import cn.util.httpUtil.HttpRestImpl;
import cn.util.httpUtil.dao.HttpRest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchSalesImpl implements MatchSales {

    private static final Logger logger = LoggerFactory.getLogger(MatchSalesImpl.class);


    @Resource
    private ProductMappingMapper productMappingMapper;

    @Override
    public void matchSalesByHwProductCode() {
        String[] strings = new String[]{"rds.pg.s1.medium.ha",
                "rds.mssql.s1.xlarge.ha",
                "rds.mssql.c2.xlarge.ha",
                "rds.mssql.volume.common.ha",
                "rds.pg.volume.common.ha",
                "rds.pg.volume.common.rr",
                "rds.pg.s1.large.ha",
                "rds.pg.s1.medium.rr",
                "rds.pg.volume.ultrahigh.ha",
                "rds.mssql.s1.2xlarge.ha",
                "rds.pg.c2.medium.ha"};
        for (String s : strings) {
           List<ProductMapping> list =  productMappingMapper.getProductMappingByHWcode(s);
           for (ProductMapping productMapping : list) {
               HttpRest httpRest = new HttpRestImpl();
               Map<String, String> map = new HashMap<String, String>();
               map.put("serviceTag", "RDSHY");
               map.put("resourceType", "RDS_VM");
               JSONObject jsonObjectZoneType = JSON.parseObject(productMapping.getSalesAttribute());
//               if (!productMapping.getSalesAttribute().contains("zoneType")) {
//                   jsonObjectZoneType.put("zoneType", String.valueOf(productMapping.getZoneType()));
//               }
               map.put("jsonStr", jsonObjectZoneType.toJSONString());
               String s1 = httpRest.postParamAsMap("http://10.144.242.126/sales/GetSalesDetailByAttr", map, null, null);
              JSONObject jsonObject = JSON.parseObject(s1);
              if (jsonObject.getInteger("statusCode") == 800) {
                  JSONArray jsonArray = jsonObject.getJSONArray("returnObj");
                  int i = jsonArray.size();
                  if (i == 1) {
                     Object jsonObject1 = jsonArray.get(0);
                     JSONObject jsonObject2 = JSON.parseObject(JSON.toJSONString(jsonObject1));
                     String salesEntryId = jsonObject2.getString("salesEntryId");
                      productMapping.setSalesId(salesEntryId);
                      productMappingMapper.updateByPrimaryKeySelective(productMapping);
                  } else {
                      logger.info("to many match jsonStr {}", jsonObjectZoneType.toJSONString());
                  }
              }
           }
        }

    }
}
