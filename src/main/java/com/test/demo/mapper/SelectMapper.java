package com.test.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.test.demo.model.Select;

public interface SelectMapper extends BaseMapper<Select> {
    int insertSelect(Select select);
}