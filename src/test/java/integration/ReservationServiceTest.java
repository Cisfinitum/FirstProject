package integration;

import com.epam.model.Reservation;
import com.epam.model.ReservationStatusEnum;
import com.epam.repository.ReservationDAO;
import com.epam.service.ReservationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ReservationServiceTest {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationDAO reservationDAO;

    private int testPage = 1;
    private int testTotal = 5;

    @Test
    public void getAll() {

        List<Reservation> actualReservations = reservationService.listReservations(testPage, testTotal);
        Assert.assertNotEquals(0, actualReservations.size());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void removeReservation() {
        reservationDAO.addReservation(new Reservation(10, 1, 1, 5, ReservationStatusEnum.PAID, 10, 20));
        List<Reservation> reservations = reservationDAO.listReservations(testPage, testTotal);
        List<Reservation> reservationsFromDb = reservations.stream().filter(reservation -> reservation.getNumberOfPeople().equals(5)).collect(Collectors.toList());
        Integer reservationId = reservationsFromDb.get(0).getId();
        reservationService.removeReservation(reservationId);
        reservationDAO.getReservationById(reservationId);
    }

}
