package com.example.demo.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.model.enums.EducationEnum;
import com.example.demo.model.enums.SexEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper=false)
@XmlRootElement // 转换XML标志
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@TableName("t_person") // 对应表名
public class Person extends BaseModel {
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	
	@TableField("name")
	private String name;
	
	private String descr;
	
	private Integer age;
	
	private Integer del_flg;
	private String create_by;
	private Date create_time;
	@EnumValue
	private EducationEnum education;
	
	@EnumValue
	private SexEnum sex;
	
	@TableField(exist = true)
	private String address;
}
