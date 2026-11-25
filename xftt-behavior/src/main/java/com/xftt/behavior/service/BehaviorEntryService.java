package com.xftt.behavior.service;

import com.xftt.common.zookeeper.sequence.Sequences;
import com.xftt.model.behavior.pojos.ApBehaviorEntry;
import com.xftt.model.mappers.app.ApBehaviorEntryMapper;
import com.xftt.utils.common.BurstUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 行为实体（ap_behavior_entry）保障服务。
 * 用户/设备首次产生行为时自动落库，避免各行为接口因缺少实体而报“无效参数”。
 */
@Service
public class BehaviorEntryService {

    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;

    @Autowired
    private Sequences sequences;

    /**
     * 查询行为实体，不存在则创建。
     *
     * @param userId      登录用户 ID（可为空）
     * @param equipmentId 设备 ID（可为空）
     */
    public ApBehaviorEntry ensureEntry(Long userId, Integer equipmentId) {
        ApBehaviorEntry entry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(userId, equipmentId);
        if (entry != null) {
            return entry;
        }
        Long refId = userId != null ? userId : equipmentId.longValue();
        entry = new ApBehaviorEntry();
        entry.setId(sequences.sequenceApBehaviorEntry().intValue());
        entry.setType(userId != null
                ? ApBehaviorEntry.Type.USER.getCode()
                : ApBehaviorEntry.Type.EQUIPMENT.getCode());
        entry.setEntryId(refId.intValue());
        entry.setCreatedTime(new Date());
        entry.setBurst(BurstUtils.groudOne(refId));
        apBehaviorEntryMapper.insert(entry);
        return entry;
    }
}
