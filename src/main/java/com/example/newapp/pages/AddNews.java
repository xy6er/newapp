package com.example.newapp.pages;

import com.example.newapp.entities.News;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.hibernate.Session;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class AddNews {

    @Property
    private News news;

    @Inject
    private Session session;

    @InjectPage
    private Index index;

    @CommitAfter
    Object onSuccess()
    {
        session.persist(news);

        return index;
    }

    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    @Property
    private UploadedFile file;

    public void onSuccessFrom() throws Exception
    {
        File uploadFile = new File("photo/" + file.getFileName());
        if(getFormat(file.getFileName()) == null) {
            message = "Выберите файл с изображением";
            return;
        }

        file.write(uploadFile);
        BufferedImage image = ImageIO.read(uploadFile);
        BufferedImage scaleImage = Scalr.resize(image, 150);

        File outputfile = new File("photo/scaleImage" + file.getFileName());
        ImageIO.write(scaleImage, getFormat(file.getFileName()), outputfile);

        message = "Изображение успешно добавлена!";
    }

    private String getFormat(String imageName)
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
