package com.xftt.common.hbase;

import com.xftt.common.common.storage.StorageData;
import com.xftt.common.hbase.entity.HBaseStorage;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class HBaseStorageClient {

    @Autowired
    private HBaseClent hBaseClent;

    public void addHBaseStorage(final String tableName, List<HBaseStorage> hBaseStorageList) {
        if (null != hBaseStorageList && !hBaseStorageList.isEmpty()) {
            hBaseStorageList.forEach(hBaseStorage -> addHBaseStorage(tableName, hBaseStorage));
        }
    }

    public void addHBaseStorage(String tableName, HBaseStorage hBaseStorage) {
        if (null != hBaseStorage && StringUtils.isNotEmpty(tableName)) {
            hBaseClent.creatTable(tableName, hBaseStorage.getColumnFamily());
            String rowKey = hBaseStorage.getRowKey();
            List<StorageData> storageDataList = hBaseStorage.getDataList();
            boolean result = addStorageData(tableName, rowKey, storageDataList);
            if (result) {
                hBaseStorage.invok();
            }
        }
    }

    public boolean addStorageData(String tableName, String rowKey, List<StorageData> storageDataList) {
        long currentTime = System.currentTimeMillis();
        log.info("开始添加StorageData到Hbase,tableName:{},rowKey:{}", tableName, rowKey);
        if (null != storageDataList && !storageDataList.isEmpty()) {
            storageDataList.forEach(hBaseData -> {
                String columnFamliyName = hBaseData.getTargetClassName();
                String[] columnArray = hBaseData.getColumns();
                String[] valueArray = hBaseData.getValues();
                if (null != columnArray && null != valueArray) {
                    hBaseClent.putData(tableName, rowKey, columnFamliyName, columnArray, valueArray);
                }
            });
        }
        log.info("添加StorageData到Hbase完成,tableName:{},rowKey:{},duration:{}", tableName, rowKey, System.currentTimeMillis() - currentTime);
        return true;
    }

    public <T> T getStorageDataEntity(String tableName, String rowKey, Class<T> tClass) {
        T tValue = null;
        if (StringUtils.isNotEmpty(tableName)) {
            StorageData hBaseData = hBaseClent.getStorageData(tableName, rowKey, tClass.getName());
            if (null != hBaseData) {
                tValue = (T) hBaseData.getObjectValue();
            }
        }
        return tValue;
    }

    public List<Object> getStorageDataEntityList(String tableName, String rowKey, List<Class> typeList) {
        List<Object> entityList = new ArrayList<Object>();
        List<String> strTypeList = typeList.stream().map(Class::getName).collect(Collectors.toList());
        List<StorageData> storageDataList = hBaseClent.getStorageDataList(tableName, rowKey, strTypeList);
        for (StorageData storageData : storageDataList) {
            entityList.add(storageData.getObjectValue());
        }
        return entityList;
    }

    public HBaseClent gethBaseClent() {
        return hBaseClent;
    }
}
