package com.gerbi.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Calendar;
import java.util.Properties;

public class FileUpload {


    public String upload(Part filePart) throws IOException {

        Properties prop = new Properties();
        InputStream inputStream = FileUpload.class.getClassLoader().getResourceAsStream("/res.properties");
        prop.load(inputStream);
        String pathDir = prop.getProperty("file_path");
        String pathUrl = prop.getProperty("file_url");
        final String fileName = getFileName(filePart);
        String path = "";
        OutputStream out = null;
        InputStream filecontent = null;

        int year = Calendar.getInstance().get(Calendar.YEAR);

        File dir = new File(pathDir+year+"\\");

        if(!dir.exists()){
            dir.mkdirs();
        }

        pathDir = pathDir.charAt(pathDir.length()-1) == '\\' ? pathDir : pathDir + "\\";
        pathUrl = pathUrl.charAt(pathUrl.length()-1) == '/' ? pathUrl : pathUrl + "/";

        pathDir += year+"\\";

        try {
            out = new FileOutputStream(new File(pathDir + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            path = pathUrl + year+ "/" + fileName;

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                try {
                    filecontent.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return path;
    }


    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
