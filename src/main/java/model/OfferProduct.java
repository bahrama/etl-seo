package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class OfferProduct{
    private Long id;
    private String pid;
    private String productNameEn;
    private String productSku;
    private String productImage;
    private String productAllImage;
    private String productWeight;
    private String productType;
    private String entryCode;
    private String entryNameEn;
    private String materialNameEn;
    private String materialKey;
    private String packingWeight;
    private String packingNameEn;
    private String packingKey;
    private String productKeyEn;
    private String productProEn;
    private String sellPrice;
    private Integer sourceFrom;
    private String description;
    private String title;
    private String shortDescription;
    private Integer addMarkStatus;
    private Date createrTime;
    private String productVideo;
    private String status;
    private String suggestSellPrice;
    private Integer listedNum;
    private String supplierName;
    private String supplierId;
    private Long offerID;

}
