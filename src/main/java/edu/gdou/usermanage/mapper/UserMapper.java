package edu.gdou.usermanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdou.usermanage.entity.Gmsuser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<Gmsuser> {
}
