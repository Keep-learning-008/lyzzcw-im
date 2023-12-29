package lyzzcw.work.im.platform.common.infrastructure.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lzy
 * @version 1.0
 * Date: 2023/12/8 11:26
 * Description: No Description
 */
public class IngoreSetterAndGetterPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        //false 会跳过此插件导致不生效
        return true;
    }

//    @Override
//    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        //添加domain的import
//        topLevelClass.addImportedType("lombok.Data");
//        topLevelClass.addImportedType("lombok.NoArgsConstructor");
//        topLevelClass.addImportedType("lombok.AllArgsConstructor");
//
//        //添加domain的注解
//        topLevelClass.addAnnotation("@Data");
//        topLevelClass.addAnnotation("@NoArgsConstructor");
//        topLevelClass.addAnnotation("@AllArgsConstructor");
//
//        //添加domain的注释
//        topLevelClass.addJavaDocLine("/**");
//        topLevelClass.addJavaDocLine("* Created by Mybatis Generator on " + date2Str(new Date()));
//        topLevelClass.addJavaDocLine("*/");
//
//        return true;
//    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //Mapper文件的注释
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine("* Created by Mybatis Generator on " + date2Str(new Date()));
        interfaze.addJavaDocLine("*/");
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }

    private String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }
}
