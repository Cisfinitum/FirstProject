package integration;

import com.epam.model.Hotel;
import com.epam.service.HotelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Before
    public void setUp() {
        hotelService.createHotel(new Hotel(1, "test", "test", "Russia", 5));
    }


    @Test
    public void getHotels() {
        List<Hotel> actualHotels = hotelService.getHotels();

        Assert.assertNotEquals(0, actualHotels.size());
        Assert.fail("GetHotels method cant get any information from the database");
    }

    @Test
    public void getHotelById() {
        Hotel actualHotel = hotelService.getHotelById(1);

        Assert.assertEquals("Russia", actualHotel.getCountry());
        Assert.fail("Hotel with id = 1 was not found");
    }

    @Test
    public void getHotelsByCountry() {
        List<Hotel> actualHotels = hotelService.getHotelsByCountry("Russia");

        Assert.assertEquals(1, actualHotels.size());
        Assert.fail("Hotel with country = Russia was not found");
    }

    @Test
    public void createHotel() {
        hotelService.createHotel(new Hotel(20, "some_hotel", "some_city", "Latvia", 5));

        List<Hotel> actualHotel = hotelService.getHotelsByCountry("Latvia");
        Assert.assertNotNull(actualHotel);
        Assert.fail("Hotel was not created");
    }

    @Test
    public void deleteHotel() {
        hotelService.createHotel(new Hotel(20, "some_hotel", "some_city", "Greece", 5));
        List<Hotel> hotelsFromDb = hotelService.getHotelsByCountry("Greece");

        hotelService.deleteHotel(hotelsFromDb.get(0).getId());

        List<Hotel> greeceHotels = hotelService.getHotelsByCountry("Greece");
        Assert.assertEquals(0, greeceHotels.size());
        Assert.fail("Hotel was not deleted");
    }

    @Test
    public void updateHotel() {
        hotelService.createHotel(new Hotel(20, "some_hotel", "some_city", "Norway", 5));
        List<Hotel> hotelsFromDb = hotelService.getHotelsByCountry("Norway");

        Hotel norwayHotel = hotelsFromDb.get(0);
        norwayHotel.setNumberOfStars(3);
        hotelService.updateHotel(norwayHotel);

        List<Hotel> actualHotel = hotelService.getHotelsByCountry("Norway");
        Integer numberOfStars = actualHotel.get(0).getNumberOfStars();
        Assert.assertEquals(Integer.valueOf(3), numberOfStars);
        Assert.fail("Hotel was not updated");
    }


}
