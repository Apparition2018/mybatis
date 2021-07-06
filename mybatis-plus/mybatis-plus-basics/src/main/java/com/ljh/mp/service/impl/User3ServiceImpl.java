package com.ljh.mp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.mp.dao.User3Mapper;
import com.ljh.mp.entity.User3;
import com.ljh.mp.service.User3Service;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User3ServiceImpl extends ServiceImpl<User3Mapper, User3> implements User3Service {

    @Override
    public List<User3> selectUserPage(IPage<User3> page, QueryWrapper<User3> wrapper) {
        return baseMapper.selectUserPage(page, wrapper);
    }
}