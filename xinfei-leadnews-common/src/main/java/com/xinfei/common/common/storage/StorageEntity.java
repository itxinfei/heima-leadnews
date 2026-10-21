package com.xinfei.common.common.storage;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个存储的实体
 */
@Setter
@Getter
public class StorageEntity {

    /**
     * 存储类型的列表
     * 一个实体可以存储多个数据列表
     */
    private List<StorageData> dataList = new ArrayList<StorageData>();

    /**
     * 获取存储数据列表（显式实现，避免某些 lombok 配置下注解处理器未生效）
     */
    public List<StorageData> getDataList() {
        return dataList;
    }

    /**
     * 设置存储数据列表
     */
    public void setDataList(List<StorageData> dataList) {
        this.dataList = dataList;
    }

    /**
     * 添加一个存储数据
     * @param storageData
     */
    public void addStorageData(StorageData storageData) {
        dataList.add(storageData);
    }
}
