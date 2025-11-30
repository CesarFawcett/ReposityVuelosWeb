package aeroline.nr.api.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import aeroline.nr.api.api.Dto.BookingRequestDto;
import aeroline.nr.api.api.Dto.BookingResponseDto;
import aeroline.nr.api.api.Dto.BookingStatusUpdateDto;
import aeroline.nr.api.services.BookingService;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        List<BookingResponseDto> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDto>> getUserBookings(@PathVariable int userId) {
        List<BookingResponseDto> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable int id) {
        return bookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<BookingResponseDto> getBookingByReference(@PathVariable String reference) {
        return bookingService.getBookingByReference(reference)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDto requestDto) {
        try {
            BookingResponseDto booking = bookingService.createBooking(requestDto);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateBookingStatus(
            @PathVariable int id,
            @RequestBody BookingStatusUpdateDto statusDto) {
        try {
            BookingResponseDto booking = bookingService.updateBookingStatus(id, statusDto.getStatus());
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable int id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}