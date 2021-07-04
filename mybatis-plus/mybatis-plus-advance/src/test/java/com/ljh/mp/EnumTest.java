package com.ljh.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ljh.mp.dao.PersonMapper;
import com.ljh.mp.entity.Person;
import com.ljh.mp.enums.*;
import org.assertj.core.data.TemporalUnitWithinOffset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 通用枚举：https://mp.baomidou.com/guide/enum.html
 */
@SpringBootTest
public class EnumTest {

    @Resource
    private PersonMapper personMapper;

    @Test
    public void insert() {
        Person person = new Person();
        person.setName("K神");
        person.setAge(AgeEnum.ONE);
        person.setGender(GenderEnum.MALE);
        person.setGrade(GradeEnum.HIGH);
        person.setStrEnum(StrEnum.ONE);
        person.setEmail("abc@mp.com");
        Assertions.assertTrue(personMapper.insert(person) > 0);
        // 成功直接拿回写的 ID
        System.err.println("\n插入成功 ID 为：" + person.getId());

        List<Person> list = personMapper.selectList(null);
        for (Person p : list) {
            System.out.println(p);
            Assertions.assertNotNull(p.getAge());
            if (p.getId().equals(person.getId())) {
                Assertions.assertNotNull(p.getGender());
                Assertions.assertNotNull(p.getGrade());
                Assertions.assertNotNull(p.getStrEnum());
            }
        }
    }

    @Test
    public void delete() {
        Assertions.assertTrue(personMapper.delete(new QueryWrapper<Person>().lambda().eq(Person::getAge, AgeEnum.TWO)) > 0);
    }
    
    @Test
    public void update() {
        Assertions.assertTrue(personMapper.update(new Person().setAge(AgeEnum.TWO), new QueryWrapper<Person>().eq("age", AgeEnum.THREE)) > 0);
    }
    
    @Test
    public void select() {
        Person person = personMapper.selectOne(new QueryWrapper<Person>().lambda().eq(Person::getId, 2));
        Assertions.assertEquals("Jack", person.getName());
        Assertions.assertSame(AgeEnum.TWO, person.getAge());

        List<Person> personList = personMapper.selectList(new QueryWrapper<Person>().lambda().eq(Person::getUserState, UserState.ACTIVE));
        Assertions.assertEquals(2, personList.size());
        Optional<Person> personOptional = personList.stream().filter(p -> p.getId() == 1).findFirst();
        personOptional.ifPresent(p -> Assertions.assertSame(p.getUserState(), UserState.ACTIVE));
    }


}
