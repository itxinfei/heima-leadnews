package com.xftt.model.mappers.app;

import com.xftt.model.behavior.pojos.ApBehaviorEntry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApBehaviorEntryMapper {
    ApBehaviorEntry selectByUserIdOrEquipemntId(@Param("userId") Long userId,@Param("equipmentId") Integer equipmentId);

    List<ApBehaviorEntry> selectAllEntry();
}
