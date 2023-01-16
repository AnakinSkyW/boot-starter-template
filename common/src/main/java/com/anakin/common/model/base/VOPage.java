package com.anakin.common.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 带分页数据VO
 * @author: Anakin
 * @date 23.1.16 16:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VOPage<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private long totalNum;
    private List<T> dataList;

    public VOPage(Integer pageNum, Integer pageSize, Long totalNum, List<T> dataList) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.dataList = dataList;
        if (totalNum == 0) {
            totalPage = 0;
        }
        if (totalNum <= pageSize) {
            totalPage = 1;
        }
        if (totalNum > pageSize && totalNum%pageSize == 0) {
            totalPage = (int)(totalNum/pageSize);
        }
        if (totalNum > pageSize && totalNum%pageSize > 0) {
            totalPage = (int)(totalNum/pageSize) + 1;
        }
    }

}
