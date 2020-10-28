package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyItineraryAttractionList;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;


public class ItineraryAttractionList implements ReadOnlyItineraryAttractionList {

    //    private Itinerary currentItinerary;
    private ObservableList<ItineraryAttraction> internalList = FXCollections.observableArrayList();
    //    private final ObservableList<ItineraryAttraction> internalUnmodifiableList =
    //            FXCollections.unmodifiableObservableList(internalList);

    private ItineraryAttraction testingItineraryAttraction = new ItineraryAttraction(
            new Attraction(new Name("test"), new Phone(), new Email(), new Address(), new Description(),
                    new Location("Test"), new OpeningHours(), new PriceRange(), new Rating(), new Visited(),
                    new HashSet<>()), new ItineraryTime("0000"), new ItineraryTime("0100"));

    public ItineraryAttractionList(Itinerary currentItinerary) {
        setItineraryAttractionList(currentItinerary);
    }

    public void setItineraryAttractionList(Itinerary currentItinerary) {
        requireNonNull(currentItinerary);
        internalList = FXCollections.observableArrayList();
        for (Day day : currentItinerary.getDays()) {
            internalList.add(new ItineraryAttractionPlaceholder(testingItineraryAttraction,
                    Integer.parseInt(day.value)));
            for (ItineraryAttraction itineraryAttraction : day.getItineraryAttractions()) {
                internalList.add(itineraryAttraction);
            }
        }
    }

    public ObservableList<ItineraryAttraction> getItineraryAttractionList() {
        return internalList;
    }
}