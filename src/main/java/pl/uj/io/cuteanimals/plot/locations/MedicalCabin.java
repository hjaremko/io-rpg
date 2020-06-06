package pl.uj.io.cuteanimals.plot.locations;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.NPC;
import pl.uj.io.cuteanimals.model.interfaces.DefaultLocation;
import pl.uj.io.cuteanimals.model.interfaces.IAction;

public class MedicalCabin extends DefaultLocation {
    private Map<String, IAction> availableActions;
    private List<NPC> npcList;

    public MedicalCabin() {
        super();
        this.description =
                "You woke up with horrible headache... You see that someone leans over you.";
    }
}
