package com.ljh.mp;

import com.ljh.mp.dao.PeopleMapper;
import com.ljh.mp.entity.People;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 字段类型处理器：https://mp.baomidou.com/guide/typehandler.html
 */
@SpringBootTest
public class TypeHandlerTest {

    @Autowired
    private PeopleMapper peopleMapper;

    @Test
    public void test() {
        People jone = peopleMapper.selectById(1);
        Assertions.assertEquals("Jone", jone.getName());
        Assertions.assertEquals(2, jone.getWallets().size());
        Assertions.assertEquals("微信钱包", jone.getWallets().get(1).getName());

        People jack = peopleMapper.selectById(2);
        Assertions.assertEquals("Jack", jack.getName());
        Assertions.assertEquals(1, jack.getWallets().size());
        Assertions.assertEquals("银联钱包", jack.getWallets().get(0).getName());
    }
}
