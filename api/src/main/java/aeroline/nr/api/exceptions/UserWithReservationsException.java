package aeroline.nr.api.exceptions;

public class UserWithReservationsException extends RuntimeException {
    public UserWithReservationsException() {
        super("Este usuario tiene reservas y no se puede eliminar");
    }
}
