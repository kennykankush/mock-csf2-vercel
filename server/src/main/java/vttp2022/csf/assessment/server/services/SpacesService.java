package vttp2022.csf.assessment.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;

@Service
public class SpacesService {

    @Autowired
    private AmazonS3 amazonS3;

    public String getDefaultImage(){
        return amazonS3.getUrl("nusiss", "lazy.png").toString();
    }


    
}
