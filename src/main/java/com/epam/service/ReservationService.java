package com.epam.service;

import com.epam.model.Reservation;
import com.epam.model.ReservationStatusEnum;
import com.epam.repository.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationService {
    private final ReservationDAO reservationDAO;
    private final PersonService personService;
    public int totalAmountOfRows = 4;

    @Autowired
    public ReservationService(ReservationDAO reservationDAO, PersonService personService) {
        this.reservationDAO = reservationDAO;
        this.personService = personService;
    }


    public int addReservation(Reservation reservation) {
        if (reservation != null) {
            if (reservation.getClientId() != null && reservation.getStatus() != null) {
                return reservationDAO.addReservation(reservation);
            } else {
                throw new IllegalArgumentException("Some fields are empty");
            }
        } else {
            throw new NoSuchElementException("Such reservation does not exist.");
        }
    }

    Reservation getReservationById(Integer id) {
        if (id != null) {
            if (id > 0) {
                return reservationDAO.getReservationById(id);
            } else {
                throw new IllegalArgumentException("Id must be > 0");
            }
        } else {
            throw new IllegalArgumentException("Id must be specified.");
        }
    }

    public List<Reservation> getReservationsByPersonId(Integer personId) {
        if (personId != null) {
            if (personId > 0) {
                return reservationDAO.getReservationsByPersonId(personId);
            } else {
                throw new IllegalArgumentException("Id must be > 0");
            }
        } else {
            throw new IllegalArgumentException("Id must be specified.");
        }
    }

    public List<Reservation> listReservations(Integer page, Integer total) {

        if (page > 0 && total > 0) {
            if (page > 1) {
                page = (page - 1) * total + 1;
            }
            return reservationDAO.listReservations(page, total);
        } else {
            throw new IllegalArgumentException("Numbers must be integer and > 0");
        }
    }

    public int removeReservation(Integer id) {
        if (id != null) {
            if (id > 0) {
                return reservationDAO.removeReservation(id);
            } else {
                throw new IllegalArgumentException("Id must be > 0");
            }
        } else {
            throw new NoSuchElementException("Reservation with such id does not exist.");
        }
    }
    public int getTourOfferById(Integer tourOfferId) {
        if (tourOfferId != null) {
            if (tourOfferId > 0) {
                return reservationDAO.getTourOfferById(tourOfferId);
            } else {
                throw new IllegalArgumentException("Id must be > 0");
            }
        } else {
            throw new IllegalArgumentException("Tour offer with such id does not exist.");
        }
    }

    int updateReservation(Reservation reservation) {
        if (reservation != null) {
            if (reservation.getId() != null) {
                return reservationDAO.updateReservation(reservation);
            } else {
                throw new IllegalArgumentException("Id must be specified");
            }
        } else {
            throw new NoSuchElementException("There is no such reservation.");
        }
    }

    public int amountOfReservation() {
        return reservationDAO.amountOfReservations();
    }

    public int getTotalPrice(Integer numberOfPeople, Integer pricePerUnit, Integer discount) {
        if (numberOfPeople > 0 && pricePerUnit > 0 && discount > 0) {
            return pricePerUnit * numberOfPeople * discount/100;
        } else {
            throw new IllegalArgumentException("All arguments must be strictly more than zero");
        }
    }

    public ModelAndView reserveTour(ModelAndView modelAndView, Principal principal, Integer idOfTour, Integer pricePerUnit, Integer numberOfPeople, Integer discount, RedirectAttributes redirectAttributes){
        if (principal == null) {
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "Please, sign in.");
            return modelAndView;
        } else {
            String email = principal.getName();
            modelAndView.setViewName("homepage");
            Integer clientId = personService.getIdByEmail(email);
            Integer totalPrice = getTotalPrice(numberOfPeople, pricePerUnit, discount);
            Reservation reservation = new Reservation(clientId, idOfTour, numberOfPeople, ReservationStatusEnum.UNPAID, discount, totalPrice);
            addReservation(reservation);
            Integer reservationId = amountOfReservation();
            modelAndView.setViewName("redirect:/payment");
            redirectAttributes.addFlashAttribute("reservationId", reservationId);
            redirectAttributes.addFlashAttribute("pricePerUnit", pricePerUnit);
            redirectAttributes.addFlashAttribute("discount", discount);
            redirectAttributes.addFlashAttribute("totalPrice", totalPrice);
            redirectAttributes.addFlashAttribute("numberOfPeople", numberOfPeople);
            return modelAndView;
        }
    }

    public int changeReservationStatusById(Integer id) {
        if(id != null){
            if (id > 0){
                return reservationDAO.changeReservationStatusById(id);
            } else {
                throw new IllegalArgumentException("id must be positive");
            }
        } else {
            throw new NoSuchElementException("id must be specified");
        }
    }

}
