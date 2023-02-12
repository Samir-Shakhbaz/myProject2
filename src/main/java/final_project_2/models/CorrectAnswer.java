package final_project_2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "correct_answers")
@Getter
@Setter
public class CorrectAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    Long id;
    String name;

    public CorrectAnswer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToOne(mappedBy = "correctAnswer")
    private Question question;


}
