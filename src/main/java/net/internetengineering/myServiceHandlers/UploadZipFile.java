//package myServiceHandlers;
//
//import com.sun.net.httpserver.HttpExchange;
//import server.MyServiceHandler;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//
///**
// * Created by Hamed Ara on 2/24/2016.
// */
//public class UploadZipFile extends MyServiceHandler {
//    @Override
//    public int executeByStatus(PrintWriter printWriter) throws IOException {
//        return 200;
//    }
//
//    @Override
//    public int executePostRequest(PrintWriter printWriter, HttpExchange t) {
//        System.out.println(t);
//        ZipInputStream zip= new ZipInputStream( t.getRequestBody());
//
//        try {
//        	File classesDirectory = new File("domain\\dealing\\types");
//        	if(!classesDirectory.exists())
//        		classesDirectory.mkdirs();
//
//            ZipEntry ze = zip.getNextEntry();
//            while (ze != null) {
//
//                String fileName = ze.getName();
//                File newFile = new File(classesDirectory.getPath()+"\\"+fileName);
//
//                System.out.println("file unzip : " + newFile.getAbsoluteFile());
//
//                FileOutputStream fos = new FileOutputStream(newFile);
//
//                int len;
//                byte[] buffer = new byte[1024];
//                while ((len = zip.read(buffer)) > 0) {
//                    fos.write(buffer, 0, len);
//                }
//
//                fos.close();
//                ze = zip.getNextEntry();
//            }
//        }catch (IOException ex){
//            System.out.println("error in receive zip file..");
//        }
//        printWriter.println("successful reconfiguration");
//        return 200;
//    }
//}
