package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferProductVariant{

    private Long id;
    private String vid;
    private String pid;
    private String variantNameEn;
    private String variantImage;
    private String variantSku;
    private String variantUnit;
    private String variantProperty;
    private String variantKey;
    private Integer variantLength;
    private Integer variantWidth;
    private Integer variantHeight;
    private Integer variantVolume;
    private Double variantWeight;
    private Double variantSellPrice;
    private String createTime;
    private String variantStandard;
    private Double variantSugSellPrice;

    private Long offerProductId;
}
