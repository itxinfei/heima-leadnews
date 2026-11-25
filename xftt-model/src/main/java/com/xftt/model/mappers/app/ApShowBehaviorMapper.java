package com.xftt.model.mappers.app;

import com.xftt.model.behavior.pojos.ApShowBehavior;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApShowBehaviorMapper {
    List<ApShowBehavior> selectListByEntryIdAndArticleIds(@Param("entryId") Integer id, @Param("articleIds")Integer[] articleIds);

    void saveShowBehavior(@Param("articleIds") Integer[] articleIds, @Param("entryId") Integer entryId);

    /** 单条插入展示行为（主键由 ZK 序列生成） */
    int insert(ApShowBehavior behavior);
}
