package lab.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

import static lab.model.Contact.ContactType.TELEPHONE;

@Value
@Builder
public class ContactImpl implements Contact {

    @Default
    ContactType type = TELEPHONE;

    String value;
}
