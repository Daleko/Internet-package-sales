/**
 *
 * @author Aleksandra George
 */
package model;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InternetSales {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id", -1);
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    private final StringProperty address = new SimpleStringProperty(this, "address", "");
    private final ObjectProperty<Speed> speed = new SimpleObjectProperty(this, "speed", Speed.Speed);
    private final ObjectProperty<Flow> flow = new SimpleObjectProperty(this, "flow", Flow.Flow);
    private final ObjectProperty<ContractLength> contractLength = new SimpleObjectProperty(this, "contractLength", ContractLength.Contract_lenght);

    public InternetSales() {
    }

    public InternetSales(String firstName, String lastName, String address, Speed speed, Flow flow, ContractLength length) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.address.set(address);

        this.speed.set(speed);
        this.flow.set(flow);
        this.contractLength.set(length);
    }

    public InternetSales(InternetSales contract) {
        this.id.set(contract.getId());
        this.firstName.set(contract.getFirstName());
        this.lastName.set(contract.getLastName());
        this.address.set(contract.getAddress());
        this.speed.set(contract.getSpeed());
        this.flow.set(contract.getFlow());
        this.contractLength.set(contract.getContractLength());
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public Speed getSpeed() {
        return speed.get();
    }

    public void setSpeed(Speed speed) {
        this.speed.set(speed);
    }

    public ObjectProperty<Speed> speedProperty() {
        return speed;
    }

    public Flow getFlow() {
        return flow.get();
    }

    public void setFlow(Flow flow) {
        this.flow.set(flow);
    }

    public ObjectProperty<Flow> flowProperty() {
        return flow;
    }

    public ContractLength getContractLength() {
        return contractLength.get();
    }

    public void setContractLength(ContractLength contractType) {
        this.contractLength.set(contractType);
    }

    public ObjectProperty<ContractLength> contractLengthProperty() {
        return contractLength;
    }

    private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());

    public ObjectProperty<ArrayList< String>> errorsProperty() {
        return errorList;
    }

    public boolean isValid() {
        boolean isValid = true;

        if (firstName.get() == null || firstName.get().equals("")) {
            errorList.getValue().add("You must enter first name!");
            isValid = false;
        }

        if (lastName.get() == null || lastName.get().equals("")) {
            errorList.getValue().add("You must enter last name!");
            isValid = false;
        }

        if (address.get() == null || address.get().equals("")) {
            errorList.getValue().add("You must enter adress!");
            isValid = false;
        }

        if (speed.get() == null || speed.get().equals(Speed.Speed)) {
            errorList.getValue().add("You must enter choosen speed!");
            isValid = false;
        }

        if (flow.get() == null || flow.get().equals(Flow.Flow)) {
            errorList.getValue().add("You must enter choosen flow!");
            isValid = false;
        }

        if (contractLength.get() == null || contractLength.get().equals(ContractLength.Contract_lenght)) {
            errorList.getValue().add("You must enter choosen contract lenght!");
            isValid = false;
        }

        return isValid;
    }

    @Override
    public String toString() {
        return "First name: " + firstName.get() + "\n"
                + "Last name: " + lastName.get() + "\n"
                + "Address: " + address.get() + "\n"
                + "Speed: " + speed.get().toString() + "\n"
                + "Flow: " + flow.get().toString() + "\n"
                + "Contract lenght: " + contractLength.get().toString();
    }

    public enum Speed {

        Speed(-1),
        MBIT_2(2),
        MBIT_5(5),
        MBIT_10(10),
        MBIT_20(20),
        MBIT_50(50),
        MBIT_100(100);

        private final int value;

        private Speed(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Speed valueOf(int value) {
            for (Speed speed : Speed.values()) {
                if (speed.getValue() == value) {
                    return speed;
                }
            }
            return Speed;
        }

        @Override
        public String toString() {
            if (this == Speed) {
                return "Speed";
            }
            return getValue() + " Mbit";
        }
    }

    public enum Flow {

        Flow(-1),
        GB_1(1),
        GB_5(5),
        GB_10(10),
        GB_100(100),
        FLAT(Integer.MAX_VALUE);

        private final int value;

        private Flow(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Flow valueOf(int value) {
            for (Flow flow : Flow.values()) {
                if (flow.getValue() == value) {
                    return flow;
                }
            }
            return Flow;
        }

        @Override
        public String toString() {
            if (this == Flow) {
                return "Flow";
            }
            if (this == FLAT) {
                return "Flat";
            }
            return getValue() + " GB";
        }
    }

    public enum ContractLength {

        Contract_lenght(-1),
        Year_1(1),
        Year_2(2);

        private final int value;

        private ContractLength(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static ContractLength valueOf(int value) {
            for (ContractLength length : ContractLength.values()) {
                if (length.getValue() == value) {
                    return length;
                }
            }
            return Contract_lenght;
        }

        @Override
        public String toString() {
            if (this == Contract_lenght) {
                return "Contract Lenght";
            }
            return getValue() + " Years";
        }
    }
}
