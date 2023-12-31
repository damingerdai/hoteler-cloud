package org.daming.hoteler.workflow.pojo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.daming.hoteler.workflow.pojo.enums.Gender;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author gming001
 * @version 2023-12-31 13:20
 */
@Schema(name = "更新客户请求体")
public class UpdateCustomerRequest  implements Serializable {

    private static final long serialVersionUID = 6273140802741108457L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String id;

    private String name;

    private Gender gender;

    private String cardId;

    private long phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public UpdateCustomerRequest() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UpdateCustomerRequest.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("gender=" + gender)
                .add("cardId='" + cardId + "'")
                .add("phone=" + phone)
                .toString();
    }
}
