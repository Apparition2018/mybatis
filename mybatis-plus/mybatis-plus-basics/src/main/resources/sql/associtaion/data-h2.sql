DELETE FROM user3;

INSERT INTO user3 (id, company_id, name, age, email) VALUES
(1, 1, 'James', 18, 'James@baomidou.com'),
(2, 2,'Mary', 20, 'Mary@baomidou.com'),
(3, 1, 'John', 22, 'John@baomidou.com');


DELETE FROM company;

INSERT INTO company (id, name) VALUES
(1, 'Google'),
(2, 'Baidu');
