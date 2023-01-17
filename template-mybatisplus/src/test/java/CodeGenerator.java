import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.mysql.jdbc.Driver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator {
    //JDBC配置，请修改为你项目的实际配置
    private static final String JDBC_URL = "jdbc:mysql://***.***.***.***:3306/******?useUnicode=true&characterEncoding=utf-8";
    private static final String JDBC_USERNAME = "******";
    private static final String JDBC_PASSWORD = "******";
    private static final String JDBC_DIVER_CLASS_NAME = Driver.class.getName();

    private static final String PROJECT_PATH = System.getProperty("user.dir").replaceAll("\\\\","/");//项目在硬盘上的基础路径
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/template";//模板位置

    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径



    private static final String AUTHOR = "Generator";//@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date


    // MBG相关
    public static final String BASE_PACKAGE = "com.anakin.example";//生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）
    public static final String ENTITY_PACKAGE = BASE_PACKAGE + ".entity";//生成的Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".repository";//生成的Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//生成的Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//生成的ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";//生成的Controller所在包
    public static final String MAPPER_XMP_PATH = RESOURCES_PATH + "/mapper";

    public static void main(String[] args) {
        genCode("name");
    }
    // 生成文件控制
    private static final boolean GEN_CONTROLLER = false;
    private static final boolean GEN_ENTITY = true;
    private static final boolean GEN_DTO = false;
    private static final boolean GEN_VO = false;
    private static final boolean GEN_CVT = false;
    private static final boolean GEN_SERVICE = false;
    private static final boolean GEN_SERVICE_IMPL = false;
    private static final boolean GEN_MAPPER = false;
    private static final boolean GEN_MAPPER_XML = false;


    public static void genCode(String tableName) {
        String tableNameCamel = StrUtil.toCamelCase(tableName);
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(PROJECT_PATH + JAVA_PATH);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        gc.setFileOverride(true); // 是否覆盖已有文件
        gc.setServiceName("%sService");
        gc.setBaseColumnList(false);
        gc.setBaseResultMap(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(JDBC_URL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(JDBC_USERNAME);
        dsc.setPassword(JDBC_PASSWORD);
        dsc.setTypeConvert(new MySqlTypeConvert());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent(BASE_PACKAGE);
        pc.setEntity("entity");
        pc.setMapper("repository");
        pc.setXml("");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String,Object> injectMap = new HashMap<>();
                injectMap.put("modelNameUpperCamel",tableNameCamel);
                this.setMap(injectMap);
            }
        };


        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return PROJECT_PATH + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        focList.add(new FileOutConfig("/templates/dto.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return PROJECT_PATH + JAVA_PATH + packageConvertPath(BASE_PACKAGE + ".model.dto")
                        + tableInfo.getEntityName() + "DTO" + StringPool.DOT_JAVA;
            }
        });

        focList.add(new FileOutConfig("/templates/vo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return PROJECT_PATH + JAVA_PATH + packageConvertPath(BASE_PACKAGE + ".model.vo")
                        + tableInfo.getEntityName() + "VO" + StringPool.DOT_JAVA;
            }
        });

        focList.add(new FileOutConfig("/templates/cvt.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return PROJECT_PATH + JAVA_PATH + packageConvertPath(BASE_PACKAGE + ".model.cvt")
                        + tableInfo.getEntityName() + "Convert" + StringPool.DOT_JAVA;
            }
        });

        // mapper文件已存在则不生成
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                if (!GEN_CONTROLLER && fileType == FileType.CONTROLLER) {
                    return false;
                }
                if (!GEN_DTO && filePath.contains("DTO")) {
                    return false;
                }
                if (!GEN_VO && filePath.contains("VO")) {
                    return false;
                }
                if (!GEN_CVT && filePath.contains("Convert")) {
                    return false;
                }
                if (!GEN_ENTITY && fileType == FileType.ENTITY) {
                    return false;
                }
                if (!GEN_SERVICE && fileType == FileType.SERVICE) {
                    return false;
                }
                if (!GEN_SERVICE_IMPL && fileType == FileType.SERVICE_IMPL) {
                    return false;
                }
                if (!GEN_MAPPER && fileType == FileType.MAPPER) {
                    return false;
                }
                if (!GEN_MAPPER_XML && fileType == FileType.XML) {
                    return false;
                }
                if (!GEN_MAPPER_XML && filePath.contains("Mapper.xml")) {
                    return false;
                }
                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录，自定义目录用");
                if (!filePath.contains("resources")&&filePath.contains("Mapper.xml")) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return false;
                } else if (filePath.contains("resources")&&filePath.contains("Mapper.xml")) {
                    checkDir(filePath);
                }
                // 允许生成模板文件
                return true;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setMapper("templates/mapper.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setController("templates/controller.java");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}
