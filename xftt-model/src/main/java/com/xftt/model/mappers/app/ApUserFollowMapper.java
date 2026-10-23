package com.xftt.model.mappers.app;

import com.xftt.model.user.pojos.ApUserFollow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApUserFollowMapper {
    ApUserFollow selectByFollowId(@Param("burst") String burst,@Param("userId") Long userId,@Param("followId") Integer followId);

    int insert(ApUserFollow record);;
    int deleteByFollowId(@Param("burst") String burst,@Param("userId")Long userId,@Param("followId")Integer followId);

    /** 关注列表分页（BE-F03） */
    List<ApUserFollow> selectFollowsByUserId(@Param("burst") String burst, @Param("userId") Long userId,
                                             @Param("offset") int offset, @Param("size") int size);

    int countByUserId(@Param("burst") String burst, @Param("userId") Long userId);
}