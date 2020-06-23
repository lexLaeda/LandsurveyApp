DROP TABLE IF EXISTS point,baseline,baseline_point,level_reference,code,holiday,post,address,contact,employee,department CASCADE;

CREATE TABLE level_reference
(
    level_reference_id BIGSERIAL PRIMARY KEY,
    name               CHARACTER VARYING(255),
    elevation          DOUBLE PRECISION,
    created            TIMESTAMP,
    updated            TIMESTAMP
);

CREATE TABLE point
(
    point_id BIGSERIAL PRIMARY KEY,
    name     CHARACTER VARYING(255),
    x        DOUBLE PRECISION,
    y        DOUBLE PRECISION,
    h        DOUBLE PRECISION,
    created  TIMESTAMP,
    updated  TIMESTAMP,
    level_reference_id BIGINT,
    FOREIGN KEY (level_reference_id) REFERENCES level_reference(level_reference_id)
);

INSERT INTO point (name, x, y, h, created)
VALUES ('PZ2011', 2456.819, 22227.944, 23.968, '2020-06-06');
INSERT INTO point (name, x, y, h, created)
VALUES ('PZ2013', 3356.819, 12387.944, 33.968, '2020-06-06');
INSERT INTO point (name, x, y, h, created)
VALUES ('PZ2015', 3456.819, 12187.944, 25.968, '2020-06-06');
INSERT INTO point (name, x, y, h, created)
VALUES ('PZ2017', 3456.819, 12287.944, 23.968, '2020-06-06');

CREATE TABLE baseline
(
    baseline_id BIGSERIAL PRIMARY KEY,
    name        CHARACTER VARYING(255),
    created     TIMESTAMP,
    updated     TIMESTAMP
);

CREATE TABLE baseline_point
(
    baseline_id BIGINT,
    point_id    BIGINT,
    FOREIGN KEY (baseline_id) REFERENCES baseline (baseline_id),
    FOREIGN KEY (point_id) REFERENCES point (point_id)
);

INSERT INTO baseline(name, created)
VALUES ('SST', '2020-06-08');
INSERT INTO baseline(name, created)
VALUES ('PST', '2020-06-08');
INSERT INTO baseline(name, created)
VALUES ('LST', '2020-06-08');
INSERT INTO baseline(name, created)
VALUES ('Vent Hodok', '2020-06-08');
INSERT INTO baseline(name, created)
VALUES ('Station', '2020-06-08');

INSERT INTO baseline_point (baseline_id, point_id)
VALUES (1, 1);
INSERT INTO baseline_point (baseline_id, point_id)
VALUES (1, 2);
INSERT INTO baseline_point (baseline_id, point_id)
VALUES (2, 2);
INSERT INTO baseline_point (baseline_id, point_id)
VALUES (2, 3);
INSERT INTO baseline_point (baseline_id, point_id)
VALUES (3, 1);
INSERT INTO baseline_point (baseline_id, point_id)
VALUES (3, 4);
INSERT INTO baseline_point (baseline_id, point_id)
VALUES (4, 1);
INSERT INTO baseline_point (baseline_id, point_id)
VALUES (4, 4);
INSERT INTO baseline_point (baseline_id, point_id)
VALUES (5, 4);
INSERT INTO baseline_point (baseline_id, point_id)
VALUES (5, 2);

INSERT INTO level_reference(name, elevation, created)
VALUES ('Rp22', 33.543, '2020-06-12');
INSERT INTO level_reference(name, elevation, created)
VALUES ('Rp44', 33.111, '2020-06-12');
INSERT INTO level_reference(name, elevation, created)
VALUES ('Rp32', 55.512, '2020-06-12');

CREATE TABLE code
(
    code_id     SERIAL PRIMARY KEY NOT NULL,
    status      CHARACTER VARYING(5),
    description CHARACTER VARYING(255),
    created     TIMESTAMP          NOT NULL,
    updated     TIMESTAMP
);

INSERT INTO code (status, description, created)
VALUES ('Я', 'Полный рабочий день', '2020-05-15');
INSERT INTO code (status, description, created)
VALUES ('Н', 'Отсутствие на рабочем месте по невыясненной причине', '2020-05-15');
INSERT INTO code (status, description, created)
VALUES ('B', 'Выходные и праздничные дни', '2020-05-15');
INSERT INTO code (status, description, created)
VALUES ('Рв', 'Работа в праздничные и выходные дни; а также работа в праздничные и выходные дни, при
нахождении в командировке', '2020-05-15');
INSERT INTO code (status, description, created)
VALUES ('Б', 'Дни временной нетрудоспособности', '2020-05-15');
INSERT INTO code (status, description, created)
VALUES ('К', 'Командировочные дни; а также, выходные (нерабочие) дни при нахождении в командировке,
когда сотрудник отдыхает, в соответствии с графиком работы ООО «Наука» в командировке', '2020-05-15');
INSERT INTO code (status, description, created)
VALUES ('ОТ', 'Ежегодный основной оплаченный отпуск', '2020-05-15');
INSERT INTO code (status, description, created)
VALUES ('До', 'Неоплачиваемый отпуск', '2020-05-15');
INSERT INTO code (status, description, created)
VALUES ('Хд', 'Хозяйственный день', '2020-05-15');
INSERT INTO code (status, description, created)
VALUES ('У', 'Отпуск на период обучения', '2020-05-15');
INSERT INTO code (status, description, created)
VALUES ('Ож', 'Отпуск по уходу за ребенком', '2020-05-15');

CREATE TABLE holiday
(
    holiday_id SERIAL PRIMARY KEY NOT NULL,
    name       CHARACTER VARYING(255),
    date       TIMESTAMP,
    created    TIMESTAMP          NOT NULL,
    updated    TIMESTAMP
);
INSERT INTO holiday (date, created)
VALUES ('2020-01-01', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-02', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-03', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-04', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-05', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-06', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-07', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-08', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-11', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-12', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-18', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-19', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-25', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-01-26', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-02-01', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-02-02', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-02-08', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-02-09', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-02-15', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-02-16', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-02-22', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-02-23', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-02-24', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-02-29', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-03-01', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-03-07', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-03-08', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-03-09', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-03-14', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-03-15', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-03-21', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-03-22', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-03-28', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-03-29', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-04-04', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-04-05', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-04-11', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-04-12', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-04-18', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-04-19', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-04-25', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-04-26', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-01', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-02', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-03', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-04', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-05', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-09', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-10', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-11', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-16', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-17', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-23', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-24', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-30', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-05-31', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-06-06', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-06-07', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-06-08', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-06-12', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-06-13', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-06-14', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-06-20', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-06-21', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-06-27', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-06-28', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-07-04', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-07-05', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-07-11', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-07-12', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-07-18', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-07-19', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-07-25', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-07-26', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-08-01', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-08-02', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-08-08', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-08-09', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-08-15', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-08-16', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-08-22', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-08-23', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-08-29', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-08-30', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-09-05', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-09-06', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-09-12', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-09-13', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-09-19', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-09-20', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-09-26', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-09-27', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-10-03', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-10-04', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-10-10', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-10-11', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-10-17', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-10-18', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-10-24', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-10-25', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-10-31', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-11-01', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-11-04', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-11-07', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-11-08', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-11-14', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-11-15', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-11-21', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-11-22', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-11-28', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-11-29', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-12-05', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-12-06', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-12-12', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-12-13', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-12-19', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-12-20', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-12-26', '2020-05-15');
INSERT INTO holiday (date, created)
VALUES ('2020-12-27', '2020-05-15');

CREATE TABLE post
(
    post_id BIGSERIAL PRIMARY KEY NOT NULL,
    name    CHARACTER VARYING(100),
    created TIMESTAMP             NOT NULL,
    updated TIMESTAMP
);

INSERT INTO post (name, created)
VALUES ('Frontend Developer', '2020-05-15');
INSERT INTO post (name, created)
VALUES ('Backend Developer', '2020-05-15');
INSERT INTO post (name, created)
VALUES ('Devops', '2020-05-15');
INSERT INTO post (name, created)
VALUES ('Big Data developer', '2020-05-15');
INSERT INTO post (name, created)
VALUES ('QA engineer', '2020-05-15');
INSERT INTO post (name, created)
VALUES ('Human resource manager', '2020-05-15');
INSERT INTO post (name, created)
VALUES ('Project Manager', '2020-05-15');
INSERT INTO post (name, created)
VALUES ('Frontend TeamLead', '2020-05-15');
INSERT INTO post (name, created)
VALUES ('Backend Teemlead', '2020-05-15');
INSERT INTO post (name, created)
VALUES ('SEO', '2020-05-15');
INSERT INTO post (name, created)
VALUES ('Designer', '2020-05-15');
INSERT INTO post (name, created)
VALUES ('Project manager', '2020-05-15');


CREATE TABLE department
(
    department_id BIGSERIAL PRIMARY KEY NOT NULL,
    name          CHARACTER VARYING(30),
    label         CHARACTER VARYING(30),
    created       TIMESTAMP             NOT NULL,
    updated       TIMESTAMP
);

INSERT INTO department (name, created)
VALUES ('Rocket Science Department', '2020-05-15');
INSERT INTO department (name, created)
VALUES ('Tesla Department', '2020-05-15');
INSERT INTO department (name, created)
VALUES ('HyperLoop Department', '2020-05-15');
INSERT INTO department (name, created)
VALUES ('Underground Department', '2020-05-15');
INSERT INTO department (name, created)
VALUES ('Social Department', '2020-05-15');
INSERT INTO department (name, created)
VALUES ('Game Dev Department', '2020-05-15');



CREATE TABLE contact
(
    contact_id BIGSERIAL PRIMARY KEY NOT NULL,
    email      CHARACTER VARYING(100),
    phone      CHARACTER VARYING(100),
    created    TIMESTAMP             NOT NULL,
    updated    TIMESTAMP
);

CREATE TABLE address
(
    address_id BIGSERIAL PRIMARY KEY NOT NULL,
    city       CHARACTER VARYING(100),
    street     CHARACTER VARYING(100),
    house      CHARACTER VARYING(100),
    apartment  CHARACTER VARYING(100),
    created    TIMESTAMP             NOT NULL,
    updated    TIMESTAMP
);

CREATE TABLE employee
(
    employee_id   BIGSERIAL PRIMARY KEY NOT NULL,
    num           CHARACTER VARYING(100),
    first_name    CHARACTER VARYING(100),
    last_name     CHARACTER VARYING(100),
    birthday      TIMESTAMP,
    gender        CHARACTER VARYING(30),
    is_remote     BOOLEAN,
    address_id    BIGINT,
    contact_id    BIGINT,
    post_id       BIGINT,
    department_id BIGINT,
    created       TIMESTAMP             NOT NULL,
    updated       TIMESTAMP,
    FOREIGN KEY (address_id) REFERENCES address (address_id),
    FOREIGN KEY (contact_id) REFERENCES contact (contact_id),
    FOREIGN KEY (post_id) REFERENCES post(post_id),
    FOREIGN KEY (department_id) REFERENCES department (department_id)
);
select * from employee;