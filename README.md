# ImageProcessing

## 1.make Binary and grayscale images by colorful image
**Gray-scale**: in RGB model, if R=G=B, color represents a grayscale color, where R=G=B value is called grayscale value. Therefore, each pixel of grayscale image needs only one byte to store the grayscale value (also known as intensity value and brightness value), and the grayscale range is 0-255. The weighted average method is commonly used to obtain the gray value of each pixel.
```
BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY); //Attention:the third param
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
                System.out.println(grayImage.getRGB(i,j));
            }
        }
```
**Binarization**: binarization of an image means setting the gray value of the pixels on the image to 0 or 255, which means presenting the entire image with an obvious visual effect of only black and white.
```
BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
//Attention:the third param:BufferedImage.TYPE_BYTE_BINARY
for (int i = height - 1; i >= 0; i--) {
    for (int j = 0; j < width; j++) {
        int rgb = image.getRGB(i, j);
        //System.out.println(rgb);
        binaryImage.setRGB(i, j, rgb);
        //if(-1 != binaryImage.getRGB(i,j)) System.out.println(binaryImage.getRGB(i,j));
    }
}
```

## 2.using LBS to hide information
### 2-1 Use LSB algorithm to hide your name in a color image(R channel).
some coding as followed.
```
ReadBmp bmp = new ReadBmp();
bmp.init("/Users/janelin/Desktop/Lena.bmp");

BinaryOrGrayImage demo = new BinaryOrGrayImage();
demo.binaryImage();
File file = new File("/Users/janelin/Desktop/binaryImage.jpg");
BufferedImage binaryImage = ImageIO.read(file);

int binaryH= binaryImage.getHeight();
int binaryW = binaryImage.getWidth();
int[][] finalBlue = bmp.getBlue();
int[][] finalGreen=bmp.getGreen();
int[][] finalRed = bmp.getRed();
int[][] addImagePixel = new int[binaryImage.getHeight()][binaryImage.getWidth()];

Color pointArray[][] = new Color[bmp.getHeight()][bmp.getWidth()];
        for(int i =0;i<bmp.getHeight();i++){
            for(int j=0;j<bmp.getWidth();j++){
                if(1 == finalRed[i][j] % 2) {
                    finalRed[i][j] = finalRed[i][j]-1;
                }
                if(1 == finalBlue[i][j] % 2) {
                    finalBlue[i][j] = finalBlue[i][j]-1;
                }
                if(1 == finalGreen[i][j] % 2) {
                    finalGreen[i][j] = finalGreen[i][j]-1;
                }

                addImagePixel[i][j] = binaryImage.getRGB(j,i);
                finalRed[i][j] = (finalRed[i][j] +addImagePixel[i][j])%255;
                if(255 < finalRed[i][j]) finalRed[i][j] %= 255;
                while(0 > finalRed[i][j]) {finalRed[i][j] +=255;};
                //finalRed[i][j] = ((finalRed[i][j] +addImagePixel[i][j])%255+255)%255;
                //finalBlue[i][j] = ((finalBlue[i][j] +addImagePixel[i][j])%255+255)%255;
                //finalGreen[i][j] = ((finalGreen[i][j] +addImagePixel[i][j])%255+255)%255;
                System.out.println(finalRed[i][j]);
                pointArray[i][j] = new Color(finalRed[i][j],finalGreen[i][j],finalBlue[i][j]);
            }
        }
        WriteBmp writeBmp = new WriteBmp(pointArray,"/Users/janelin/Desktop/temp.bmp");


```
### 2-2 You can extract hidden information from a picture.
some coding as followed.
```
        ReadBmp readGroupBmp = new ReadBmp();
        readGroupBmp.init("/Users/janelin/Desktop/temp.bmp");
        finalRed = readGroupBmp.getRed();
        finalBlue = readGroupBmp.getBlue();
        finalGreen = readGroupBmp.getGreen();
        File file1 = new File("/Users/janelin/Desktop/temp.bmp");
        BufferedImage tempImage = ImageIO.read(file1);
        for(int i =0;i<bmp.getHeight();i++){
            for(int j=0;j<bmp.getWidth();j++){
                if(1 == finalRed[i][j] % 2) {
                    finalRed[i][j] = 1;
                }
                pointArray[i][j] = new Color(finalRed[i][j],0,0);
            }
        }
        WriteBmp writeSubstractBmp = new WriteBmp(pointArray,"/Users/janelin/Desktop/substract.bmp");

```

## 3.to be continued
