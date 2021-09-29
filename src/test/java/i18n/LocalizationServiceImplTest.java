package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    //Написать тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)
    //Проверить работу метода public String locale(Country country)

    LocalizationServiceImpl localizationService;

    @BeforeEach
    public void initTest() {
        System.out.println("Тест работы возвращаемого текста");
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    public void localeTestUSA(){
        String testResult = localizationService.locale(Country.USA);
        Assertions.assertEquals("Welcome", testResult);
    }

    @Test
    public void localeTestRussia(){
        String testResult = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals("Добро пожаловать", testResult);
    }
}
