package lab.model;

public interface Contact {

  ContactType getType();

  String getValue();

  enum ContactType {TELEPHONE, EMAIL}
}
