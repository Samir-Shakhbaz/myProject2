//package final_project_2;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ArticleService {
//
//    @Value("${api_key}")
//    private String apikey;
//
//    @Value("${mostPopularUrl}")
//    private String mostPopularUrl;
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    public List<Article> getMostPopular(){
////        Map<String, String> mapMy = new HashMap<>();
////        mapMy.put("api-key", apikey);
////        NytResponse response = restTemplate.getForObject(mostPopularUrl, NytResponse.class, mapMy);
//        NytResponse response = restTemplate.getForObject(mostPopularUrl + "api-key=" + apikey, NytResponse.class);
////        response.getResults().forEach(article -> {
////           if(article.getMedia().size()>0) article.setImageUrl(article.getMedia().get(0).getMediaMetadata().get(0).getUrl());
////        });
//        for(Article article: response.getResults()){
//            if(article.getMedia().size()>0) article.setImageUrl(article.getMedia().get(0).getMediaMetadata().get(0).getUrl());
//        }
//        List<Article> results = new ArrayList<>();
//        if (response != null && response.getStatus().equals("OK")) {
//            return response.getResults();
//        } else {
//            return results;
//        }
//    }
//}
