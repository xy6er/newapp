package com.example.newapp;

/**
 * User: xy6er
 * Date: 14.05.13
 * Time: 3:20
 */

public class Utils {

    public static String getImageFormat(String imageName)
    {
        imageName.toLowerCase();

        if(imageName.endsWith(".png"))
            return "PNG";
        if(imageName.endsWith(".gif"))
            return "GIF";
        if(imageName.endsWith(".jpg"))
            return "JPG";

        return null;
    }
}
