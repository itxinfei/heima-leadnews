package com.xftt.article.stream;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * HotArticleStreamHandler.accumulate 单元测试（反射调私有静态方法）。
 * 验证同窗口多条行为的累加正确性（修复原聚合丢数据问题）。
 */
class HotArticleStreamHandlerTest {

    private String accumulate(String aggValue, String value) throws Exception {
        Method m = HotArticleStreamHandler.class.getDeclaredMethod("accumulate", String.class, String.class);
        m.setAccessible(true);
        return (String) m.invoke(null, aggValue, value);
    }

    @Test
    void accumulate_sameTypeAccumulates() throws Exception {
        assertEquals("COLLECTION:0,COMMENT:0,LIKES:4,VIEWS:2",
                accumulate("COLLECTION:0,COMMENT:0,LIKES:1,VIEWS:2", "LIKES:3"));
    }

    @Test
    void accumulate_multiRoundKeepsPrior() throws Exception {
        // 窗口内多条：先加 COLLECTION，再加 COMMENT、VIEWS，累加器现值必须被保留累加
        String s1 = accumulate("COLLECTION:1,COMMENT:0,LIKES:0,VIEWS:0", "COLLECTION:2");
        assertEquals("COLLECTION:3,COMMENT:0,LIKES:0,VIEWS:0", s1);
        String s2 = accumulate(s1, "COMMENT:1");
        assertEquals("COLLECTION:3,COMMENT:1,LIKES:0,VIEWS:0", s2);
        String s3 = accumulate(s2, "VIEWS:5");
        assertEquals("COLLECTION:3,COMMENT:1,LIKES:0,VIEWS:5", s3);
    }

    @Test
    void accumulate_nullAgg_startsFromZero() throws Exception {
        assertEquals("COLLECTION:0,COMMENT:0,LIKES:0,VIEWS:5", accumulate(null, "VIEWS:5"));
    }

    @Test
    void accumulate_invalidValue_treatedAsZero() throws Exception {
        assertEquals("COLLECTION:0,COMMENT:0,LIKES:0,VIEWS:0",
                accumulate("COLLECTION:0,COMMENT:0,LIKES:0,VIEWS:0", "LIKES:null"));
    }
}
