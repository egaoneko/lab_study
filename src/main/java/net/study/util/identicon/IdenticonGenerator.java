package net.study.util.identicon;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/17/15 | 1:05 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public class IdenticonGenerator {
    public static int height = 5;
    public static int width = 5;

    public static BufferedImage generate(String userName, HashGeneratorInterface hashGenerator) {
        byte[] hash = hashGenerator.generate(userName);

        BufferedImage identicon = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = identicon.getRaster();

        //get byte values as unsigned ints
        int r = hash[0] & 255;
        int g = hash[1] & 255;
        int b = hash[2] & 255;

        int [] background = new int [] {255,255,255, 0};
        int [] foreground = new int [] {r, g, b, 255};

        for(int x=0 ; x < width ; x++) {

            int i = x < 3 ? x : 4 - x;
            for(int y=0 ; y < height; y++) {
                int [] pixelColor;

                if((hash[i] >> y & 1) == 1)
                    pixelColor = foreground;
                else
                    pixelColor = background;

                raster.setPixel(x, y, pixelColor);
            }
        }

        return identicon;
    }
}