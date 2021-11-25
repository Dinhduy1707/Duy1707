package constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum StatusCode {
    UNDEFINED_ERROR("-101"),
    SUCCESS("000"),
    FAILURE("101"),
    INCORRECT_FORMAT("401"),
    TOKEN_KEY_EXISTED("410"),
    ;
    private final String code;

}
