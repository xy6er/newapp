package com.example.newapp.pages;

import com.example.newapp.entities.News;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
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
import java.util.Date;

import static com.example.newapp.Utils.getImageFormat;


public class AddNews {

    @Property
    private News news;

    @Inject
    private Session session;

    @InjectPage
    private Index index;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    @Property
    private UploadedFile file;

    @Property
    private String newsTitle;
    @Property
    private String newsText;
    @Property
    private Date date;


    @CommitAfter
    @OnEvent(component = "uploadButton", value = "selected")
    Object onSuccessForm() throws Exception
    {
        News news = new News();
        news.title = newsTitle;
        news.text = newsText;
        news.date = date;
        session.persist(news);

        if(getImageFormat(file.getFileName()) == null) {
            message = "Выберите файл с изображением";
            return null;
        }

        File dir = new File("src/main/webapp/images/news" + news.id);
        dir.mkdir();

        File imageFile = new File(dir.getPath() + "/" + file.getFileName());
        file.write(imageFile);
        news.imageUrl = "images/news" + news.id + "/" + imageFile.getName();
        BufferedImage image = ImageIO.read(imageFile);

        if(image.getWidth() > 200 || image.getHeight() > 200) {
            BufferedImage scaledImage = Scalr.resize(image, 150);
            File scaledImageFile = new File(dir.getPath() + "/scaled" + file.getFileName());
            ImageIO.write(scaledImage, getImageFormat(file.getFileName()), scaledImageFile);
            news.scaledImageUrl = "images/news" + news.id + "/" + scaledImageFile.getName();
        }

        message = "Изображение успешно добавлена!";
        return index;
    }

}
