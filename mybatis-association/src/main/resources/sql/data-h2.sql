DELETE FROM user;

INSERT INTO user (id, name, age) VALUES
(1, 'James', 18),
(2, 'Mary', 20),
(3, 'John', 22);


DELETE FROM orders;

INSERT INTO orders (id, user_id, no) VALUES
(1, 1, '001'),
(2, 1, '002'),
(3, 2, '003'),
(4, 3, '004'),
(5, 3, '005');
