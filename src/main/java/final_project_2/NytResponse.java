package final_project_2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class NytResponse {

    String status;
    String copyright;
    @JsonProperty("num-results")
    int numResults;
    List<Article> results;

}
