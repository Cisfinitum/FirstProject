package integration;

import com.epam.model.TourOffer;
import com.epam.repository.TourOfferDAO;
import com.epam.service.TourOfferService;
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

    @Test
    public void deleteTour() {

    }
}
