package final_project_2.controllers;

import final_project_2.models.Answer;
import final_project_2.repositories.AnswerRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("test")
public class AnswerRepoTest implements CommandLineRunner {

    @Autowired
    AnswerRepository answerRepository;

//    @Autowired
//    Answer answer;


    @Override
    public void run(String... args) throws Exception {

        if (answerRepository.findAll().isEmpty()) {

//Answer answer = Answer.builder().

        }
    }
}


