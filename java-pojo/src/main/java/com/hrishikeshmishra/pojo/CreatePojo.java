package com.hrishikeshmishra.pojo;


import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hrishikesh.mishra
 * @since 28/10/16
 */
public class CreatePojo {

    public static void main(String[] args) throws IOException {
        String className = "MyResponse";
        String packageName = "com.hrishikeshmishra.responses";
        String outputDir = "/Users/hrishikesh.mishra/hrishi/codes/rd/java-pojo/config/output";
        String schema =  new String(Files.readAllBytes(Paths.get("/Users/hrishikesh.mishra/hrishi/codes/rd/java-pojo/config/schema/MyResponse.json")));

        createPojo(className, packageName, schema, outputDir);
    }

    public static void createPojo(String className, String packageName, String schema, String outputDir) throws IOException {
        List<String> outputFile = new ArrayList<>();
        JCodeModel codeModel = new JCodeModel();
        DefaultGenerationConfig config = getConfig();
        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(), new SchemaStore()), new SchemaGenerator());

        mapper.generate(codeModel, className, packageName, schema);
        codeModel.build(new File(outputDir));

        Iterator packages = codeModel.packages();

        while(packages.hasNext()) {
            JPackage jPackage = (JPackage)packages.next();
            Iterator classes = jPackage.classes();

            while(classes.hasNext()) {
                JDefinedClass classDefined = (JDefinedClass)classes.next();
                String outputFilePath = outputFilePath(outputDir, classDefined.fullName());
                outputFile.add(outputFilePath);
                System.out.println("Output file path of the class generated: " + outputFilePath);
            }
        }

        System.out.println(outputFile);

    }

    private static DefaultGenerationConfig getConfig() {
        return new DefaultGenerationConfig() {
            public char[] getPropertyWordDelimiters() {
                return new char[]{'-', ' '};
            }

            public boolean isRemoveOldOutput() {
                return true;
            }

            public boolean isIncludeDynamicAccessors() {
                return false;
            }

            public boolean isUseLongIntegers() {
                return true;
            }
        };
    }

    private static String outputFilePath(String outputDir, String fullClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append(outputDir);
        sb.append("/");
        sb.append(fullClassName.replaceAll("\\.", "/"));
        sb.append(".");
        sb.append("java");
        return sb.toString();
    }
}
