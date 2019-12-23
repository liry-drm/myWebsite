package com.example.demo.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.demo.common.utils.StringUtil;

/**
 * mybatis plus 提供的代码生成器 可以快速生成 Model、Mapper、Mapper XML、Service、Controller
 * 等各个模块的代码
 *
 * @link https://mp.baomidou.com/guide/generator.html
 */
@Component
public class CodeGenerator {

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
	
	// @author
	private static final String AUTHOR = System.getProperty("user.name");
	// 包的基础路径
	private static final String BASE_PACKAGE_URL = "com.example.demo";
	// xml 文件模板
	private static final String XML_MAPPER_TEMPLATE_PATH = "generator/templates/mapper.xml";
	// model 文件模板
	private static final String ENTITY_TEMPLATE_PATH = "generator/templates/model.java";
	// service 文件模板
	private static final String SERVICE_TEMPLATE_PATH = "generator/templates/service.java";
	// serviceImpl 文件模板
	private static final String SERVICE_IMPL_TEMPLATE_PATH = "generator/templates/serviceImpl.java";
	// controller 文件模板
	private static final String CONTROLLER_TEMPLATE_PATH = "generator/templates/controller.java";
	// controller父类
	private static final String SUPER_CONTROLLER_CLASS = "com.example.demo.web.controller.BaseController";
	// service父类
	private static final String SUPER_SERVICE_CLASS = "com.example.demo.service.IBaseService";
	// model父类
	private static final String SUPER_MODEL_CLASS = "com.example.demo.model.BaseModel";

	/**
	 * 生成model、mapper、mapper.xml、service
	 * 
	 * @param dbName     数据库名 支持mysql、postgresql; 默认mysql
	 * @param tableNames 需要生成的表名
	 */
	public static void generator(String dbName, String[] tableNames) {

		AutoGenerator generator = new AutoGenerator();
		// 全局配置
		GlobalConfig globalConfig = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		globalConfig.setOutputDir(projectPath + "/src/main/java");// 文件输出路径
		globalConfig.setAuthor(AUTHOR);
		globalConfig.setOpen(false);
		globalConfig.setFileOverride(true);// 是否覆盖同名文件，默认是false
		globalConfig.setEnableCache(false);// XML 二级缓存
		globalConfig.setBaseResultMap(true);// XML ResultMap
		globalConfig.setBaseColumnList(true);// XML columList
		globalConfig.setIdType(IdType.AUTO);
		generator.setGlobalConfig(globalConfig);

		// 使用freemarker模板
		generator.setTemplateEngine(new FreemarkerTemplateEngine());

		// 数据源配置
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		if (StringUtil.isEmpty(dbName)) {
			dbName = "";
		}
		switch (dbName.toLowerCase()) {
		case "postgresql":
			dataSourceConfig.setDbType(DbType.POSTGRE_SQL).setSchemaName("public");// 指定 SCHEMA
			dataSourceConfig.setTypeConvert(new PostgreSqlTypeConvert() {
				@Override
				public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
					//将数据库中timestamp转换成date
					if (fieldType.toLowerCase().contains("timestamp")) {
						return DbColumnType.DATE;
					}
//					if (fieldType.toLowerCase().contains("smallint")) {
//						return DbColumnType.BASE_BOOLEAN;
//					}
					return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
				}
			});
			dataSourceConfig.setUrl(POSTGRESQL_URL).setDriverName(POSTGRESQL_DRIVER_NAME)
							.setUsername(POSTGRESQL_USERNAME).setPassword(POSTGRESQL_PASSWORD);
			break;
		default:
			dataSourceConfig.setDbType(DbType.MYSQL);
				dataSourceConfig.setTypeConvert(new MySqlTypeConvert() {
					// 自定义数据库表字段类型转换【可选】
					@Override
					public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
		                System.out.println("转换类型：" + fieldType);
						if (fieldType.toLowerCase().contains("datetime")) {
							return DbColumnType.DATE;
						}
		                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
						return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
		            }

		        });
			dataSourceConfig.setUrl(MYSQL_URL).setDriverName(MYSQL_DRIVER_NAME)
							.setUsername(MYSQL_USERNAME).setPassword(MYSQL_PASSWORD);
		}
		generator.setDataSource(dataSourceConfig);

		// 包配置
		PackageConfig packageConfig = new PackageConfig();
		packageConfig.setParent(BASE_PACKAGE_URL)
				.setEntity("model")
				.setService("service").setServiceImpl("service.impl")
				.setController("web.controller");
		generator.setPackageInfo(packageConfig);

		// xml文件路径
		String XML_PACKAGE_URL = "/src/main/resources/mybatis/"+ dataSourceConfig.getDbType().getDb()+ "/";
		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		// 自定义输出配置,自定义配置会被优先输出
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义Mapper.xml
		focList.add(new FileOutConfig(XML_MAPPER_TEMPLATE_PATH + ".ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + XML_PACKAGE_URL + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		generator.setCfg(cfg);

		// 配置自定义代码模板
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setXml(null).setService(SERVICE_TEMPLATE_PATH).setEntity(ENTITY_TEMPLATE_PATH)
				.setServiceImpl(SERVICE_IMPL_TEMPLATE_PATH).setController(CONTROLLER_TEMPLATE_PATH);
		generator.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true); // 启用lombok;如果不启用可以改为false

		strategy.setSuperControllerClass(SUPER_CONTROLLER_CLASS);
		strategy.setSuperEntityClass(SUPER_MODEL_CLASS);
		strategy.setSuperServiceClass(SUPER_SERVICE_CLASS);
		strategy.setRestControllerStyle(true);
		strategy.setControllerMappingHyphenStyle(true);
	    strategy.setLogicDeleteFieldName("DELETED");
	    strategy.setSuperEntityColumns("id");
		strategy.setInclude(tableNames); // 需要生成的表
		// strategy.setSuperEntityColumns("id");
		strategy.setTablePrefix(new String[] { "t_" });// 此处可以修改为您的表前缀
		generator.setStrategy(strategy);

		// 执行生成
		generator.execute();
	}
	
    public static void main(String[] args) {
		String dbName="mysql";
        String[] tables=new String[]{"t_log","t_login_log"};
		generator(dbName,tables);
    }
}
