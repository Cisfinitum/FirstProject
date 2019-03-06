package com.epam.service;

import com.epam.exception.NotFoundException;
import com.epam.model.*;
import com.epam.repository.TourOfferDAO;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class TourOfferServiceTest {
    @Mock
    private TourOfferDAO tourOfferDAO;
    @Mock
    private TourOffer expectedTourOffer;
    @Mock
    private HotelService hotelService;
    @Mock
    private ReservationService reservationService;
    @Mock
    private Hotel expectedHotel;
    @Mock
    private Reservation expectedReservation;

    private List<TourOffer> tourOfferList;

    private TourOfferService tourOfferService;

    private List<Integer> expectedHotelsId;

    private List<Hotel> expectedHotels;

    private List<Reservation> expectedReservations;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tourOfferService = new TourOfferService(tourOfferDAO, hotelService,reservationService);
        tourOfferList = new ArrayList<>();
        expectedHotels = new ArrayList<>();
        expectedHotels.add(expectedHotel);
        expectedReservations = new ArrayList<>();
        expectedReservations.add(expectedReservation);
        when(hotelService.getHotelsByCountry("test")).thenReturn(expectedHotels);
        expectedHotelsId = new ArrayList<>();
        for(Hotel hotel: expectedHotels){
            expectedHotelsId.add(hotel.getId());
        }
        when(expectedTourOffer.getTourType()).thenReturn("test");
        when(expectedTourOffer.getPricePerUnit()).thenReturn(1);
        when(expectedTourOffer.getHotelId()).thenReturn(1);
        when(expectedTourOffer.getId()).thenReturn(1);
        when(expectedTourOffer.getDescription()).thenReturn("test");
        when(expectedTourOffer.getDiscount()).thenReturn(1);
        when(expectedTourOffer.getStartDate()).thenReturn(LocalDate.now());
        when(expectedTourOffer.getEndDate()).thenReturn(LocalDate.now());
        when(expectedHotel.getId()).thenReturn(1);
        when(expectedHotel.getName()).thenReturn("test");
        when(expectedHotel.getCountry()).thenReturn("test");
        when(expectedHotel.getNumberOfStars()).thenReturn(1);
        when(expectedHotel.getCity()).thenReturn("test");
    }

    @Test
    public void deleteTourCheck() {
        when(tourOfferDAO.deleteTour(1)).thenReturn(1);
        assertEquals(tourOfferService.deleteTour(1),1);
    }

    @Test
    public void getToursCheck() {
        when(tourOfferDAO.getTours()).thenReturn(tourOfferList);
        assertEquals(tourOfferService.getTours(),tourOfferList);
    }

    @Test
    public void addTourCheck(){
        when(tourOfferDAO.addTour(expectedTourOffer)).thenReturn(1);
        assertEquals(tourOfferService.addTour(expectedTourOffer),1);
    }

    @Test
    public void updateTourCheck(){
        when(tourOfferDAO.updateTour(expectedTourOffer)).thenReturn(1);
        assertEquals(tourOfferService.updateTour(expectedTourOffer,"test",1,1,"test"),1);
    }

    @Test
    public void searchTourCheck(){
        List<Hotel> expectedHotels = new ArrayList<>();
        expectedHotels.add(expectedHotel);
        when(hotelService.getHotelsByCountry("test")).thenReturn(expectedHotels);
        List<Integer> expectedHotelsId = new ArrayList<>();
        for(Hotel hotel: expectedHotels){
            expectedHotelsId.add(hotel.getId());
        }
        when(tourOfferDAO.searchTours(expectedHotelsId,LocalDate.now(),LocalDate.now(),1,5)).thenReturn(tourOfferList);
        assertEquals(tourOfferService.searchTours("test",LocalDate.now(),LocalDate.now(),1),tourOfferList);
    }

    @Test
    public void getTourByIdCheck(){
        when(tourOfferDAO.getTourById(1)).thenReturn(expectedTourOffer);
        assertEquals(tourOfferService.getTourById(1),expectedTourOffer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteTourThrowExceptionNull(){
        when(tourOfferService.deleteTour(null)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteTourThrowExceptionZero(){
        when(tourOfferService.deleteTour(0)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourThrowExceptionTourIsNull(){
        when(tourOfferService.addTour(null)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowException(){
        when(tourOfferService.updateTour(null,"test",1,1,"test")).thenThrow(NullPointerException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTourByIdThrowExceptionNull(){
        when(tourOfferService.getTourById(null)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTourByIdThrowExceptionZero(){
        when(tourOfferService.getTourById(0)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionTourType(){
        when(tourOfferService.updateTour(expectedTourOffer,null,1,1,"test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionReservationExist(){
        when(reservationService.getTourOfferById(expectedTourOffer.getId())).thenReturn(1);
        when(tourOfferService.updateTour(expectedTourOffer,"test",1,1,"test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionPricePerPersonNull(){
        when(tourOfferService.updateTour(expectedTourOffer,"test",null,1,"test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionPricePerPersonZero(){
        when(tourOfferService.updateTour(expectedTourOffer,"test",0,1,"test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionDiscountNull(){
        when(tourOfferService.updateTour(expectedTourOffer,"test",1,null,"test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionDiscountNegative(){
        when(tourOfferService.updateTour(expectedTourOffer,"test",1,-100,"test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionTourDescriptiontNull(){
        when(tourOfferService.updateTour(expectedTourOffer,"test",1,1,null)).thenThrow(IllegalArgumentException.class);
    }

    @Test
    public void getToursByPageCheck() {
        when(tourOfferDAO.getToursByPage(1, 4)).thenReturn(tourOfferList);
        assertEquals(tourOfferService.getToursByPage(1), tourOfferList);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getToursByPageThrowsIllegalArgumentExceptionForNullValue() {
        tourOfferService.getToursByPage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getToursByPageThrowsIllegalArgumentExceptionForIncorrectValue() {
        tourOfferService.getToursByPage(0);
    }

    @Test
    public void getAmountOfToursCheck() {
        when(tourOfferDAO.getAmountOfTours()).thenReturn(1);
        assertEquals(tourOfferService.getAmountOfTours(), 1);

    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 4, 1 }, { 5, 2 }, { 1, 1 }, { 0, 0 }
        });
    }
    @Parameterized.Parameter
    public int pInput;

    @Parameterized.Parameter(1)
    public int pExpected;
    @Test
    public void getNumberOfPagesCheck() {
        when(tourOfferDAO.getAmountOfTours()).thenReturn(pInput);
        assertEquals(pExpected, tourOfferService.getNumberOfPages());

    }

    @Test
    public void getToursByPage(){
        when(tourOfferDAO.getToursByPage(5, 4)).thenReturn(tourOfferList);
        assertEquals(tourOfferService.getToursByPage(2), tourOfferList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteTourThrowExceptionReservationExist(){
        when(reservationService.getTourOfferById(1)).thenReturn(1);
        when(tourOfferService.deleteTour(1)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourThrowExceptionTourTypeIsNull(){
        when(expectedTourOffer.getTourType()).thenReturn(null);
        when(tourOfferService.addTour(expectedTourOffer)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourThrowExceptionPriceIsNull(){
        when(expectedTourOffer.getPricePerUnit()).thenReturn(null);
        when(tourOfferService.addTour(expectedTourOffer)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourThrowExceptionHotelIsNull(){
        when(expectedTourOffer.getHotelId()).thenReturn(null);
        when(tourOfferService.addTour(expectedTourOffer)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourThrowExceptionIdIsNull(){
        when(expectedTourOffer.getId()).thenReturn(null);
        when(tourOfferService.addTour(expectedTourOffer)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourThrowExceptionDescriptionIsNull(){
        when(expectedTourOffer.getDescription()).thenReturn(null);
        when(tourOfferService.addTour(expectedTourOffer)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourThrowExceptionDiscountIsNull(){
        when(expectedTourOffer.getDiscount()).thenReturn(null);
        when(tourOfferService.addTour(expectedTourOffer)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourThrowExceptionStartDateIsNull(){
        when(expectedTourOffer.getStartDate()).thenReturn(null);
        when(tourOfferService.addTour(expectedTourOffer)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourThrowExceptionEndDateIsNull(){
        when(expectedTourOffer.getEndDate()).thenReturn(null);
        when(tourOfferService.addTour(expectedTourOffer)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchToursThrowExceptionPageIsZero(){
        when(tourOfferService.searchTours("test",LocalDate.now(),LocalDate.now(),0)).thenThrow(IllegalArgumentException.class);
    }

    @Test
    public void searchToursCountryNotFoundAndNotEmpty(){
        String countryInvalid = "NotFound";
        when(hotelService.getHotelsByCountry(countryInvalid)).thenReturn(new ArrayList<>());
        Assert.assertEquals(tourOfferService.searchTours(countryInvalid,LocalDate.now(),LocalDate.now(),1),new ArrayList<>());
    }

    @Test
    public void searchToursCountryNotFoundAndEmpty(){
        String countryInvalid = "";
        when(hotelService.getHotelsByCountry(countryInvalid)).thenReturn(new ArrayList<>());
        Assert.assertEquals(tourOfferService.searchTours(countryInvalid,LocalDate.now(),LocalDate.now(),1),new ArrayList<>());
    }

    @Test
    public void searchToursPageBiggerThanOne(){
        when(tourOfferDAO.searchTours(expectedHotelsId,LocalDate.now(),LocalDate.now(),6,5)).thenReturn(tourOfferList);
        Assert.assertEquals(tourOfferService.searchTours("test",LocalDate.now(),LocalDate.now(),2),tourOfferList);
    }

    @Test
    public void getNumberOfPagesSearchEqualToTotal(){
        Assert.assertEquals(tourOfferService.getNumberOfPagesSearch(5),1);
    }

    @Test
    public void getNumberOfPagesSearchBiggerThanTotal(){
        Assert.assertEquals(tourOfferService.getNumberOfPagesSearch(8),2);
    }

    @Test
    public void checkIfHotelUsed(){
        Assert.assertEquals(tourOfferService.checkIfHotelUsed(1),tourOfferDAO.checkIfHotelsIsUsed(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfHotelUsedThrowExceptionIfIntegerIsNull(){
        when(tourOfferService.checkIfHotelUsed(null)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfHotelUsedThrowExceptionIfIntegerIsNegativeOrZero(){
        when(tourOfferService.checkIfHotelUsed(-1)).thenThrow(IllegalArgumentException.class);
    }

    @Test
    public void getToursStatusMapIfReservationExist(){
        tourOfferList.add(expectedTourOffer);
        when(tourOfferService.getTours()).thenReturn(tourOfferList);
        when(reservationService.getTourOfferById(expectedTourOffer.getId())).thenReturn(1);
        Map<Integer,Boolean> actualToursStatusMap = new HashMap<>();
        actualToursStatusMap.put(expectedTourOffer.getId(),false);
        Assert.assertEquals(tourOfferService.getToursStatusMap(),actualToursStatusMap);
    }

    @Test
    public void getToursStatusMapIfReservationNotExist(){
        tourOfferList.add(expectedTourOffer);
        when(tourOfferService.getTours()).thenReturn(tourOfferList);
        when(reservationService.getTourOfferById(expectedTourOffer.getId())).thenReturn(0);
        Map<Integer,Boolean> actualToursStatusMap = new HashMap<>();
        actualToursStatusMap.put(expectedTourOffer.getId(),true);
        Assert.assertEquals(tourOfferService.getToursStatusMap(),actualToursStatusMap);
    }

    @Test
    public void getMapOfHotelUseIfHotelUsed(){
        when(expectedHotel.getId()).thenReturn(1);
        when(tourOfferService.checkIfHotelUsed(expectedHotel.getId())).thenReturn(1);
        Map<Integer,Boolean> actualMapOfHotels = new HashMap<>();
        actualMapOfHotels.put(expectedHotel.getId(),true);
        Assert.assertEquals(tourOfferService.getMapOfHotelUse(expectedHotels),actualMapOfHotels);
    }

    @Test
    public void getMapOfHotelUseIfHotelNotUsed(){
        when(expectedHotel.getId()).thenReturn(1);
        when(tourOfferService.checkIfHotelUsed(expectedHotel.getId())).thenReturn(0);
        Map<Integer,Boolean> actualMapOfHotels = new HashMap<>();
        actualMapOfHotels.put(expectedHotel.getId(),false);
        Assert.assertEquals(tourOfferService.getMapOfHotelUse(expectedHotels),actualMapOfHotels);
    }

    @Test
    public void getDescription(){
        when(expectedReservation.getTourOfferId()).thenReturn(1);
        when(tourOfferService.getTourById(1)).thenReturn(expectedTourOffer);
        when(hotelService.getHotelById(1)).thenReturn(expectedHotel);
        Map<Integer, String> actualMapOfDescription = new HashMap<>();
        actualMapOfDescription.put(expectedHotel.getId(),expectedTourOffer.toString()+expectedHotel.toString());
        Assert.assertEquals(tourOfferService.getDescription(expectedReservations),actualMapOfDescription);
    }

    @Test
    public void amountOfToursSearched(){
        Assert.assertEquals(tourOfferService.amountOfToursSearched("test", LocalDate.now(), LocalDate.now()),tourOfferDAO.amountOfToursSearched(expectedHotelsId, LocalDate.now(), LocalDate.now()));
    }
}

