package com.example.demo.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * mybatis plus 提供的代码生成器
 * 可以快速生成 Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码
 *
 * @link https://mp.baomidou.com/guide/generator.html
 */
@SuppressWarnings("all")
public class CodeGenerator_old {

    // mysql数据库 URL
    private static final String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/mysqldb?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    // mysql数据库驱动
    private static final String MYSQL_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    // mysql数据库用户名
    private static final String MYSQL_USERNAME = "root";
    // mysql数据库密码
    private static final String MYSQL_PASSWORD = "123456";
    
    // postgresql数据库 URL
    private static final String POSTGRESQL_URL = "jdbc:postgresql://192.168.2.94:5432/QDIP?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    // postgresql数据库驱动
    private static final String POSTGRESQL_DRIVER_NAME = "org.postgresql.Driver";
    // postgresql数据库用户名
    private static final String POSTGRESQL_USERNAME = "postgres";
    // postgresql数据库密码
    private static final String POSTGRESQL_PASSWORD = "postgres";
    
    // @author 值
    private static final String AUTHOR = "lry";
    // 包的基础路径
    private static final String BASE_PACKAGE_URL = "com.example.demo";
    // Mysql_xml文件路径
    private static final String MYSQL_XML_PACKAGE_URL = "/src/main/resources/mybatis/mysql/";
    // Postgresql_xml文件路径
    private static final String POSTGRESQL_XML_PACKAGE_URL = "/src/main/resources/mybatis/postgresql/";
    // xml 文件模板
    private static final String XML_MAPPER_TEMPLATE_PATH = "generator/templates/mapper.xml";
    // mapper 文件模板
    private static final String MAPPER_TEMPLATE_PATH = "generator/templates/mapper.java";
    // entity 文件模板
    private static final String ENTITY_TEMPLATE_PATH = "generator/templates/entity.java";
    // service 文件模板
    private static final String SERVICE_TEMPLATE_PATH = "generator/templates/service.java";
    // serviceImpl 文件模板
    private static final String SERVICE_IMPL_TEMPLATE_PATH = "generator/templates/serviceImpl.java";
    // controller 文件模板
    private static final String CONTROLLER_TEMPLATE_PATH = "generator/templates/controller.java";
    // 父类
    private static final String SUPERCONTROLLERCLASS = "com.example.demo.controller.web.BaseController";
    //mapper继承mybatis-plus基类
    private static final String SUPERMAPPERCLASS = "com.baomidou.mybatisplus.core.mapper.BaseMapper";
    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor(AUTHOR);
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);
        generator.setGlobalConfig(globalConfig);

        int sqlcode=0;
        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        switch (scanner("数据库名:").toLowerCase()) {
			case "postgresql":
				dataSourceConfig.setDbType(DbType.POSTGRE_SQL);
				dataSourceConfig.setUrl(POSTGRESQL_URL);
		        dataSourceConfig.setDriverName(POSTGRESQL_DRIVER_NAME);
		        dataSourceConfig.setUsername(POSTGRESQL_USERNAME);
		        dataSourceConfig.setPassword(POSTGRESQL_PASSWORD);
				/*dataSourceConfig.setTypeConvert(new MySqlTypeConvert(){
					// 自定义数据库表字段类型转换【可选】
					public DbColumnType processTypeConvert(String fieldType) {
						System.out.println("转换类型：" + fieldType);
						// 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
						return super.processTypeConvert(fieldType);
					}
				});*/
		        sqlcode=1;
				break;
			default:
				dataSourceConfig.setDbType(DbType.MYSQL);
				dataSourceConfig.setUrl(MYSQL_URL);
		        dataSourceConfig.setDriverName(MYSQL_DRIVER_NAME);
		        dataSourceConfig.setUsername(MYSQL_USERNAME);
		        dataSourceConfig.setPassword(MYSQL_PASSWORD);
				break;
		}
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(scanner("模块名:"));
        packageConfig.setParent(BASE_PACKAGE_URL); 
        generator.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(XML_MAPPER_TEMPLATE_PATH+".ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + MYSQL_XML_PACKAGE_URL + "/" 
						+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);
        
        // 配置自定义代码模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setMapper(MAPPER_TEMPLATE_PATH);
        templateConfig.setEntity(ENTITY_TEMPLATE_PATH);
        templateConfig.setService(SERVICE_TEMPLATE_PATH);
        templateConfig.setServiceImpl(SERVICE_IMPL_TEMPLATE_PATH);
        templateConfig.setController(CONTROLLER_TEMPLATE_PATH);
        generator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setSuperControllerClass(SUPERCONTROLLERCLASS);
        strategy.setSuperMapperClass(SUPERMAPPERCLASS);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名:"));
        //strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("t_");
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) return ipt;
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
