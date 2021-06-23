package edu.gdou.placemange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.placemange.entity.PlaceFeeStander;
import edu.gdou.placemange.mapper.PlaceFeeStanderMapper;
import edu.gdou.placemange.service.PlaceFeeStanderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceFeeStanderServiceImpl extends ServiceImpl<PlaceFeeStanderMapper, PlaceFeeStander> implements PlaceFeeStanderService {

    @Autowired
    PlaceFeeStanderMapper placeFeeStanderMapper;

    @Override
    public void PlaceFeeStanderInsert(PlaceFeeStander placeFeeStander) {

        int rows = placeFeeStanderMapper.insert(placeFeeStander);
        System.out.println("插入产生的影响行数"+rows);

    }

    @Override
    public void PlaceFeeStanderUpdate(PlaceFeeStander placeFeeStander) {

        int rows = placeFeeStanderMapper.updateById(placeFeeStander);
        System.out.println("插入产生的影响行数"+rows);

    }

    @Override
    public void PlaceFeeStanderDelete(String placeType) {

        int rows = placeFeeStanderMapper.deleteById(placeType);
        System.out.println("插入产生的影响行数"+rows);
    }

    @Override
    public List<PlaceFeeStander> PlaceFeeStanderSelect() {

        List<PlaceFeeStander> list = placeFeeStanderMapper.selectList(null);
        list.forEach(placeFeeStander ->System.out.println(placeFeeStander));
        return list;
    }
}
