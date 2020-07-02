-- DROP TABLE IF EXISTS banners;
-- DROP SEQUENCE IF EXISTS banners_id_seq;
CREATE TABLE IF NOT EXISTS banners (
        id     INTEGER PRIMARY KEY ,
        name   VARCHAR(200) NOT NULL ,
        path   VARCHAR(400) NOT NULL ,
        type   INTEGER NOT NULL ,
    --     type   VARCHAR(20) NOT NULL ,
        status BOOLEAN ,
    --     drm    VARCHAR(10) NOT NULL
        drm    INTEGER
    -- DRM can be null
    --         VARCHAR(10) NOT NULL
    );
CREATE SEQUENCE banners_id_seq START WITH 25 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS types (
        id     INTEGER PRIMARY KEY ,
        name   VARCHAR(10) NOT NULL
    );
CREATE TABLE IF NOT EXISTS drms (
        id     INTEGER PRIMARY KEY ,
        name   VARCHAR(10) NOT NULL
    );
ALTER TABLE banners
    ADD CONSTRAINT fk_banner_types FOREIGN KEY (type)
        REFERENCES types (id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE banners
    ADD CONSTRAINT fk_banner_drms FOREIGN KEY (drm)
        REFERENCES drms (id) ON UPDATE CASCADE ON DELETE RESTRICT;