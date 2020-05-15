package com.user.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.util.Date;

public class ExcelUtil {

    public static String fileUpload(HttpServletRequest request, String fileRoot, String rootPath, String extArr) throws IOException {

        MultipartFile fileData = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartFile = (MultipartHttpServletRequest)request;

            fileData = multipartFile.getFile("fileData"); //页面中name
        }
        if (null == fileData || fileData.getSize() == 0) {
            System.out.println("fileData="+fileData);
            return null;
        }
        String fileName = fileData.getOriginalFilename();  //获得文件名字
        String ext = fileName.substring(fileName.indexOf("."));  //获取.xls
        File filePath = new File(rootPath + fileRoot);

        //文件不存在的时候创建新文件夹
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        if (ext != null && ext.length() > 1&& extArr.indexOf(ext.toLowerCase().substring(1)) > -1) {
            InputStream is = null;
            String tempName = new Date().getTime() + ext;  //产生新的文件名
            String url = rootPath + fileRoot + tempName;
            File file = new File(url);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            try {
                is = fileData.getInputStream();
                IOUtils.copy(is, out);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (out != null) {
                    out.close();
                }
                if (is != null) {
                    is.close();
                }
            }
            return fileRoot + tempName;
        } else {
            throw new IOException("文件类型不匹配");
        }

    }

    public static String createFile(String rootPath, String childPath,
                                    String content, String fileName) throws IOException {
        try {
            File dir = new File(rootPath + childPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(rootPath + childPath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(
                    new FileOutputStream(file), "utf-8");
            oStreamWriter.write(content);
            oStreamWriter.flush();
            oStreamWriter.close();

            // HtmlImageGenerator hig=new HtmlImageGenerator();

            // hig.loadHtml(content);
            // hig.saveAsImage(rootPath + childPath + imgName);

            return childPath + fileName;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * base64生成文件
     *
     * @param rootPath
     * @param childPath
     * @param content
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String createBase64File(String rootPath, String childPath,
                                          String content, String fileName) throws IOException {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            File dir = new File(rootPath + childPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(rootPath + childPath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            BASE64Decoder decoder = new BASE64Decoder();

            // Base64解码
            byte[] b = decoder.decodeBuffer(content);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
            return childPath + fileName;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    public static String readFile(String rootPath, String childPath,
                                  String fileName) throws IOException {
        try {

            File file = new File(rootPath + childPath + fileName);
            FileInputStream in = new FileInputStream(file);

            int size = in.available();

            byte[] buffer = new byte[size];

            in.read(buffer);

            in.close();

            String str = new String(buffer, "utf-8");
            return str;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String copyFile(String rootPath, String childPath,
                                  String srcFileName, String descFileName) throws IOException {

        FileInputStream in = new FileInputStream(rootPath + childPath + srcFileName);
        FileOutputStream out = new FileOutputStream(rootPath + childPath + descFileName);
        try {

            IOUtils.copy(in, out);

        } catch (IOException e) {
            return null;
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
        return childPath + descFileName;

    }
    /**
     * 处理导入小数点
     */
    public  static String  numOfImport(Cell cell) {
        String value = new BigDecimal(cell.toString()).toPlainString();
        int i = cell.getCellType();
        if (i == 1) {//字符串类型
            return value;
        } else {
            String[] str = value.split("\\.");
            if (str.length > 1) {
                String str1 = str[1];
                int m = Integer.parseInt(str1);
                if (m == 0) {
                    return str[0];
                } else {
                    return value;
                }
            }else{
                return value;
            }
        }


    }

}