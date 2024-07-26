package com.app.TicketUK.service;

import static org.mockito.Mockito.*;

import com.app.TicketUK.dto.*;
import com.app.TicketUK.model.Destination;
import com.app.TicketUK.model.User;
import com.app.TicketUK.repository.SegmentRepository;
import com.app.TicketUK.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the TravelService class.
 */
public class TravelServiceTest {

    @Mock
    private SegmentRepository segmentRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserDataService userDataService;

    @InjectMocks
    private TravelService travelService;

    /**
     * Sets up the mock objects before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the getSegments method of TravelService.
     */
    @Test
    public void testGetSegments() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setDeparture("CityA");
        destination.setArrival("CityB");
        destination.setSegmentCount(2);

        List<Destination> destinations = new ArrayList<>();
        destinations.add(destination);

        when(segmentRepository.findAll()).thenReturn(destinations);

        // Act
        List<DestinationDto> segments = travelService.getSegments();

        // Assert
        assertNotNull(segments);
        assertEquals(1, segments.size());
        assertEquals("CityA", segments.get(0).getDeparture());
    }

    /**
     * Tests the addSegment method of TravelService.
     */
    @Test
    public void testAddSegment() {
        // Arrange
        DestinationDto destinationDto = new DestinationDto(1L, "CityA", "CityB", 2);
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setDeparture("CityA");
        destination.setArrival("CityB");
        destination.setSegmentCount(2);

        when(segmentRepository.save(any(Destination.class))).thenReturn(destination);

        // Act
        DestinationDto result = travelService.addSegment(destinationDto);

        // Assert
        assertNotNull(result);
        assertEquals("CityA", result.getDeparture());
        assertEquals(2, result.getSegmentCount());
    }

    /**
     * Tests the calculateOptimalTravelCost method of TravelService.
     */
    @Test
    public void testCalculateOptimalTravelCost() {
        // Arrange
        Destination destination = new Destination();
        destination.setDeparture("CityA");
        destination.setArrival("CityB");
        destination.setSegmentCount(2);

        List<Destination> destinations = new ArrayList<>();
        destinations.add(destination);

        when(segmentRepository.findAll()).thenReturn(destinations);

        TicketSearchDto ticketSearchDto = new TicketSearchDto("CityA", "CityB");

        // Act
        TicketWithPriceDto result = travelService.calculateOptimalTravelCost(ticketSearchDto);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalSegments());
        assertEquals(7, result.getPrice());
        assertEquals("GBP", result.getCurrency());
    }

    /**
     * Tests the saveTicket method of TravelService for a successful purchase.
     */
    @Test
    public void testSaveTicketSuccess() {
        // Arrange
        TicketDto ticketDto = new TicketDto("CityA", "CityB", 2, 15, "GBP", 20, "John Doe");

        User mockUser = new User();
        mockUser.setUserId(1L);
        when(userDataService.getAuthenticatedUserID()).thenReturn(mockUser);

        // Act
        TicketPurchase result = travelService.saveTicket(ticketDto);

        // Assert
        assertTrue(result instanceof PurchaseSuccessDto);
        PurchaseSuccessDto successDto = (PurchaseSuccessDto) result;
        assertEquals("Success", successDto.getResult());
        assertEquals(5, successDto.getChange());
    }

    /**
     * Tests the saveTicket method of TravelService for a failed purchase.
     */
    @Test
    public void testSaveTicketFailure() {
        // Arrange
        TicketDto ticketDto = new TicketDto("CityA", "CityB", 2, 20, "GBP", 15, "John Doe");

        User mockUser = new User();
        mockUser.setUserId(1L);
        when(userDataService.getAuthenticatedUserID()).thenReturn(mockUser);

        // Act
        TicketPurchase result = travelService.saveTicket(ticketDto);

        // Assert
        assertTrue(result instanceof PurchaseFailureDto);
        PurchaseFailureDto failureDto = (PurchaseFailureDto) result;
        assertEquals("Failure", failureDto.getResult());
        assertEquals(5, failureDto.getLackOf());
    }
}