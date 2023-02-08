package com.guo.tienchin;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class GenerateCode {
    @Test
    void generateActivityCode() {
        String path = "D:\\Programme\\Projects\\idea-projects\\tienchin\\tienchin-activity\\src\\main";
        FastAutoGenerator.create(
                        "jdbc:mysql:///tienchin?serverTimeZone=Asia/Shanghai&useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                        "root",
                        "123456")
                .globalConfig(builder -> {
                    builder.author("guo") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.guo.tienchin") // 设置父包名
                            .moduleName("activity") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tienchin_activity") // 设置需要生成的表名
                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
    @Test
    void generateChannelCode() {
        String path = "D:\\Programme\\Projects\\idea-projects\\tienchin\\tienchin-channel\\src\\main\\";
        FastAutoGenerator.create(
                "jdbc:mysql:///tienchin?serverTimeZone=Asia/Shanghai&useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                        "root",
                        "123456")
                .globalConfig(builder -> {
                    builder.author("guo") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.guo.tienchin") // 设置父包名
                            .moduleName("channel") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tienchin_channel") // 设置需要生成的表名
                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
