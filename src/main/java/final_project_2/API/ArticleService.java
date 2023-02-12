package final_project_2.API;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Value("${api_key}")
    private String apikey;
    @Value("${articleSearchUrl}")
    private String articleSearchUrl;

    @Autowired
    RestTemplate restTemplate;


    public List<Docs> getSearchJava() {

        ArticleStatus articleStatus = restTemplate.getForObject(articleSearchUrl + "q=computers@java" + "&api-key=" + apikey, ArticleStatus.class);
        System.out.println(articleStatus);

        List<Docs> results = new ArrayList<>();
        if (articleStatus != null && articleStatus.getStatus().equals("OK")) {
            for (Docs docs : articleStatus.getResponse().getDocs()) {
                results.add(docs);
            }
        }
        return results;

    }

}
