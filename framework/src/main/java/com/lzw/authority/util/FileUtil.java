package com.lzw.authority.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 11
 * @Description:
 */
public class FileUtil {

    public static File saveFile(InputStream is, String folder, String fileName) throws IOException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(folder);
        File dfile = null;
        if (!f.exists()) {
            f.mkdirs();
        }

        try {
            bis = new BufferedInputStream(is);
            fileName = URLDecoder.decode(fileName, "utf-8");
            dfile = new File(folder + File.separator + fileName);
            byte[] b = new byte[1048576];
            bos = new BufferedOutputStream(new FileOutputStream(dfile));
            boolean var8 = false;

            int i;
            while((i = bis.read(b)) != -1) {
                bos.write(b, 0, i);
            }
        } finally {
            bos.flush();
            bis.close();
            bos.close();
        }

        return dfile;
    }

    public static void saveFile(File file, String content) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content.getBytes());
        fop.flush();
        fop.close();
    }

    public static void saveFile(File file, List<String> contents) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }

        if (contents != null) {
            FileOutputStream fop = new FileOutputStream(file);
            Iterator var3 = contents.iterator();

            while(var3.hasNext()) {
                String content = (String)var3.next();
                content = content + "\r\n";
                fop.write(content.getBytes());
            }

            fop.flush();
            fop.close();
        }
    }

    public static void deleteFile(File file) {
        if (null != file && file.exists()) {
            File[] listFiles = file.listFiles();
            File[] var2 = listFiles;
            int var3 = listFiles.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                File f = var2[var4];
                if (f.isDirectory()) {
                    deleteFile(f);
                } else {
                    f.delete();
                }
            }

        }
    }

    public static String readFileByChars(File file) {
        return null;
    }

    public static String getFileRootString(File file) {
        File target = new File(file.getAbsolutePath());

        while(true) {
            File p = target.getParentFile();
            if (p == null) {
                return target.getAbsolutePath();
            }

            target = p;
        }
    }

    public static List<File> getJavaFile(String rootPath) {
        File file = new File(rootPath);
        List<File> files = new ArrayList();
        fileForeach(file, files);
        return files;
    }

    private static List<File> fileForeach(File file, List<File> files) {
        File[] tempList = file.listFiles();
        if (null != tempList) {
            for(int i = 0; i < tempList.length; ++i) {
                if (tempList[i].isFile()) {
                    if (isJavaFile(tempList[i].getName())) {
                        files.add(tempList[i]);
                    }
                } else if (tempList[i].isDirectory()) {
                    fileForeach(tempList[i], files);
                }
            }
        }

        return files;
    }

    public static boolean isJavaFile(String fileName) {
        String extention = "";
        if (fileName.length() > 0 && fileName != null) {
            int i = fileName.lastIndexOf(".");
            if (i > -1 && i < fileName.length()) {
                extention = fileName.substring(i + 1);
                if ("java".equals(extention)) {
                    return true;
                }
            }
        }

        return false;
    }
}
