package abox.assets.adt.service;

import abox.assets.adt.model.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BannerService {

    /**
     * Возвращает баннер по его ID
     * @param bannerID -- ID баннера
     * @return         -- объект баннера с заданным ID
     */
    Banner getOne(int bannerID);

    /**
     * Возвращает все баннеры по заданнолму типу
     * @param type  -- тип баннера
     * @return      -- объект баннера с заданным ID
     */
    //List<Banner> findByTypeOnly(String type);

    /**
     * Возвращает все баннеры, отличные от заданного типа
     * @param type  -- тип баннера
     * @return      -- объект баннера с заданным ID
     */
    //List<Banner> findByTypeNotLikeOnly(String type);

    /**
     * Возвращает основной информационный банер по заданным параметрам
     * @param status    -- статус услуги (подключена == true, не подключена == false), обязательный параметр
     * @param drm       -- уровень защиты (если есть == L1/L2/L3), не обязательный параметр
     * @return
     */
    List<Banner> findByType(String type, Optional<Boolean> status, Optional<String> drm);

    /**
     * Возвращает двух комплектов вспомогательных информационных банеров (массивом)
     * @param status    -- статус услуги (подключена == true, не подключена == false), обязательный параметр
     * @param drm       -- уровень защиты (если есть == L1/L2/L3), не обязательный параметр
     * @return
     */
    List<Banner> findByTypeNotLike(String type, Optional<Boolean> status, Optional<String> drm);

//    /**
//     * Возвращает все баннеры (для отображения в админке)
//     * @return
//     */
//    List<Banner> getBannerAll();

    /**
     * Обновляет данные заданного баннера
     * @param path -- обновление адреса
     * @param id   -- ID баннера, который нужно обновить
     */
    boolean update(int id, MultipartFile path);
}