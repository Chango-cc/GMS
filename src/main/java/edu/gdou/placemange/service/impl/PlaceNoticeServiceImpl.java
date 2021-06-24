package edu.gdou.placemange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.placemange.entity.PlaceApply;
import edu.gdou.placemange.entity.PlaceNotice;
import edu.gdou.placemange.mapper.PlaceNoticeMapper;
import edu.gdou.placemange.service.PlaceNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaceNoticeServiceImpl extends ServiceImpl<PlaceNoticeMapper, PlaceNotice> implements PlaceNoticeService {

    @Autowired
    private PlaceNoticeMapper placeNoticeMapper;

    /*
    添加场地公告
     */
    @Override
    public void PlaceNoticeInsert(PlaceNotice placeNotice) {

        int rows = placeNoticeMapper.insert(placeNotice);
        System.out.println("插入产生的影响行数"+rows);

    }

    /*
    更新场地公告
     */
    @Override
    public void PlaceNoticeUpdate(PlaceNotice placeNotice) {

        int rows = placeNoticeMapper.updateById(placeNotice);
        System.out.println("插入产生的影响行数"+rows);

    }

    /*
    删除场地公告，逻辑删除
     */
    @Override
    public void PlaceNoticeDelete(Integer noticeId, String noticeState) {

        UpdateWrapper<PlaceNotice> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("notice_state",noticeState);
        updateWrapper.eq("notice_id",noticeId);
        int rows = placeNoticeMapper.update(null,updateWrapper);
        System.out.println("插入产生的影响行数"+rows);

    }

    /*
    查询场地公告，可进行多条件筛选
     */
    @Override
    public List<PlaceNotice> PlaceNoticeSelect(String noticeTitle, String noticeContent, String noticeDate) {

        QueryWrapper<PlaceNotice> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        //组装条件
        queryWrapper.eq("notice_state","已发布");
        queryWrapper.like("notice_title",noticeTitle);
        queryWrapper.like("notice_content",noticeContent);
        queryWrapper.like("notice_date",noticeDate);
        //查询
        List<PlaceNotice> list = placeNoticeMapper.selectList(queryWrapper);
        list.forEach(placeNotice ->System.out.println(placeNotice));
        return list;
    }

    /*
    普通的场地查询
     */
    @Override
    public IPage<PlaceNotice> PlaceNoticeSelecting(Integer current , Integer size){

        QueryWrapper<PlaceNotice> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        //组装条件
        queryWrapper.eq("notice_state","已发布");
        //查询
        IPage<PlaceNotice> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        IPage<PlaceNotice> result=placeNoticeMapper.selectPage(page,queryWrapper);
        List<PlaceNotice> placeNotices = result.getRecords();
        System.out.println("palaces.size=---  " + placeNotices.size());
        long pages = result.getPages();
        System.out.println("页数：  " + pages);
        System.out.println("总记录数--  "+result.getTotal());
        System.out.println("当前页码：    " + result.getCurrent());
        System.out.println("每页的纪录数： " + result.getSize());
//        List<PlaceNotice> list = placeNoticeMapper.selectList(queryWrapper);
        placeNotices.forEach(placeNotice ->System.out.println(placeNotice));
        return result;

    }
}
