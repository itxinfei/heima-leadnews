package com.xftt.model.mappers.app;

import com.xftt.model.article.pojos.ApCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApCollectionMapper {
    /**
     * 选择一个终端的收藏数据
     * @return
     */
    ApCollection selectForEntryId(@Param("burst") String burst, @Param("objectId")Integer objectId, @Param("entryId")Integer entryId, @Param("type")Short type);

    /** 收藏列表分页（BE-F03），type：0 文章 / 1 动态 */
    List<ApCollection> selectCollectionsByEntry(@Param("burst") String burst, @Param("objectId") Integer objectId,
                                                @Param("type") Short type, @Param("offset") int offset, @Param("size") int size);

    int countCollectionsByEntry(@Param("burst") String burst, @Param("objectId") Integer objectId, @Param("type") Short type);
}