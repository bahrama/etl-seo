package model.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Data {
    private String pid;
    private String productName;
    private List<String> productNameSet;
    private String productNameEn;
    private String productSku;
    private String productImage;
    private List<String> productImageSet;
    private String productWeight;
    private Object productUnit;
    private String productType;
    private String categoryId;
    private String categoryName;
    private String entryCode;
    private String entryName;
    private String entryNameEn;
    private String materialName;
    private List<String> materialNameSet;
    private String materialNameEn;
    private List<String> materialNameEnSet;
    private String materialKey;
    private List<String> materialKeySet;
    private String packingWeight;
    private String packingName;
    private List<String> packingNameSet;
    private String packingNameEn;
    private List<String> packingNameEnSet;
    private String packingKey;
    private List<String> packingKeySet;
    private String productKey;
    private List<String> productKeySet;
    private String productKeyEn;
    private String productPro;
    private List<String> productProSet;
    private String productProEn;
    private List<String> productProEnSet;
    private String sellPrice;
    private Integer sourceFrom;
    private String description;
    private List<Variant> variants;
    private Integer addMarkStatus;
    private Date createrTime;
    private String productVideo;
    private String status;
    private String suggestSellPrice;
    private Integer listedNum;
    private String supplierName;
    private String supplierId;
}
