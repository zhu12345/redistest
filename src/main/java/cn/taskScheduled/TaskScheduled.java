package cn.taskScheduled;

import ch.ethz.ssh2.Session;
import cn.LinuxShell.CheckCDRNoService;
import cn.LinuxShell.impl.CheckCDRNoServiceImpl;
import cn.constant.ConstantKey;
import cn.hws.bo.ZoneConfig;
import cn.redis.utils.RedisUtils;
import cn.util.httpUtil.HttpRestImpl;
import cn.util.httpUtil.dao.HttpRest;
import cn.util.shell.ShellOperateUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TaskScheduled {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Resource
    private CheckCDRNoService checkCDRNoService;

    public void repay() {
        checkCDRNoService.repay();
    }
}
