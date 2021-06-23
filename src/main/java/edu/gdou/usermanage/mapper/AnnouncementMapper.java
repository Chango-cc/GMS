package edu.gdou.usermanage.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdou.usermanage.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AnnouncementMapper extends BaseMapper<Announcement> {
}
