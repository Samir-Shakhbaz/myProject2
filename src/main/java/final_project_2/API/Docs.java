package final_project_2.API;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Docs {

    @JsonProperty("abstract")
    String absTract;
    String web_url;
    String snippet;
    @JsonProperty("lead_paragraph")
    String leadParagraph;

}
