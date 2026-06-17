package bizmart.backend.contact.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {

    private Long interestId;

    private String name;

    private String email;

    private String mobile;

    private String message;
}