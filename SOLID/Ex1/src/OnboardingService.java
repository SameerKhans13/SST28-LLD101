import java.util.*;

public class OnboardingService {
    private final IStudentRepository repository;
    private final InputParser parser;
    private final StudentValidator validator;
    private final RegistrationPresenter presenter;

    public OnboardingService(IStudentRepository repository, 
                             InputParser parser, 
                             StudentValidator validator, 
                             RegistrationPresenter presenter) {
        this.repository = repository;
        this.parser = parser;
        this.validator = validator;
        this.presenter = presenter;
    }

    public void registerFromRawInput(String raw) {
        presenter.printInput(raw);

        Map<String, String> data = parser.parse(raw);
        List<String> errors = validator.validate(data);

        if (!errors.isEmpty()) {
            presenter.printErrors(errors);
            return;
        }

        String name = data.get("name");
        String email = data.get("email");
        String phone = data.get("phone");
        String program = data.get("program");

        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        repository.save(rec);

        presenter.printSuccess(rec, repository.count());
    }
}
