package integration;

import com.epam.exception.NotFoundException;
import com.epam.model.TourOffer;
import com.epam.repository.TourOfferDAO;
import com.epam.service.TourOfferService;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TourOfferServiceTest {

    private TourOfferService tourOfferService;

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private TourOffer tourOffer;

    private List<TourOffer> tourOffers;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        tourOfferService = new TourOfferService(new TourOfferDAO(jdbcTemplate));
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
        int tourOfferId = 10;
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), (Integer) Mockito.any())).thenReturn(1);

        int actualReturnCode = tourOfferService.deleteTour(tourOfferId);
        Assert.assertEquals(1, actualReturnCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourNull() {
        tourOfferService.addTour(null);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void addTourOfferWrongHotelId() {
//        Mockito.when(tourOffer.getHotelId()).thenReturn(0);
//
//        tourOfferService.addTour(tourOffer);
//
//        Mockito.verifyZeroInteractions(jdbcTemplate);
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void addTourOfferWrongTourType() {
//        Mockito.when(tourOffer.getTourType()).thenReturn("");
//
//        tourOfferService.addTour(tourOffer);
//
//        Mockito.verifyZeroInteractions(jdbcTemplate);
//    }

//    @Test(expected = NotFoundException.class)
//    public void updateTourNotFoundInTheDatabase() {
//        Mockito.when(tourOffer.getId()).thenReturn(1);
//        Mockito.when(tourOffer.getHotelId()).thenReturn(2);
//        Mockito.when(jdbcTemplate.update(Mockito.anyString(), (Object[]) Mockito.any())).thenReturn(0);
//
//        tourOfferService.updateTour(tourOffer);
//    }

    @Test
    public void updateTour() {
        Mockito.when(tourOffer.getId()).thenReturn(1);
        Mockito.when(tourOffer.getHotelId()).thenReturn(1);
        Mockito.when(tourOffer.getTourType()).thenReturn("dd");
        Mockito.when(tourOffer.getDiscountId()).thenReturn(1);
        Mockito.when(tourOffer.getEndDate()).thenReturn(LocalDate.of(2019, 10, 11));

        tourOfferService.updateTour(tourOffer);

        Mockito.verify(jdbcTemplate, Mockito.times(1)).update("UPDATE tourOffer SET " +
                "tourType = ?, startDate = ?, endDate = ?, pricePerUnit = ?, hotel_id = ?, description = ?, discount_id = ? WHERE id = ?",
            tourOffer.getTourType(), tourOffer.getStartDate(), tourOffer.getEndDate(), tourOffer.getPricePerUnit(), tourOffer.getHotelId(),
            tourOffer.getDescription(), tourOffer.getDiscountId(), tourOffer.getId());
    }
}
