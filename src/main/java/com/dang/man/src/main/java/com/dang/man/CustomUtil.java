package com.dang.man;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class CustomUtil {
    public static JLabel loadImage(String resource){
        BufferedImage image;
        try{
            InputStream inputStream = CustomUtil.class.getResourceAsStream(resource);
            image = ImageIO.read(inputStream);
            return new JLabel(new ImageIcon(image));
        }catch(Exception e){
            System.out.println("Exception : "+e);
        }
        return null;
    }

    public static void updateImage(JLabel imageContainer,String resource){
        BufferedImage image;
        try{
            InputStream inputStream = CustomUtil.class.getResourceAsStream(resource);
            image = ImageIO.read(inputStream);
            imageContainer.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String hideWords(String word){
        String hiddenWord = "";
        for(int i = 0;i < word.length() ;i++){
            if(!((word.charAt(i)) ==' ')){
                hiddenWord+="*";
            }
            else
                    hiddenWord+=" ";
        }
        return hiddenWord;
    }
}
