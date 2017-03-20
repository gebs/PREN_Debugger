package ch.hslu.pren.team8.common;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by gebs on 3/17/17.
 */
public class LogMessageImage extends LogMessageBase implements Serializable {
    transient BufferedImage image;
    byte[] imageBytes;
    int imageSize;
    ImageType imageType;

    public void setImage(BufferedImage image) {
        this.image = image;
        ByteArrayOutputStream bScrn = new ByteArrayOutputStream();
        try {
            ImageIO.write(this.image,"JPG",bScrn);
            byte[] imgByte = bScrn.toByteArray();
            bScrn.flush();
            bScrn.close();
            this.imageSize = bScrn.size();
            this.imageBytes = imgByte;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getImage() {
        if (image == null && imageBytes.length > 0)
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            try {
                return image = ImageIO.read(bais);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return image;
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }
    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

}
