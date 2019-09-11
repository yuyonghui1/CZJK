package com.itheima.jobs;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.service.MemberService;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @Author zwh
 * @Date 2019/9/10 14:28
 */
public class ClearSettingJobs {
    @Reference
    private OrderSettingService orderSettingService;

    public void clear(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String DelDate = simpleDateFormat.format(date);
        orderSettingService.del(DelDate);
    }

}
