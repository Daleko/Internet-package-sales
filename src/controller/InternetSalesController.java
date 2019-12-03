/**
 *
 * @author Aleksandra George
 */
package controller;

import model.InternetSales;
import model.InternetSales.ContractLength;
import model.InternetSales.Flow;
import model.InternetSales.Speed;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class InternetSalesController implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private ComboBox<InternetSales.Speed> speedCombo;
    @FXML
    private ComboBox<InternetSales.Flow> flowCombo;
    @FXML
    private ComboBox<InternetSales.ContractLength> contractLengthCombo;
    @FXML
    private TableView<InternetSales> contractsTable;
    @FXML
    private TableColumn<InternetSales, Integer> idColumn;
    @FXML
    private TableColumn<InternetSales, String> firstNameColumn;
    @FXML
    private TableColumn<InternetSales, String> lastNameColumn;
    @FXML
    private TableColumn<InternetSales, String> addressColumn;
    @FXML
    private TableColumn<InternetSales, String> speedColumn;
    @FXML
    private TableColumn<InternetSales, String> flowColumn;
    @FXML
    private TableColumn<InternetSales, String> contractLengthColumn;
    @FXML
    private TableColumn<InternetSales, InternetSales> deleteColumn;

    ObservableList<InternetSales> contracts = FXCollections.<InternetSales>observableArrayList();
    InternetSales contract;

    public InternetSalesController() {
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        initializeSalesTable();
        initializeSalesForm();
    }

    public void initializeSalesForm() {
        contract = new InternetSales();
        firstName.textProperty().bindBidirectional(contract.firstNameProperty());
        lastName.textProperty().bindBidirectional(contract.lastNameProperty());
        address.textProperty().bindBidirectional(contract.addressProperty());
        speedCombo.getItems().setAll(Speed.values());
        speedCombo.getSelectionModel().select(Speed.Speed);
        speedCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Speed>() {
            @Override
            public void changed(ObservableValue<? extends Speed> selected, Speed oldSpeed, Speed newSpeed) {
                contract.setSpeed(newSpeed);
            }
        });

        flowCombo.getItems().setAll(Flow.values());
        flowCombo.getSelectionModel().select(Flow.Flow);
        flowCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Flow>() {
            @Override
            public void changed(ObservableValue<? extends Flow> selected, Flow oldFlow, Flow newFlow) {
                contract.setFlow(newFlow);
            }
        });

        contractLengthCombo.getItems().setAll(ContractLength.values());
        contractLengthCombo.getSelectionModel().select(ContractLength.Contract_lenght);
        contractLengthCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ContractLength>() {
            @Override
            public void changed(ObservableValue<? extends ContractLength> selected, ContractLength oldLength, ContractLength newLength) {
                contract.setContractLength(newLength);
            }
        });
    }

    public void initializeSalesTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        flowColumn.setCellValueFactory(new PropertyValueFactory<>("flow"));
        contractLengthColumn.setCellValueFactory(new PropertyValueFactory<>("contractLength"));

        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteColumn.setCellFactory((TableColumn<InternetSales, InternetSales> param) -> new TableCell<InternetSales, InternetSales>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(InternetSales contract, boolean empty) {

                if (contract == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        contractsTable.getItems().remove(contract);
                        contracts.remove(contract);
                        System.out.println("Contract is deleted:");
                        System.out.println(contract);
                    }
                });
            }
        });

        contractsTable.getItems().setAll(contracts);
    }

    @FXML
    private void saveContract() {
        if (contract.isValid()) {
            InternetSales last = contracts.size() > 0 ? contracts.get(contracts.size() - 1) : null;
            int id = last != null ? last.getId() : 0;

            InternetSales newContract = new InternetSales(contract);
            newContract.setId(id + 1);

            contracts.add(newContract);
            contractsTable.getItems().add(newContract);
            System.out.println("Contract is entered:");
            System.out.println(newContract);

        } else {

            StringBuilder errMsg = new StringBuilder();

            ArrayList<String> errList = contract.errorsProperty().get();

            for (String err : errList) {
                errMsg.append(err);
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Please fill all filds");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();
        }

    }

    @FXML
    private void clearContractForm() {

        contract.setFirstName("");
        contract.setLastName("");
        contract.setAddress("");

        contract.setSpeed(Speed.Speed);
        speedCombo.getSelectionModel().clearSelection();

        contract.setFlow(Flow.Flow);
        flowCombo.getSelectionModel().clearSelection();

        contract.setContractLength(ContractLength.Contract_lenght);
        contractLengthCombo.getSelectionModel().clearSelection();
        System.out.println("Ready for new contract");
    }

    @FXML
    private void closeForm() {
        Platform.exit();
    }
}
