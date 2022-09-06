package com.lite.system.utils.list.iml;

import com.lite.system.entity.SystemEntity;
import com.lite.system.utils.list.ListComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/26 19:56
 */
@Slf4j
@Component
public class SystemEntityComparator implements ListComparator<SystemEntity> {

    /**
     * 从system中找出system有，但是database没有的元素并收集成list
     */
    @Override
    public List<? extends SystemEntity> findExtraPart(List<? extends SystemEntity> system, List<? extends SystemEntity> database) {
        if (Objects.isNull(system) || Objects.isNull(database)) {
            return null;
        }

        return system.stream()
                .filter(systemController -> database.stream()
                        .noneMatch(databaseController -> databaseController.getFullName()
                                .equals(systemController.getFullName())))
                .collect(Collectors.toList());
    }

    /**
     * 从database中找出database有，但是system中没有的元素并收集成list
     */
    @Override
    public List<? extends SystemEntity> findLessPart(List<? extends SystemEntity> system, List<? extends SystemEntity> database) {
        if (Objects.isNull(system) || Objects.isNull(database)) {
            return null;
        }

        return database.stream()
                .filter(databaseController -> system.stream()
                        .noneMatch(systemController -> systemController.getFullName()
                                .equals(databaseController.getFullName())))
                .collect(Collectors.toList());
    }

    /**
     * 从system中找出相较于database中数据是更新了但是逻辑上仍然是相同的对象并收集成list
     */
    @Override
    public List<? extends SystemEntity> findUpdatePart(List<? extends SystemEntity> system, List<? extends SystemEntity> database) {
        if (Objects.isNull(system) || Objects.isNull(database)) {
            return null;
        }

        return system.stream()
                .filter(systemEntity -> database.stream()
                        .anyMatch(databaseEntity-> {
                            boolean res = (!systemEntity.equals(databaseEntity)) && systemEntity.getFullName().equals(databaseEntity.getFullName());
                            if (res) {
                                systemEntity.setId(databaseEntity.getId());
                            }
                            return res;
                        }))
                .collect(Collectors.toList());
    }
}
