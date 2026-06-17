package bizmart.backend.contact.dto;

import java.time.LocalDateTime;

import bizmart.backend.contact.entity.ContactStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactResponse {

    private Long id;

    private Long interestId;

    private String name;

    private String email;

    private String mobile;

    private String message;

    private ContactStatus status;

    private LocalDateTime createdAt;
}