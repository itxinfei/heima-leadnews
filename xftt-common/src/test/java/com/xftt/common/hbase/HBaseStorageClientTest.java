package com.xftt.common.hbase;

import com.xftt.common.common.storage.StorageData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * HBaseStorageClient.addStorageData 单元测试：验证 HBase put 失败时返回 false（避免误回调 sync_status=true）。
 */
@ExtendWith(MockitoExtension.class)
class HBaseStorageClientTest {

    @Mock private HBaseClent hBaseClent;
    @InjectMocks private HBaseStorageClient client;

    private StorageData sd() {
        StorageData s = mock(StorageData.class);
        when(s.getTargetClassName()).thenReturn("cf");
        when(s.getColumns()).thenReturn(new String[] { "c" });
        when(s.getValues()).thenReturn(new String[] { "v" });
        return s;
    }

    @Test
    void addStorageData_putThrows_returnsFalse() {
        doThrow(new RuntimeException("hbase down")).when(hBaseClent)
                .putData(anyString(), anyString(), anyString(), any(String[].class), any(String[].class));
        List<StorageData> list = Collections.singletonList(sd());
        assertFalse(client.addStorageData("t", "rk", list));
    }

    @Test
    void addStorageData_emptyList_returnsTrue() {
        assertTrue(client.addStorageData("t", "rk", Collections.emptyList()));
    }

    @Test
    void addStorageData_ok_returnsTrue() {
        List<StorageData> list = Arrays.asList(sd(), sd());
        assertTrue(client.addStorageData("t", "rk", list));
    }
}
