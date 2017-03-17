package ch.hslu.pren.team8.common;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by gebs on 3/17/17.
 */
public class LogMessageImage extends LogMessageBase implements Serializable {
    BufferedImage image;

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
