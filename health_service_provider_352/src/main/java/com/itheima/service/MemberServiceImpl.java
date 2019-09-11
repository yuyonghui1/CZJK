package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        memberDao.add(member);
    }

    /**
     * 需要的数据
     *      months: [2018.10,.....,2019.9]
     *      memberCount: [100,.....,1000]
     * @return
     */
    @Override
    public Map<String, Object> getMemberReport() {

        //获取最近的12个月
        List<String> months = new ArrayList<>();
        List<Long> memberCount = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -11);

        for (int i = 0; i < 12; i++) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            String str = simpleDateFormat.format(calendar.getTime());
            calendar.add(Calendar.MONTH, 1);
            months.add(str);
        }
        //memberCount:每个会员数量
        for (String date : months) {
            // 2018-10-31
            date += "-31";
            long count = memberDao.findByBeforeDate(date);
            memberCount.add(count);
        }

        //组合数据
        Map<String,Object> map = new HashMap<>();
        map.put("months",months);
        map.put("memberCount",memberCount);

        return map;
    }

    @Override
    public List<Map<String, Object>> getSex() {
        return memberDao.getSex();
    }

    @Override
    public List<Map<String, Object>> getSex2() {
        return memberDao.getSex2();
    }

    @Override
    public Map<String, Object> getAge() {
        List<Map<String,Object>> memberAgecount = new ArrayList<>();
        Map<String,Object> mapList = new HashMap<>();
        Map<String,Object> mapList1 = new HashMap<>();
        Map<String,Object> mapList2 = new HashMap<>();
        Map<String,Object> mapList3 = new HashMap<>();
        List<String> memberAgename = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        int dateT = Integer.parseInt(dateFormat.format(date));//当前年
        List<Date> birthdayDate = memberDao.getAge();//获取会员生日
        int a=0;//定义0-18人数累加
        int b=0;//定义18-30人数累加
        int c=0;//定义30-45人数累加
        int d=0;//定义45以上人数累加
        for (Date dateA : birthdayDate) {
            int age = dateT - Integer.parseInt(dateFormat.format(dateA));//用户年龄
            if(age<0){
                continue;
            }
            if (0<=age&&age<=18){
                mapList1.put("value",a++);
                mapList1.put("name","0-18");
            }else if (18<age&& age<=30){
                mapList2.put("value",b++);
                mapList2.put("name","18-30");
            }else if (30<age && age<=45){
                mapList3.put("value",c++);
                mapList3.put("name","30-45");
            }else {
                mapList.put("value",d++);
                mapList.put("name","45以上");
            }
        }
        memberAgecount.add(mapList);
        memberAgecount.add(mapList1);
        memberAgecount.add(mapList2);
        memberAgecount.add(mapList3);
        for (Map<String, Object> map : memberAgecount) {
            memberAgename.add(String.valueOf(map.get("name")));
        }
        Map<String, Object> map=new HashMap<>();
        map.put("memberAgecount",memberAgecount);
        map.put("memberAgename",memberAgename);
        return map;
    }



    }



