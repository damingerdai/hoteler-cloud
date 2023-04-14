package org.daming.hoteler.workflow.pojo;

import org.daming.hoteler.workflow.pojo.enums.Gender;
import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author daming
 * @version 2023-04-14 17:40
 **/
public class Customer implements Serializable {

    private static final long serialVersionUID = -6886394623640158367L;

    private String id;

    private String name;

    private Gender gender;

    private String cardId;

    private long phone;

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Customer setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getCardId() {
        return cardId;
    }

    public Customer setCardId(String cardId) {
        this.cardId = cardId;
        return this;
    }

    public long getPhone() {
        return phone;
    }

    public Customer setPhone(long phone) {
        this.phone = phone;
        return this;
    }

    public Customer() {
        super();
    }

    public Customer(String id, String name, Gender gender, String cardId, long phone) {
        super();
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.cardId = cardId;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("gender=" + gender)
                .add("cardId='" + cardId + "'")
                .add("phone=" + phone)
                .toString();
    }
}
