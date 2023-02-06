package final_project_2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    //here we creating a many-to-one relationship with "answer" as a parent
    @ManyToOne(
            optional = true
    )
    private Question question;

    @Override
    public String toString() {
        return (name + " (id-" + id + ")");
    }

}
