package edu.gdou.placemange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdou.placemange.entity.Place;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaceService extends IService<Place> {

    /*
    添加场地
     */

    public void PlaceInsert(Place place);

    /*
    修改场地信息
     */

    public void PlaceUpdate(Place place);

    /*
    查询总的场地信息，包括多条件筛选
     */

    public List<Place>PlaceIdSelect(String placeStorey,String placeType,String placeState);

    /*
    查询可用场地信息，包括多条件筛选
     */

    public List<Place>PlaceIdSelected(String placeStorey,String placeType,String placeState);

    /*
    删除场地信息，逻辑删除
     */

    public void PlaceDelete(Integer placeId,String placeState);

    /*
    场地编号唯一性验证
     */

    public boolean PlaceNoChecked(String placeNo);
}
