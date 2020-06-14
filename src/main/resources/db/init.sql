DROP TABLE IF EXISTS point,baseline,baseline_point,level_reference CASCADE;

CREATE TABLE point
(
    point_id BIGSERIAL PRIMARY KEY,
    name CHARACTER VARYING(255),
    x DOUBLE PRECISION,
    y DOUBLE PRECISION,
    h DOUBLE PRECISION,
    created TIMESTAMP,
    updated TIMESTAMP
);

INSERT INTO point (name, x, y, h, created) VALUES ('PZ2011',2456.819,22227.944,23.968,'2020-06-06');
INSERT INTO point (name, x, y, h, created) VALUES ('PZ2013',3356.819,12387.944,33.968,'2020-06-06');
INSERT INTO point (name, x, y, h, created) VALUES ('PZ2015',3456.819,12187.944,25.968,'2020-06-06');
INSERT INTO point (name, x, y, h, created) VALUES ('PZ2017',3456.819,12287.944,23.968,'2020-06-06');

CREATE TABLE baseline
(
  baseline_id BIGSERIAL PRIMARY KEY,
  name CHARACTER VARYING(255),
  created TIMESTAMP,
  updated TIMESTAMP
);

CREATE TABLE baseline_point
(
    baseline_id BIGINT,
    point_id BIGINT,
    FOREIGN KEY (baseline_id) REFERENCES baseline(baseline_id),
    FOREIGN KEY (point_id) REFERENCES point(point_id)
);

CREATE TABLE level_reference
(
    level_reference_id BIGSERIAL PRIMARY KEY,
    name CHARACTER VARYING(255),
    elevation DOUBLE PRECISION,
    created TIMESTAMP,
    updated TIMESTAMP
);

INSERT INTO baseline(name, created) VALUES ('SST','2020-06-08');
INSERT INTO baseline(name, created) VALUES ('PST','2020-06-08');
INSERT INTO baseline(name, created) VALUES ('LST','2020-06-08');
INSERT INTO baseline(name, created) VALUES ('Vent Hodok','2020-06-08');
INSERT INTO baseline(name, created) VALUES ('Station','2020-06-08');

INSERT INTO baseline_point (baseline_id, point_id) VALUES (1,1);
INSERT INTO baseline_point (baseline_id, point_id) VALUES (1,2);
INSERT INTO baseline_point (baseline_id, point_id) VALUES (2,2);
INSERT INTO baseline_point (baseline_id, point_id) VALUES (2,3);
INSERT INTO baseline_point (baseline_id, point_id) VALUES (3,1);
INSERT INTO baseline_point (baseline_id, point_id) VALUES (3,4);
INSERT INTO baseline_point (baseline_id, point_id) VALUES (4,1);
INSERT INTO baseline_point (baseline_id, point_id) VALUES (4,4);
INSERT INTO baseline_point (baseline_id, point_id) VALUES (5,4);
INSERT INTO baseline_point (baseline_id, point_id) VALUES (5,2);

INSERT INTO level_reference(name, elevation, created) VALUES ('Rp22',33.543,'2020-06-12');
INSERT INTO level_reference(name, elevation, created) VALUES ('Rp44',33.111,'2020-06-12');
INSERT INTO level_reference(name, elevation, created) VALUES ('Rp32',55.512,'2020-06-12');
