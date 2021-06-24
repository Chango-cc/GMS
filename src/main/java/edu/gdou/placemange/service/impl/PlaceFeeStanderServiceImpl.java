package edu.gdou.placemange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.placemange.entity.Place;
import edu.gdou.placemange.entity.PlaceFeeStander;
import edu.gdou.placemange.entity.PlaceNotice;
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
        System.out.println("修改场地收费标准-------------------------------controlled");
        int rows = placeFeeStanderMapper.updateById(placeFeeStander);
        System.out.println("插入产生的影响行数"+rows);

    }

    @Override
    public void PlaceFeeStanderDelete(Integer feestanderId) {

        int rows = placeFeeStanderMapper.deleteById(feestanderId);
        System.out.println("插入产生的影响行数"+rows);
    }

    @Override
    public IPage<PlaceFeeStander> PlaceFeeStanderSelect(Integer current , Integer size) {

        IPage<PlaceFeeStander> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        IPage<PlaceFeeStander> result=placeFeeStanderMapper.selectPage(page,null);
        List<PlaceFeeStander> placeFeeStanders = result.getRecords();
        System.out.println("palaces.size=---  " + placeFeeStanders.size());
        long pages = result.getPages();
        System.out.println("页数：  " + pages);
        System.out.println("总记录数--  "+result.getTotal());
        System.out.println("当前页码：    " + result.getCurrent());
        System.out.println("每页的纪录数： " + result.getSize());
//        List<PlaceFeeStander> list = placeFeeStanderMapper.selectList(null);
        placeFeeStanders.forEach(placeFeeStander ->System.out.println(placeFeeStander));
        return result;
    }

    @Override
    public boolean PlaceFeeTypeChecked(String placeType) {
        QueryWrapper<PlaceFeeStander> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("place_type", placeType);
        Integer integer = placeFeeStanderMapper.selectCount(queryWrapper);
        return integer != 0;
    }
}
