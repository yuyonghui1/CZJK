package com.itheima.service;

import com.itheima.pojo.Member;

import java.util.List;
import java.util.Map;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
public interface MemberService {
    Member findByTelephone(String telephone);

    void add(Member member);

    Map<String,Object> getMemberReport();

    List<Map<String,Object>> getSex();

    List<Map<String,Object>> getSex2();

    Map<String,Object> getAge();



    //查询会员性别



}
