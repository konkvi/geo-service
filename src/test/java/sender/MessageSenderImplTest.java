package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    //Написать тесты для проверки языка отправляемого сообщения (класс MessageSender) с использованием Mockito
    //Поверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов.
    //Поверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к американскому сегменту адресов.

    @ParameterizedTest
    @CsvSource({"172.3.56.25, Добро Пожаловать", "96.3.56.25, Welcome"})
    void sendTest(String location, String welcomeMessage) {
        //класс-заглушка для класса возвращения текста
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро Пожаловать");
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Location locationRussia = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location locationUSA = new Location("New York", Country.USA, " 10th Avenue", 32);

        //класс-заглушка для класса определения локации по Ip
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.3.56.25")).thenReturn(locationRussia);
        Mockito.when(geoService.byIp("96.3.56.25")).thenReturn(locationUSA);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, location);
        String result = messageSender.send(headers);
        Assertions.assertEquals(welcomeMessage, result);
    }
}

