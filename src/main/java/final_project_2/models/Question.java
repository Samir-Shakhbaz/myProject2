package final_project_2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "questions")
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "correct_answers_id")
    private CorrectAnswer correctAnswer;

    //here we are creating a one-to-many relationship with 'answer', where 'answer' is parent
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    //here we are creating a many-to-ne relationship with 'test', where 'question' is parent
    @ManyToOne(
            optional = true
    )
    private Test test;

    @Override
    public String toString() {
        return (name + " (id-" + id + ")");
    }

}

