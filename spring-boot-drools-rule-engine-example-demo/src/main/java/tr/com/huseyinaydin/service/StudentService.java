package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.StudentRequest;
import tr.com.huseyinaydin.dto.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

/**
 *
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot Examples
 *
 **/

@Service
@RequiredArgsConstructor
public class StudentService {
    private final KieContainer kieContainer;

    public StudentResponse studentResponse(StudentRequest studentRequest) {
        StudentResponse studentResp = new StudentResponse();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("studentResp", studentResp);
        kieSession.insert(studentRequest);
        kieSession.fireAllRules();
        kieSession.dispose();;
        return studentResp;
    }
}