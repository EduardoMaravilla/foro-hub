package org.maravill.foro_hub.security.config.docs;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.maravill.foro_hub.security.dto.ResponseSecurityApiError;
import org.springframework.http.MediaType;

@ApiResponses(value = {
        @ApiResponse(
                responseCode = HttpStatusCode.UNAUTHORIZED,
                description = HttpStatusCode.UNAUTHORIZED_VALUE,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseSecurityApiError.class)
                )
        ),
        @ApiResponse(
                responseCode = HttpStatusCode.BAD_REQUEST,
                description = HttpStatusCode.BAD_REQUEST_VALUE,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseSecurityApiError.class)
                )
        ),
        @ApiResponse(
                responseCode = HttpStatusCode.FORBIDDEN,
                description = HttpStatusCode.FORBIDDEN_VALUE,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseSecurityApiError.class)
                )
        ),
        @ApiResponse(
                responseCode = HttpStatusCode.NOT_FOUND,
                description = HttpStatusCode.NOT_FOUND_VALUE,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseSecurityApiError.class)
                )
        ),
        @ApiResponse(
                responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
                description = HttpStatusCode.INTERNAL_SERVER_ERROR_VALUE,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseSecurityApiError.class)
                )
        )
})
public interface DefaultSecurityApiResponses {
}
