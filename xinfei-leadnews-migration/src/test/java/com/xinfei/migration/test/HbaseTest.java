package com.xinfei.migration.test;


import com.xinfei.common.common.storage.StorageData;
import com.xinfei.common.common.storage.StorageEntity;
import com.xinfei.common.common.storage.StorageEntry;
import com.xinfei.common.hbase.HBaseClent;
import com.xinfei.common.hbase.HBaseStorageClient;
import com.xinfei.common.hbase.constants.HBaseConstants;
import com.xinfei.common.hbase.entity.HBaseStorage;
import com.xinfei.model.article.pojos.ApArticle;
import org.apache.hadoop.hbase.client.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class HbaseTest {

    @Autowired
    private HBaseClent hBaseClent;

    @Test
    public void testCreateTable(){
        List<String> columnFamily = new ArrayList<>();
        columnFamily.add("test_cloumn_family1");
        columnFamily.add("test_cloumn_family2");
        boolean ret = hBaseClent.creatTable("hbase_test_table_name", columnFamily);
    }

    @Test
    public void testDelTable(){
        hBaseClent.deleteTable(HBaseConstants.APARTICLE_QUANTITY_TABLE_NAME);
    }

    @Test
    public void testSaveData(){
        //String tableName, String rowKey, String familyName, String[] columns, String[] values
        String []columns ={"name","age"};
        String [] values = {"zhangsan","28"};
        hBaseClent.putData("hbase_test_table_name","test_row_key_001","test_cloumn_family1",columns,values);
    }

    @Test
    public void testFindByRowKey(){
        Result hbaseResult = hBaseClent.getHbaseResult("hbase_test_table_name", "test_row_key_001");
        System.out.println(hbaseResult);
    }

    @Autowired
    private HBaseStorageClient storageClient;

    @Test
    public void testStorageSaveData(){
        HBaseStorage storage = new HBaseStorage();
        storage.setRowKey("storage_row_key_00001");

        List<StorageData> dataList = new ArrayList<StorageData>();
        StorageData storageData = new StorageData();
        storageData.setTargetClassName("com.xinfei.model.article.pojos.ApArticle");

        List<StorageEntry> entryList = new ArrayList<StorageEntry>();
        StorageEntry entry = new StorageEntry();
        entry.setKey("title");
        entry.setValue("数据迁移");
        StorageEntry entry2 = new StorageEntry();
        entry2.setKey("id");
        entry2.setValue("123456");
        entryList.add(entry);
        entryList.add(entry2);
        storageData.setEntryList(entryList);

        dataList.add(storageData);
        storage.setDataList(dataList);

        storageClient.addHBaseStorage("hbase_storage_test_table_name",storage);
    }

    @Test
    public void testStorageGet(){
        ApArticle apArticle = storageClient.getStorageDataEntity("hbase_storage_test_table_name", "storage_row_key_00001", ApArticle.class);
        System.out.println(apArticle);
    }

}
