package com.hecai.service.test.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("test1")
public class Test1 {
    @TableId(type = IdType.AUTO,value = "id")
    private Integer id;
    private String name;
    private Integer age;
}
