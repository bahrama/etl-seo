package model.product;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class CustomVariant implements Serializable {
    private String vid;
    private String pid;
    private String variantNameEn;
    private Double variantSellPrice;
    private Integer count;
    private String shippingCountry;
    private String variantType;
}
