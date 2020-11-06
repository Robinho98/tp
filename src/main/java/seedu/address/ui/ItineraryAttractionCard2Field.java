package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import seedu.address.model.itinerary.ItineraryAttraction;

public class ItineraryAttractionCard2Field extends ItineraryAttractionCard {

    private static final String FXML = "ItineraryAttractionListCard2Field.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final ItineraryAttraction itineraryAttraction;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label startEndTime;

    @FXML
    // For JavaFX, rename location to locale as location is a reserved keyword in JavaFX
    private Label locale;

    //optional fields
    @FXML
    private Label field1;
    @FXML
    private Label field2;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code AttractionCode} with the given {@code Attraction} and index to display.
     */
    public ItineraryAttractionCard2Field(ItineraryAttraction itineraryAttraction) {
        super(itineraryAttraction, FXML);

        this.itineraryAttraction = itineraryAttraction;

        id.setText(itineraryAttraction.getIndex() + ". ");

        name.setText(itineraryAttraction.getName().fullName);
        name.setWrapText(true);

        locale.setText("\uD83C\uDF0E " + itineraryAttraction.getLocation().value);
        locale.setWrapText(true);

        startEndTime.setText("\uD83D\uDD56 " + itineraryAttraction.getStartTime().toString()
                + "-"
                + itineraryAttraction.getEndTime().toString());
        startEndTime.setWrapText(true);

        //optional fields

        int fieldsFilled = 0;

        if (!itineraryAttraction.getAddress().value.isEmpty()) {
            String address = "\uD83C\uDFE0 " + itineraryAttraction.getAddress().value;
            field1.setText(address);

            fieldsFilled++;
        }

        if (!itineraryAttraction.getPhone().value.isEmpty()) {
            String phone = "📞 " + itineraryAttraction.getPhone().value;
            if (fieldsFilled == 0) {
                field1.setText(phone);
            } else {
                field2.setText(phone);
            }
            fieldsFilled++;
        }

        if (!itineraryAttraction.getEmail().value.isEmpty()) {
            String email = "\uD83D\uDCE7 " + itineraryAttraction.getEmail().value;
            if (fieldsFilled == 0) {
                field1.setText(email);
            } else if (fieldsFilled == 1) {
                field2.setText(email);
            }
            fieldsFilled++;
        }

        if (!itineraryAttraction.getOpeningHours().value.isEmpty()) {
            String openingHours = "\uD83C\uDE3A " + itineraryAttraction.getOpeningHours().value;
            if (fieldsFilled == 0) {
                field1.setText(openingHours);
            } else if (fieldsFilled == 1) {
                field2.setText(openingHours);
            }
            fieldsFilled++;
        }

        if (!itineraryAttraction.getDescription().value.isEmpty()) {
            String description = itineraryAttraction.getDescription().value;
            if (fieldsFilled == 0) {
                field1.setText(description);
            } else {
                field2.setText(description);
            }
        }

        field1.setWrapText(true);
        field2.setWrapText(true);

        if (itineraryAttraction.getPriceRange().toString() != "") {
            Label priceRange = new Label(itineraryAttraction.getPriceRange().toString());
            priceRange.setStyle("-fx-background-color: #800;");
            tags.getChildren().add(priceRange);
        }

        if (itineraryAttraction.getRating().toString() != "") {
            Label rating = new Label(itineraryAttraction.getRating().toString() + "\u2605");
            rating.setStyle("-fx-background-color: #080;");
            tags.getChildren().add(rating);
        }

        if (itineraryAttraction.getVisited().toString() != "") {
            Label visited = new Label(itineraryAttraction.getVisited().toString());
            visited.setText(itineraryAttraction.getVisited().toString());
            visited.setStyle("-fx-background-color: #9933ff;");
            tags.getChildren().add(visited);
        }

        itineraryAttraction.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

}
