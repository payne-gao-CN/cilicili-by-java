package live.cilicili.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: payne
 * @createDate: 2022/9/29 9:56
 * @description:
 */
public class WebpUtil {
    public static void imageToWebp(MultipartFile file) throws IOException {
//        BufferedImage image = ImageIO.read(file.getInputStream());
//        ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();
//        WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
//        //Notify encoder to consider WebPWriteParams
//        writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//        //Set lossy compression
//        writeParam.setCompressionType(writeParam.getCompressionTypes()[WebPWriteParam.LOSSY_COMPRESSION]);
//        //Set 80% quality. Allowed values are between 0 and 1
//        writeParam.setCompressionQuality(0.7f);
//        // Save the image
//        // Save the image
//        writer.setOutput(new FileImageOutputStream(new File("E:\\java\\cilicili-by-java\\webp")));
//        writer.write(null, new IIOImage(image, null, null), writeParam);
    }
}
