package confirm_page;

import helpers.AlertBoxHelper;
import helpers.GotoOtherPage;
import helpers.InfoBoxHelper;
import insurance.Insurance;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmController implements Initializable {

    @FXML
    private ScrollPane scroll;

    @FXML
    private ComboBox paymentType;

    @FXML
    private Label price;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField cvv;

    @FXML
    private TextField expirationDateMonth;

    @FXML
    private TextField expirationDateYear;

    @FXML
    private TextField zipCode;

    @FXML
    private VBox cardData;

    @FXML
    private Button submissionButton;

    @FXML
    private ImageView cardTypeImage;

    @FXML
    private Label cardNumberError;

    @FXML
    private Label cvvError;

    @FXML
    private Label expirationDateError;

    @FXML
    private Label zipCodeError;

    private ObservableList<String> insuranceOptions =
            FXCollections.observableArrayList(
                    "Πιστωτική Κάρτα", "Μετρητά"
            );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        cardTypeImage.setImage(new Image("/resources/images/money.png"));
        cardTypeImage.setFitHeight(26);
        cardTypeImage.setFitWidth(50);

        cardNumber.textProperty().addListener((ov, oldValue, newValue) -> {
            if (cardNumber.getText().length() > 16) {
                String s = cardNumber.getText().substring(0, 16);
                cardNumber.setText(s);
            }

            if (!newValue.matches("\\d*")) {
                cardNumber.setText(newValue.replaceAll("[^\\d]", ""));
            }else{
                switch (CardType.detect(cardNumber.getText())) {
                    case VISA:
                        cardTypeImage.setImage(new Image("/resources/images/visa.png"));
                        break;
                    case MASTERCARD:
                        cardTypeImage.setImage(new Image("/resources/images/mastercard.png"));
                        break;
                    case UNKNOWN:
                    default:
                        cardTypeImage.setImage(new Image("/resources/images/money.png"));
                        break;
                }
            }
        });


        addTextLimiter(cvv, 3);
        addTextLimiter(expirationDateMonth, 2);
        addTextLimiter(expirationDateYear, 2);

        acceptOnlyNumbers(cvv);
        acceptOnlyNumbers(expirationDateMonth);
        acceptOnlyNumbers(expirationDateYear);

        paymentType.setItems(insuranceOptions);
        paymentType.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    switch (newValue.toString()) {
                        case "Πιστωτική Κάρτα":
                            cardData.setVisible(true);
                            break;
                        case "Μετρητά":
                            cardData.setVisible(false);
                            break;
                    }
                    submissionButton.setVisible(true);
                });

        submissionButton.setOnAction(event -> {
            if (validate()) {
                try {
                    openMain();
                } catch (IOException | IllegalStateException | NullPointerException e) {
                    AlertBoxHelper.display("Error", "An error has occurred: Internal Error");
                    e.printStackTrace();
                }
            }
        });

    }

    private boolean validate() {
        boolean result = true;

        if(cardNumber.getText().length() == 0){
            cardNumberError.setVisible(true);
            result = false;
        }

        if(cvv.getText().length() == 0){
            cvvError.setVisible(true);
            result = false;
        }

        if(expirationDateMonth.getText().length() == 0 || expirationDateYear.getText().length() == 0){
            expirationDateError.setVisible(true);
            result = false;
        }

        if(zipCode.getText().length() == 0){
            zipCodeError.setVisible(true);
            result = false;
        }

        return result;
    }

    private static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((ov, oldValue, newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }

    private static void acceptOnlyNumbers(final TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void openMain() throws IOException {
        InfoBoxHelper.display("Η δημιουργία συμβολαίου ολοκληρώθηκε", "Η δημιουργία συμβολαίου ολοκληρώθηκε με επιτυχία!");
        GotoOtherPage.mainPage(getClass(), (Stage) submissionButton.getScene().getWindow());
    }

    public void getInsurance(Insurance insurance) {
        price.setText("Συνολικό Ετήσιο Ποσό " + insurance.calculateAmount() + "€");
        System.out.print(insurance.get().toString());
    }
}
