package integration;

import com.epam.exception.NotFoundException;
import com.epam.model.TourOffer;
import com.epam.repository.ReservationDAO;
import com.epam.repository.TourOfferDAO;
import com.epam.service.HotelService;
import com.epam.service.ReservationService;
import com.epam.service.TourOfferService;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.exceptions.verification.NoInteractionsWanted;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TourOfferServiceTest {

    private TourOfferService tourOfferService;

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private TourOffer tourOffer;
    private TourOfferDAO tourOfferDAO;
    private List<TourOffer> tourOffers;
    private HotelService hotelService;
    @Mock
    private ReservationService reservationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tourOfferDAO = new TourOfferDAO(jdbcTemplate);
        tourOfferDAO = Mockito.spy(tourOfferDAO);
        ReflectionTestUtils.setField(tourOfferDAO, "tableName", "tour_offer");
        tourOfferService = new TourOfferService(tourOfferDAO,hotelService,reservationService);
        tourOffers = new ArrayList<>();
        tourOffers.add(tourOffer);

    }

    @Test
    public void getAll() {
        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(RowMapper.class))).thenReturn(tourOffers);
        List<TourOffer> actualTours = tourOfferService.getTours();
        Assert.assertEquals(tourOffer, actualTours.get(0));
    }

    @Test
    public void getAllEmptyList() {
        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(RowMapper.class))).thenReturn(Collections.emptyList());
        List<TourOffer> actualTours = tourOfferService.getTours();
        Assert.assertEquals(0, actualTours.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteTourNullId() {
        tourOfferService.deleteTour(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteTourZeroId() {
        tourOfferService.deleteTour(0);
    }

    @Test
    public void deleteTour() {
        int tourOfferId = 2;
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), (Integer) Mockito.any())).thenReturn(1);
        Mockito.when(reservationService.getTourOfferById(tourOfferId)).thenReturn(0);
        int actualReturnCode = tourOfferService.deleteTour(tourOfferId);
        Assert.assertEquals(1, actualReturnCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourNull() {
        tourOfferService.addTour(null);
    }

    @Test(expected = NoInteractionsWanted.class)
    public void addTourOfferWrongHotelId() {
        Mockito.when(tourOffer.getHotelId()).thenReturn(0);

        tourOfferService.addTour(tourOffer);

        Mockito.verifyZeroInteractions(jdbcTemplate);
    }
    @Test(expected = NoInteractionsWanted.class)
    public void addTourOfferWrongTourType() {
        Mockito.when(tourOffer.getTourType()).thenReturn("");

        tourOfferService.addTour(tourOffer);

        Mockito.verifyZeroInteractions(jdbcTemplate);
    }

    @Test(expected = NotFoundException.class)
    public void updateTourNotFoundInTheDatabase() {
        Mockito.when(tourOffer.getId()).thenReturn(1);
        Mockito.when(tourOffer.getHotelId()).thenReturn(2);
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), (Object[]) Mockito.any())).thenReturn(0);

        tourOfferService.updateTour(tourOffer,"test",1,1,"test");
    }

    @Test
    public void updateTour() {
        Mockito.when(tourOffer.getId()).thenReturn(1);
        Mockito.when(tourOffer.getHotelId()).thenReturn(1);
        Mockito.when(tourOffer.getTourType()).thenReturn("dd");
        Mockito.when(tourOffer.getDiscountId()).thenReturn(1);
        Mockito.when(tourOffer.getStartDate()).thenReturn(LocalDate.of(2019, 10, 6));
        Mockito.when(tourOffer.getEndDate()).thenReturn(LocalDate.of(2019, 10, 11));
        Mockito.when(tourOffer.getPricePerUnit()).thenReturn(1);
        Mockito.when(tourOffer.getDescription()).thenReturn("dd");
        Mockito.when(jdbcTemplate.update("UPDATE tour_offer SET " +
                        "tour_type = ?, start_date = ?, end_date = ?, price_per_unit = ?, hotel_id = ?, description = ?, discount_id = ? " +
                        "WHERE id = ?",
                tourOffer.getTourType(), tourOffer.getStartDate(), tourOffer.getEndDate(), tourOffer.getPricePerUnit(), tourOffer.getHotelId(),
                tourOffer.getDescription(), tourOffer.getDiscountId(), tourOffer.getId())).thenReturn(1);

        tourOfferService.updateTour(tourOffer,"test",1,1,"test");

        Mockito.verify(jdbcTemplate, Mockito.times(1)).update("UPDATE tour_offer SET " +
                "tour_type = ?, start_date = ?, end_date = ?, price_per_unit = ?, hotel_id = ?, description = ?, discount_id = ? " +
                "WHERE id = ?",
            tourOffer.getTourType(), tourOffer.getStartDate(), tourOffer.getEndDate(), tourOffer.getPricePerUnit(), tourOffer.getHotelId(),
            tourOffer.getDescription(), tourOffer.getDiscountId(), tourOffer.getId());
    }
}
