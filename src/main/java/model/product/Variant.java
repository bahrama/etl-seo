package model.product;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class Variant implements Serializable {
    private String vid;
    private String pid;
    private String variantName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variant)) return false;
        Variant variant = (Variant) o;
        return Objects.equals(getVid(), variant.getVid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVid());
    }
}
