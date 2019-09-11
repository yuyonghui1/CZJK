package com.itheima.dao;

import com.itheima.pojo.Member;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
public interface MemberDao {
    Member findByTelephone(String telephone);

    void add(Member member);

    long findByBeforeDate(String date);

    long findTodayNewMember(String todayDate);

    long findTotalMember();

    long findNewMemberByAfterDate(String date);



    List<Map<String,Object>> getSex();

    List<Map<String,Object>> getSex2();


    List<Date> getAge();

}
