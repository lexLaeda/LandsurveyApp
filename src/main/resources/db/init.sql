DROP TABLE IF EXISTS point,baseline,baseline_point CASCADE;

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
)
