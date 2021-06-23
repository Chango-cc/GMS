package edu.gdou.usermanage.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.usermanage.entity.Announcement;
import edu.gdou.usermanage.mapper.AnnouncementMapper;
import edu.gdou.usermanage.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

    @Autowired
    AnnouncementMapper announcementMapper;

    @Override
    public List<Announcement> selectAll() {
        List<Announcement> list=new ArrayList<>();
        list=announcementMapper.selectList(null);
        return list;
    }
}
