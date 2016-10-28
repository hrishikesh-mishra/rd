package com.hrishikeshmishra.jcompile.loader;

import javax.tools.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * @author hrishikesh.mishra
 * @since 28/10/16
 */
public class CustomJavaFileManager implements JavaFileManager {

    private final ClassLoader classLoader;
    private final StandardJavaFileManager standardFileManager;

    public CustomJavaFileManager(ClassLoader classLoader, StandardJavaFileManager standardFileManager) {
        this.classLoader = classLoader;
        this.standardFileManager = standardFileManager;
    }

    public ClassLoader getClassLoader(Location location) {
        return location == StandardLocation.CLASS_PATH?this.classLoader:this.standardFileManager.getClassLoader(location);
    }

    public String inferBinaryName(Location location, JavaFileObject file) {
        return file instanceof JavaFileObject?((JavaFileObject)file).getName():this.standardFileManager.inferBinaryName(location, file);
    }

    public boolean isSameFile(FileObject a, FileObject b) {
        throw new UnsupportedOperationException();
    }

    public boolean handleOption(String current, Iterator<String> remaining) {
        return this.standardFileManager.handleOption(current, remaining);
    }

    public boolean hasLocation(Location location) {
        return location == StandardLocation.CLASS_PATH || location == StandardLocation.PLATFORM_CLASS_PATH;
    }

    public JavaFileObject getJavaFileForInput(Location location, String className, JavaFileObject.Kind kind) throws IOException {
        throw new UnsupportedOperationException();
    }

    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        return this.standardFileManager.getJavaFileForOutput(location, className, kind, sibling);
    }

    public FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
        throw new UnsupportedOperationException();
    }

    public FileObject getFileForOutput(Location location, String packageName, String relativeName, FileObject sibling) throws IOException {
        throw new UnsupportedOperationException();
    }

    public void flush() throws IOException {
    }

    public void close() throws IOException {
    }

    public Iterable<JavaFileObject> list(Location location, String packageName, Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
        if(location == StandardLocation.PLATFORM_CLASS_PATH) {
            return this.standardFileManager.list(location, packageName, kinds, recurse);
        } else {
//            if(location == StandardLocation.CLASS_PATH && kinds.contains(JavaFileObject.Kind.CLASS)) {
//                if(packageName.startsWith("java.")) {
//                    return this.standardFileManager.list(location, packageName, kinds, recurse);
//                }
//
//                try {
//                    return this.finder.find(packageName, recurse);
//                } catch (ClassGenerationException var6) {
//                    new IOException(var6);
//                }
//            }

            return Collections.emptyList();
        }
    }

    public int isSupportedOption(String option) {
        return -1;
    }

}
