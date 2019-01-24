package lab.model;

public interface Contact {

    enum ContactType { TELEPHONE, EMAIL }

    ContactType getType();
    String getValue();
}
