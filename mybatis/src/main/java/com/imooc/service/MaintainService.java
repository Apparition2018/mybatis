package com.imooc.service;

import com.imooc.dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

/**
 * MaintainService
 * <p>
 * 维护相关的业务功能
 *
 * @author Arsenal
 * created on 2019/8/5 12:59
 */
public class MaintainService {

    /**
     * 单条删除
     */
    public void deleteOne(String id) {
        if (id != null && !"".equalsIgnoreCase(id.trim())) {
            new MessageDao().deleteOne(Integer.valueOf(id));
        }
    }

    /**
     * 批量删除
     */
    public void deleteBatch(String[] ids) {
        List<Integer> idList = new ArrayList<>();
        for (String id : ids) {
            idList.add(Integer.valueOf(id));
        }
        new MessageDao().deleteBatch(idList);
    }
}
