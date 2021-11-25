package exception;

import constants.StatusCode;
import constants.StatusMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateException extends Exception {
    private String errorcode;

    public ValidateException(StatusCode code, StatusMessage message) {

        super(message.getDescription());
        this.errorcode = code.getCode();
    }
}
