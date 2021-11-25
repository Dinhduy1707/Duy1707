package constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusMessage {
    SUCCESS("success"),
    TOKEN_KEY_FORMAT_INCORRECT("token key format_incorrect"),
    TOKEN_KEY_EXISTED("token_key_existed"),
    API_ID_NOT_BLANK("api_id_not_blank"),
    PAYDATE_FORMAT_INCORRECT("paydate_format_incorrect"),
    ORDER_CODE_NOT_BLANK("order_code_not_blank"),
    AMOUNT_MONEY_ERROR("amount_money_error"),
    PROMOTION_CODE_NOT_NULL("promotion_code_not_null"),
    UNDEFINED_ERROR("undefined error");

    private final String description;


}
