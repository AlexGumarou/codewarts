CREATE TABLE department(
                           id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(250),
                           address VARCHAR(250),
                           phone VARCHAR(250));
INSERT INTO department(name, address, phone) VALUES
('Каменная горка', 'Налибокская 38', '3752912345678'),
('Юго-Запад', 'Алибегова 12', '3752998765432'),
('Восток', 'Мстиславца 3', '3755698798562');
CREATE TABLE child_group(
                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(250),
                            lessonTime VARCHAR(250),
                            department_id INT,
                            FOREIGN KEY (department_id)  REFERENCES department (id));
INSERT INTO child_group(name, lessonTime, department_id) VALUES
('1a', '1 час', '1'),
('1b', '1 час', '1'),
('1c','1 час 20 минут', '1'),
('1d','1 час 20 минут', '1'),
('2a','1 час', '2'),
('2b','1 час', '2'),
('2c','1 час 20 минут', '2'),
('2d','1 час 20 минут', '2');
CREATE TABLE parent(
                       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       mother VARCHAR(250),
                       father VARCHAR(250),
                       phone_mother VARCHAR(250),
                       phone_father VARCHAR(250));
INSERT INTO parent (mother, father, phone_mother, phone_father) VALUES
('Иванова Ирина Адреевна', 'Иванов Павел Сергеевич', '375295566777','375295566777'),
('Петрова Ольга Ивановна', 'Петров Сергей Сергеевич', '375295566777','375295566777'),
('Сидорова Антонина Игоревна', 'Сидоров Олег Петрович', '375295566777','375295566777'),
('Козлова Ирина Адреевна', 'Козлов Павел Сергеевич', '375295566777','375295566777'),
('Фролова Ольга Ивановна', 'Фролов Сергей Сергеевич', '375295566777','375295566777'),
('Зубова Антонина Игоревна', 'Зубов Олег Петрович', '375295566777','375295566777');
CREATE TABLE child(
                      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(250),
                      surname VARCHAR(250),
                      birthday_date DATE,
                      child_group_id INT,
                      parent_id INT,
                      FOREIGN KEY (child_group_id)  REFERENCES child_group (id),
                      FOREIGN KEY (parent_id)  REFERENCES parent (id));
INSERT INTO child (name, surname, birthday_date, child_group_id, parent_id) VALUES
('Семен','Иванов','2020-02-22','1','1'),
('Филипп','Петров','2020-02-16','2','2'),
('Кирилл','Сидоров','2020-02-15','3','3'),
('Иван','Козлов','2020-05-05','1','4'),
('Максим','Фролов','2020-05-15','2','5'),
('Андрей','Зубов','2020-05-25','3','6');
INSERT INTO child (name, surname, birthday_date, child_group_id) VALUES
('Стас','Иванов','2020-03-03','4'),
('Андрей','Иванов','2020-05-05','5'),
('Анатолий','Иванов','2020-05-05','6'),
('Александр','Иванов','2020-05-05','7'),
('Александр','Кулягов','2020-05-05','1'),
('Александр','Аверин','2020-05-05','1'),
('Александр','Кузьмич','2020-05-05','1'),
('Алексей','Иванов','2020-05-05','8');
CREATE TABLE payment(
                        id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        payment_date DATE,
                        child_id INT,
                        sum VARCHAR(250),
                        FOREIGN KEY (child_id)  REFERENCES child (id));
INSERT INTO payment(PAYMENT_DATE, CHILD_ID, SUM) VALUES
('2020-12-25', '1','80'),
('2020-12-25', '2','80'),
('2020-12-25', '3','80'),
('2020-12-25', '4','80'),
('2020-12-25', '5','80'),
('2020-12-25', '6','80'),
('2020-12-25', '7','80'),
('2020-12-25', '8','80'),
('2020-12-25', '9','80'),
('2020-12-25', '10','80'),
('2020-12-25', '11','80');
CREATE TABLE attendance(
                           id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           date DATE,
                           child_id INT,
                           FOREIGN KEY (child_id)  REFERENCES child (id));
INSERT INTO attendance(DATE, CHILD_ID) VALUES
('2021-01-18','1'),
('2021-01-18','2'),
('2021-01-18','3'),
('2021-01-18','4'),
('2021-01-18','5'),
('2021-01-18','6'),
('2021-01-18','7'),
('2021-01-18','8'),
('2021-01-18','9'),
('2021-01-18','10'),
('2021-01-25','1'),
('2021-01-25','2'),
('2021-01-25','3'),
('2021-01-25','4'),
('2021-01-25','5'),
('2021-01-25','6'),
('2021-01-25','7'),
('2021-01-25','8'),
('2021-01-25','9'),
('2021-01-25','10');
CREATE TABLE staff_role(
                           id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           role VARCHAR(250));
INSERT INTO staff_role (role)VALUES
('TEACHER'),
('ADMIN'),
('DIRECTOR');
CREATE TABLE staff(
                      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(250),
                      surname VARCHAR(250),
                      birthday_date DATE,
                      login VARCHAR(250),
                      pass VARCHAR(250),
                      address VARCHAR(250),
                      email VARCHAR(250),
                      phone VARCHAR(250),
                      phone_additional VARCHAR(250),
                      role_id INT,
                      department_id INT,
                      price_per_hour VARCHAR(250),
                      FOREIGN KEY (department_id)  REFERENCES department (id),
                      FOREIGN KEY (role_id) REFERENCES staff_role (id));
INSERT INTO staff (name, surname, birthday_date, login, pass, address, email, phone, role_id, department_id, price_per_hour) VALUES
('Анастасия', 'Чернявская', '1984-05-27','admin','$2y$12$vDvCJgUlo8c3neK72dtOXu.53drY50U68DOsuSa.YJeb8S6K6JM0C','Minsk','nast@mail.ru','375295565456','2','1', '0'),
('Карина', 'Корнеева', '1984-05-27','teacher','$2y$12$yjKEuhEMPDm8JDvY1Xtd/uCCmcLXnTMlgLYRWHsPBzV0IRM8AK.Vq','Minsk','nast@mail.ru','375295565456','1','1', '15'),
('Ирина', 'Петровна', '1984-05-27','tea','$2y$12$/KzkPM8hsXBJvgCUCUPeteNl7Kd6jMtHsKcnXiR0pHZTHa1cLvX4q','Minsk','nast@mail.ru','375295565456','1','1', '15'),
('Светлана', 'Булатская', '1984-05-27','director','$2y$12$bLJ4XvnK/qhRA.YjMgUMGO.JJPwvzNNRLYU6E7NEHRqjEtDaMs6Gi','Minsk','nast@mail.ru','375295565456','3','1','0');
CREATE TABLE theme (
                       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(250));
INSERT INTO theme(name) VALUES
('Урок робототехники. Модель "Самолет"'),
('Урок робототехники. Модель "Ракета"'),
('Урок робототехники. Модель "Танцующий человечек"'),
('Урок робототехники. Модель "Лягушка"'),
('Урок Kodu. "Мотоцикл"'),
('Урок Kodu. "Стреляющие роботы"'),
('Урок Kodu. "Построение мира"'),
('Урок Kodu. "Дискотека"'),
('Урок Scratch. "Дракон"'),
('Урок Scratch. "Почтальон"'),
('Урок Scratch. "Поймай птичку"'),
('Урок Scratch. "Знакомство с программой"');
CREATE TABLE accounting(
                           id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           staff_id INT,
                           child_group_id INT,
                           theme_id INT,
                           date DATE,
                           FOREIGN KEY (staff_id)  REFERENCES staff (id),
                           FOREIGN KEY (theme_id)  REFERENCES theme (id),
                           FOREIGN KEY (child_group_id)  REFERENCES child_group (id));
INSERT INTO accounting(staff_id, CHILD_GROUP_ID, DATE, theme_id) VALUES
('2','1','2021-01-25','1'),
('2','1','2021-02-01','5'),
('2','1','2021-02-08','9'),
('2','1','2021-02-15','2'),
('2','2','2021-01-25','1'),
('2','2','2021-02-01','5'),
('2','2','2021-02-08','9'),
('2','2','2021-02-15','2'),
('2','3','2021-01-25','1'),
('2','3','2021-02-01','5'),
('2','3','2021-02-08','9'),
('2','3','2021-02-15','2'),
('2','4','2021-01-28','4');