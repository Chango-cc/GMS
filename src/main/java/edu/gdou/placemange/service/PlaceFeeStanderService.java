package edu.gdou.placemange.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdou.placemange.entity.PlaceFeeStander;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaceFeeStanderService extends IService<PlaceFeeStander> {

    /*
    插入场地收费标准
     */

    public void PlaceFeeStanderInsert(PlaceFeeStander placeFeeStander);

    /*
    修改场地收费标准
     */

    public void PlaceFeeStanderUpdate(PlaceFeeStander placeFeeStander);

    /*
    删除场地收费标准
     */

    public void PlaceFeeStanderDelete(Integer feestanderId);

    /*
    查询场地收费标准，包括以添加筛选
     */

    public IPage<PlaceFeeStander> PlaceFeeStanderSelect(Integer current , Integer size);

    /*
    场地收费标准类型唯一性
     */
    public boolean PlaceFeeTypeChecked(String placeType);

}
