package model.list;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Data {
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
    private List<ListCj> list;
}
