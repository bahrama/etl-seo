package model.list;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CjProductList {
    private Integer code;
    private Boolean result;
    private String message;
    private Data data;
    private String requestId;
    private Boolean success;
}
