package abox.assets.adt.service;

import abox.assets.adt.model.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BannerService {
//    /**
//     * Возвращает все баннеры (для отображения в админке)
//     * @return
//     */
//    List<Banner> getBannerAll();

    /**
     * -- Если параметры с <Optional> НЕ заданы, то:
     * Возвращает все баннеры по заданнолму типу
     * @param type  -- тип баннера
     * @return      -- объект баннера с заданным ID
     * -- иначе:
     * Возвращает основной информационный банер (main) по заданным параметрам
     * @param status    -- статус услуги (подключена == true, не подключена == false), обязательный параметр
     * @param drm       -- уровень защиты (если есть == L1/L2/L3), не обязательный параметр
     * @return
     */
    List<Banner> findByType(String type, Optional<Boolean> status, Optional<String> drm);

    /**
     * -- Если параметры с <Optional> НЕ заданы, то:
     * Возвращает все баннеры, отличные от заданного типа
     * @param type  -- тип баннера
     * @return      -- объект баннера с заданным ID
     * -- иначе:
     * Возвращает комплект из двух вспомогательных банеров (small + info) по заданным параметрам
     * @param status    -- статус услуги (подключена == true, не подключена == false), обязательный параметр
     * @param drm       -- уровень защиты (если есть == L1/L2/L3), не обязательный параметр
     * @return
     */
    List<Banner> findByTypeNotLike(String type, Optional<Boolean> status, Optional<String> drm);

    /**
     * Обновляет данные заданного баннера
     * @param path -- обновление адреса
     * @param id   -- ID баннера, который нужно обновить
     */
    boolean update(int id, MultipartFile path);

    /**
     * Преобразование banner.type & banner.drm в человекочитаемый
     * вид для отображения в админке
     * @param banners
     * @return
     */
    List<Object[]> convertBannerData (List<Banner> banners);

    /**
     * Преобразование banner.type & banner.drm в человекочитаемый
     * вид для отображения в админке + обработка img для preview
     * @param banners
     * @return
     */
    List<Banner> changeBannerData (List<Banner> banners);

    /**
     * Создание нового баннера с заданными параметрами
     * @param name
     * @param path
     * @param type
     * @param status
     * @param drm
     */
    void addBanner(String name, MultipartFile path, String type, boolean status, String drm);

}