package mmdev.dto.response;

public record ErrorResponse(
        int status,
        String message
) {
}
