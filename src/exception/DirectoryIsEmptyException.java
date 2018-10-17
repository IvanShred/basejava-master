package exception;

public class DirectoryIsEmptyException extends RuntimeException {
    public DirectoryIsEmptyException(String message) {
        super(message);
    }
}
