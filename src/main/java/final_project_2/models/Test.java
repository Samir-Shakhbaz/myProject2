package final_project_2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tests")
@Getter
@Setter
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;

    //here we are creating a one-to-many relationship where "quetion" is parent
    @OneToMany(mappedBy = "test")
    private List<Question> questions;

    @Override
    public String toString() {
        return (name + " (id-" + id + ")");
    }

}



