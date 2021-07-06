package com.ljh.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.mp.entity.User3;

import java.util.List;

public interface User3Service extends IService<User3> {

    List<User3> selectUserPage(IPage<User3> page, QueryWrapper<User3> wrapper);
}
