package final_project_2.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "questions")
//@Builder
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;

//    @OneToOne
//    @JoinColumn (name = "test_id")
//    private Test test;

    @OneToOne
    @JoinColumn (name = "correct_answers_id")
    private CorrectAnswer correctAnswer;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @ManyToOne(
//            cascade = CascadeType.ALL,
            optional = true
    )
    private Test test;


    @Override
    public String toString() { return (name + " (id-" + id + ")");}

    //    @OneToMany
//    private List <Answer> answers;
}

