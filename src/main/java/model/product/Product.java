package model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Integer code;
    private Boolean result;
    private String message;
    private Data data;
    private String requestId;
    private Boolean success;
}
