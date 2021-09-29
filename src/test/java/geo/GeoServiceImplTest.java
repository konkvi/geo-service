package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {
    //Написать тесты для проверки определения локации по ip (класс GeoServiceImpl)
    //Проверить работу метода public Location byIp(String ip)

    @ParameterizedTest
    @ValueSource(strings = {"172.3.56.25"})
    public void byIpTestRussia(String argument) {
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        GeoService geoService = new GeoServiceImpl();
        Location testResult = geoService.byIp(argument);
        Assertions.assertEquals(location.getCountry(), testResult.getCountry());
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.3.56.25"})
    public void byIpTestUSA(String argument) {
        Location location = new Location("New York", Country.USA, " 10th Avenue", 32);
        GeoService geoService = new GeoServiceImpl();
        Location testResult = geoService.byIp(argument);
        Assertions.assertEquals(location.getCountry(), testResult.getCountry());
    }
}
