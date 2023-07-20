DELETE
FROM user;

INSERT INTO user (id, name, age, email)
VALUES (1, 'Jone', 18, 'test1@baomidou.com'),
       (2, 'Jack', 20, 'test2@baomidou.com'),
       (3, 'Tom', 28, 'test3@baomidou.com'),
       (4, 'Sandy', 21, 'test4@baomidou.com'),
       (5, 'Billie', 24, 'test5@baomidou.com');

DELETE
FROM address;

INSERT INTO address (id, user_id, city, address)
VALUES (1, 1, '北京', '人民广场'),
       (2, 2, '上海', '人民广场'),
       (3, 3, '广州', '人民广场'),
       (4, 4, '上海', '人民广场'),
       (5, 5, '北京', '人民广场');