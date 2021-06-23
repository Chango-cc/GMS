package edu.gdou.placemange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.placemange.entity.Place;
import edu.gdou.placemange.mapper.PlaceMapper;
import edu.gdou.placemange.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaceServiceImpl extends ServiceImpl<PlaceMapper, Place> implements PlaceService {


    @Autowired
    private PlaceMapper placeMapper;


    @Override
    public void PlaceInsert(Place place) {

        //调用对应的方法
        int rows = placeMapper.insert(place);
        System.out.println("插入产生的影响行数"+rows);

    }

    @Override
    public void PlaceUpdate(Place place) {

        int rows = placeMapper.updateById(place);
        System.out.println("成功修改场地状态的行数为"+rows);

    }

    @Override
    public List<Place> PlaceIdSelect(String placeStorey, String placeType, String placeState) {

        QueryWrapper<Place> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        //组装条件
        map.put("place_state",placeState);
        map.put("place_storey",placeStorey);
        map.put("place_type",placeType);
        queryWrapper.allEq(map,false).or().eq("place_state","维修中");

        List<Place> list = placeMapper.selectList(queryWrapper);
        list.forEach(place ->System.out.println(place));
        return list;
    }

    @Override
    public List<Place> PlaceIdSelected(String placeStorey, String placeType, String placeState) {
        System.out.println("要判断的条件为：场地楼层"+placeStorey+"    "+placeType+"    "+placeState);
        QueryWrapper<Place> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        //组装条件
        map.put("place_state",placeState);
        map.put("place_storey",placeStorey);
        map.put("place_type",placeType);
        queryWrapper.allEq(map,false);

        List<Place> list = placeMapper.selectList(queryWrapper);
        list.forEach(place ->System.out.println(place));
        return list;
    }

    @Override
    public void PlaceDelete(Integer placeId, String placeState) {

        Place place = new Place();
        //赋值
        place.setPlaceId(placeId);
        place.setPlaceState(placeState);
        int rows = placeMapper.updateById(place);
        System.out.println("成功修改场地状态的行数为"+rows);

    }

    @Override
    public boolean PlaceNoChecked( String placeNo) {
        QueryWrapper<Place> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("place_no", placeNo);
        queryWrapper.ne("place_state", "已删除");
        Integer integer = placeMapper.selectCount(queryWrapper);
        return integer != 0;
    }
}
