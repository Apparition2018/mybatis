package com.ljh;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ljh.dto.UserDTO;
import com.ljh.entity.Address;
import com.ljh.entity.User;
import com.ljh.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * MybatisPlusJoinTest
 *
 * @author ljh
 * @since 2023/7/20 10:44
 */
@SpringBootTest
public class MybatisPlusJoinTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        MPJLambdaWrapper<User> wrapper = new MPJLambdaWrapper<User>()
                // 查询 user 表全部字段
                .selectAll(User.class)
                .select(Address::getCity, Address::getAddress)
                .leftJoin(Address.class, Address::getUserId, User::getId);

        List<UserDTO> userList = userMapper.selectJoinList(UserDTO.class, wrapper);

        userList.forEach(System.out::println);
    }
}
