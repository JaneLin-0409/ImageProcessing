package InformationSecurityHomeWork;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BinaryOrGrayImage  {

    public void binaryImage() throws IOException {
        File file = new File("/Users/janelin/Desktop/Name.jpg");
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                int rgb = image.getRGB(i, j);
                //System.out.println(rgb);
                binaryImage.setRGB(i, j, rgb);
                //if(-1 != binaryImage.getRGB(i,j)) System.out.println(binaryImage.getRGB(i,j));
            }
        }

        File newFile = new File("/Users/janelin/Desktop/binaryImage.jpg");
        ImageIO.write(binaryImage, "jpg", newFile);
        System.out.println("Binary!!!!!");
    }

    public void grayImage() throws IOException {
        File file = new File("/Users/janelin/Desktop/Name.jpg");
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_GRAY
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
                System.out.println(grayImage.getRGB(i,j));
            }
        }

        File newFile = new File("/Users/janelin/Desktop/GrayImage.jpg");
        ImageIO.write(grayImage, "jpg", newFile);
    }
    
}
