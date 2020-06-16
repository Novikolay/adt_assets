package web.assets.service;

import web.assets.model.Banner;

import java.nio.file.Path;
import java.util.List;

public interface BannerService {

//    /**
//     * Обновляет баннер с заданным ID,
//     * в соответствии с переданным баннером
//     * @param banner - баннер, в соответствии с которым нужно обновить данные
//     * @param id - id баннера, который нужно обновить
//     * @return - true если данные были обновлены, иначе false
//     */
//    boolean update(Banner banner, int id);

    /**
     * Возвращает баннер по его ID
     * @param bannerID -- ID баннера
     * @return         -- объект баннера с заданным ID
     */
    Banner getBanner(int bannerID);

    /**
     * Возвращает все баннеры по заданнолму типу
     * @param type  -- тип баннера
     * @return      -- объект баннера с заданным ID
     */
    List<Banner> getBannerByType(String type);

    /**
     * Возвращает все баннеры, отличные от заданного типа
     * @param type  -- тип баннера
     * @return      -- объект баннера с заданным ID
     */
    List<Banner> getBannerNotByType(String type);

    /**
     * Возвращает основной информационный банер по заданным параметрам
     * @param status    -- статус услуги (подключена == true, не подключена == false), обязательный параметр
     * @param drm       -- уровень защиты (если есть == L1/L2/L3), не обязательный параметр
     * @return
     */
    Banner getBannerMain(boolean status, String drm);

    /**
     * Возвращает двух комплектов вспомогательных информационных банеров (массивом)
     * @param status    -- статус услуги (подключена == true, не подключена == false), обязательный параметр
     * @param drm       -- уровень защиты (если есть == L1/L2/L3), не обязательный параметр
     * @return
     */
    List<Banner> getBannerComplex(boolean status, String drm);

    /**
     * Возвращает все баннеры (для отображения в админке)
     * @return
     */
    List<Banner> getBannerAll();

    /**
     * Обновляет данные заданного баннера
     * @param path -- обновление адреса
     * @param id   -- ID баннера, который нужно обновить
     */
    void updateBannerFile(String path, int id);
}