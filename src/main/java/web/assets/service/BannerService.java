package web.assets.service;

import web.assets.model.Banner;

import java.util.List;

public interface BannerService {

    /**
     * Создает новый баннер
     * @param banner - баннер для создания
     */
    void create(Banner banner);

    /**
     * Возвращает список всех имеющихся баннеров
     * @return список баннеров
     */
    List<Banner> readAll();

    /**
     * Возвращает баннер по его ID
     * @param id - ID баннера
     * @return - объект баннера с заданным ID
     */
    Banner read(int id);

    /**
     * Обновляет баннер с заданным ID,
     * в соответствии с переданным баннером
     * @param banner - баннер, в соответствии с которым нужно обновить данные
     * @param id - id баннера, который нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Banner banner, int id);

    /**
     * Удаляет баннер с заданным ID
     * @param id - id баннера, который нужно удалить
     * @return - true если баннер был удален, иначе false
     */
    boolean delete(int id);
}