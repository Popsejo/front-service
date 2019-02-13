package kdg.microservices.client.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kdg.microservices.client.model.School;
import kdg.microservices.client.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SchoolService {
    @Autowired
    private StudentService studentService;



    public List<School> getSchools() throws IOException {
        String schoolPad = "http://localhost:8082/schoolservice/";
        String result = studentService.getResult(schoolPad);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(result, new TypeReference<List<School>>() {
        });
    }
}
