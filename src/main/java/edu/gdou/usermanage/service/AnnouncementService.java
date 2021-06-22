package edu.gdou.usermanage.service;

import edu.gdou.usermanage.entity.Announcement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//@Component    调用数据访问层 Mapper
public interface AnnouncementService {

    public List<Announcement> selectAll();


}
