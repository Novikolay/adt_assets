package web.assets.dao;

import web.assets.model.Banner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BannerMapper implements RowMapper<Banner> {

    @Override
    public Banner mapRow(ResultSet rs, int rowNum) throws SQLException {
        Banner banner = new Banner();
        banner.setId(rs.getInt("id"));
        banner.setName(rs.getString("name"));
        banner.setPath(rs.getString("path"));
        banner.setType(rs.getString("type"));
        banner.setStatus(rs.getBoolean("status"));
        banner.setDRM(rs.getString("drm"));
        return banner;
    }
}
