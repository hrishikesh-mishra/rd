package com.hrishikeshmishra.jcompile;


import javax.tools.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author hrishikesh.mishra
 * @since 28/10/16
 */
public class CompileJava {

    public static void main(String[] args) {

        String files[] = {"/Users/hrishikesh.mishra/hrishi/codes/java-random/code/HelloWorld.java"};
        String outputDir = "/Users/hrishikesh.mishra/hrishi/codes/java-random/target/classes";

        compile(files, outputDir);
    }

    public static void compile(String[] filePath, String outputDir) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, Locale.ENGLISH, null);
        Iterable compilationUnit = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(filePath));

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        List options = Arrays.asList(new String[]{"-d", outputDir, "-cp", outputDir});

        //CustomJavaFileManager customJavaFileManager = new CustomJavaFileManager(CompileJava.class.getClassLoader(), fileManager);

//        JavaCompiler.CompilationTask task = compiler.getTask(null, customJavaFileManager, diagnostics, options, null, compilationUnit);
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, options, null, compilationUnit);

        boolean success = task.call();
        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
            System.out.println(diagnostic.getCode());
            System.out.println(diagnostic.getKind());
            System.out.println(diagnostic.getPosition());
            System.out.println(diagnostic.getStartPosition());
            System.out.println(diagnostic.getEndPosition());
            System.out.println(diagnostic.getSource());
            System.out.println(diagnostic.getMessage(null));
        }

        if (true) {
            try {
                Class.forName("HelloWorld").getDeclaredMethod("main", new Class[]{String[].class})
                        .invoke(null, new Object[]{null});
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found: " + e);
            } catch (NoSuchMethodException e) {
                System.err.println("No such method: " + e);
            } catch (IllegalAccessException e) {
                System.err.println("Illegal access: " + e);
            } catch (InvocationTargetException e) {
                System.err.println("Invocation target: " + e);
            }
        }
    }

}
