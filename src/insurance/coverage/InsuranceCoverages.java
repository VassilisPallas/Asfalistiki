package insurance.coverage;

import java.util.ArrayList;
import java.util.List;

public class InsuranceCoverages {
    public static List<Coverage> healthCoverages() {
        return new ArrayList<Coverage>() {{
            add(new Coverage("Αίτηση ασφάλισης", "Η πρόταση, γραπτή ή προφορική, του υποψηφίου ασφαλιζόμενου για τον κίνδυνο που επιθυμεί να ασφαλίσει. Συνήθως γίνεται με τη συμπλήρωση γραπτής αίτησης σε έντυπο του ασφαλιστικού συνεργάτη και υπογράφεται από τον υποψήφιο ασφαλιζόμενο. Εάν η πρόταση γίνει αποδεκτή από τον ασφαλιστικό συνεργάτη, συνάπτεται η ασφαλιστική σύμβαση.", 600));
            add(new Coverage("Νοσοκομειακή περίθαλψη", "Καλύπτει τις δαπάνες που έγιναν στο νοσοκομείο. Για να αποζημιωθεί ο ασφαλισμένος, χρειάζεται να προσκομισθούν τα πρωτότυπα τιμολόγια. Ο ασφαλισμένος αποζημιώνεται για τις πραγματικές δαπάνες που πραγματοποίησε στο νοσοκομείο.", 120));
        }};
    }

    public static List<Coverage> vehicleCoverages() {
        return new ArrayList<Coverage>() {{
            add(new Coverage("Κλοπή", "Καλύπτεται η ολική κλοπή του οχήματος. Η αποζημίωση που καταβάλλεται ανταποκρίνεται στην τρέχουσα εμπορική αξία του αυτοκινήτου κατά την ημέρα κλοπής.", 100));
            add(new Coverage("Οδική Βοήθεια", "Σε περίπτωση που το ασφαλισμένο αυτοκίνητο ακινητοποιηθεί, από οποιαδήποτε βλάβη, η οδική βοήθεια αναλαμβάνει να αποκαταστήσει τη ζημιά επί τόπου, εάν αυτό είναι εφικτό. Εάν δεν είναι, θα αναλάβει τη μεταφορά του σε ένα κοντινό συνεργείο ή να το επαναπατρίσει στην έδρα του ασφαλισμένου.", 300));
        }};
    }

    public static List<Coverage> homeCoverages() {
        return new ArrayList<Coverage>() {{
            add(new Coverage("Πυρκαγιά", "Καλύπτονται οι υλικές ζημιές από πυρκαγιά, πτώση κεραυνού και έκρηξη.", 200));
            add(new Coverage("Σεισμός", "Καλύπτονται οι ζημιές που προκαλούνται στο απίτι σας από σεισμό.", 300));
        }};
    }
}
