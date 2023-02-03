package final_project_2.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "answers")
//@Builder
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    Long id;
    String name;
    boolean correct;

    public Answer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToOne (
//            cascade = CascadeType.ALL,
            optional = true
    )
    private Question question;

    @Override
    public String toString() { return (name + " (id-" + id + ")");}

}
