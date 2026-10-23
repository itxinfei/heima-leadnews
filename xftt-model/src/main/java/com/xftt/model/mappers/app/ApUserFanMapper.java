package com.xftt.model.mappers.app;

import com.xftt.model.user.pojos.ApUserFan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApUserFanMapper {
    int insert(ApUserFan record);
    ApUserFan selectByFansId(@Param("burst") String burst, @Param("userId")Integer userId ,@Param("fansId") Long fansId);
    int deleteByFansId(@Param("burst")String burst,@Param("userId")Integer userId ,@Param("fansId")Long fansId);

    /** 粉丝列表分页（BE-F03） */
    List<ApUserFan> selectFansByUserId(@Param("burst") String burst, @Param("userId") Integer userId,
                                       @Param("offset") int offset, @Param("size") int size);

    int countFansByUserId(@Param("burst") String burst, @Param("userId") Integer userId);
}