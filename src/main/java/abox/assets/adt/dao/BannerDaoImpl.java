package abox.assets.adt.dao;

import abox.assets.adt.model.Banner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BannerDaoImpl implements BannerDao {

    private static final String SQL_GET_BANNER_BY_ID =
            "select id, name, path, type, status, drm from banners where id = :id";

    private static final String SQL_GET_BANNER_BY_TYPE =
            "select id, name, path, type, status, drm from banners where type = :type";

    private static final String SQL_GET_BANNER_NOT_BY_TYPE =
            "select id, name, path, type, status, drm from banners where type != :type";

    private static final String SQL_GET_BANNER_BY_MAIN =
            "select id, name, path, type, status, drm from banners where (type = :type and status = :status and drm = :drm)";

    private static final String SQL_GET_BANNER_BY_STATUS_AND_DRM =
            "select id, name, path, type, status, drm from banners where (type != :type and status = :status and drm = :drm)";

    private static final String SQL_GET_BANNER_ALL =
            "select id, name, path, type, status, drm from banners";

    private static final String SQL_UPDATE_BANNER =
            "update banners set path = :path where id = :id";

    private final BannerMapper bannerMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BannerDaoImpl(
            BannerMapper bannerMapper,
            NamedParameterJdbcTemplate jdbcTemplate
    ) {
        this.bannerMapper = bannerMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Banner> getBannerById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_GET_BANNER_BY_ID,
                            params,
                            bannerMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Banner>> getBannerByType(String type) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("type", type);
        try {
            return Optional.of(
                    jdbcTemplate.query(
                            SQL_GET_BANNER_BY_TYPE,
                            params,
                            bannerMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Banner>> getBannerNotByType(String type) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("type", type);
        try {
            return Optional.of(
                    jdbcTemplate.query(
                            SQL_GET_BANNER_NOT_BY_TYPE,
                            params,
                            bannerMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Banner> getBannerMain(boolean status, String drm) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("type", "main");
        params.addValue("status", status);
        params.addValue("drm", drm);
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_GET_BANNER_BY_MAIN,
                            params,
                            bannerMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Banner>> getBannerComplex(boolean status, String drm) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("type", "main"); //small, info
        params.addValue("status", status);
        params.addValue("drm", drm);
        try {
            return Optional.of(
                    jdbcTemplate.query(
                            SQL_GET_BANNER_BY_STATUS_AND_DRM,
                            params,
                            bannerMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Banner>> getBannerAll() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        try {
            return Optional.of(
                    jdbcTemplate.query(
                            SQL_GET_BANNER_ALL,
                            params,
                            bannerMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateBannerFile(String path, int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("path", path);
        params.addValue("id", id);
        jdbcTemplate.update(SQL_UPDATE_BANNER, params);
    }

}
