package  org.daming.hoteler.workflow.pojo.request;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author daming
 * @version 2023-04-08 14:10
 **/
public class CreateRoomRequest implements Serializable {

    private static final long serialVersionUID = 3097150561081198317L;

    private String name;

    private double price;

    public String getName() {
        return name;
    }

    public CreateRoomRequest setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public CreateRoomRequest setPrice(double price) {
        this.price = price;
        return this;
    }

    public CreateRoomRequest() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CreateRoomRequest.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("price=" + price)
                .toString();
    }
}
