package edu.gdou.placemange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdou.placemange.entity.PlaceNotice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaceNoticeService extends IService<PlaceNotice> {

    /*
    添加场地公告
     */

    public void PlaceNoticeInsert(PlaceNotice placeNotice);

    /*
    更新场地公告
     */

    public void PlaceNoticeUpdate(PlaceNotice placeNotice);

    /*
    删除场地公告，逻辑删除
     */

    public void PlaceNoticeDelete(Integer noticeId,String noticeState);

    /*
    查询场地公告，可进行多条件筛选
     */

    public List<PlaceNotice> PlaceNoticeSelect(String noticeTitle, String noticeContent, String noticeDate);


}
