package edu.gdou.placemange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdou.placemange.entity.Place;
import org.springframework.stereotype.Repository;

/*
自定义mapper，dao接口
1.要实现BaseMapper
2.指定实体类
BaseMapper是MP自带的，有17个CRUD
 */
@Repository
public interface PlaceMapper extends BaseMapper<Place> {
}
