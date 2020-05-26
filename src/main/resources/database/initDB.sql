-- CREATE TABLE IF NOT EXISTS banners
-- (
--     id    BIGSERIAL PRIMARY KEY ,
--     name  VARCHAR(200) NOT NULL ,
--     email VARCHAR(254) NOT NULL ,
--     phone VARCHAR(20)  NOT NULL
-- );
--
--DROP TABLE IF EXISTS banners
--
-- CREATE TABLE IF NOT EXISTS banners
-- (
--     id    SERIAL PRIMARY KEY ,
--     name  VARCHAR(200) NOT NULL ,
--     email VARCHAR(254) NOT NULL ,
--     phone VARCHAR(50)  NOT NULL
-- );
-- CREATE TABLE IF NOT EXISTS banners
-- (
--     id    INTEGER PRIMARY KEY ,
--     name  VARCHAR(200) NOT NULL ,
--     email VARCHAR(254) NOT NULL ,
--     phone VARCHAR(50)  NOT NULL
-- );
-- CREATE SEQUENCE banners_id_seq START WITH 3 INCREMENT BY 1;
-- DROP TABLE IF EXISTS banners;
-- DROP SEQUENCE IF EXISTS banners_id_seq
CREATE TABLE IF NOT EXISTS banners
(
    id     INTEGER PRIMARY KEY ,
    name   VARCHAR(200) NOT NULL ,
    path   VARCHAR(400) NOT NULL ,
    type   VARCHAR(20) NOT NULL ,
    status BOOLEAN ,
    drm    VARCHAR(10) NOT NULL
);
CREATE SEQUENCE banners_id_seq START WITH 4 INCREMENT BY 1;