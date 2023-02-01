package final_project_2.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "tests")
//@Builder
@Getter
@Setter
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;

//

    @OneToMany(mappedBy="test")
//    @JoinColumn(name = "question_id")
    private List<Question> questions;


//    @OneToOne(mappedBy = "test")
//    private Question question;
//

//    @Override
//    public String toString() {
//        return "Test{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }

    @Override
    public String toString() { return (name + " (id-" + id + ")");}

}



