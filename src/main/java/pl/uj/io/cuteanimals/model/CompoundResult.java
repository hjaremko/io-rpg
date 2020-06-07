package pl.uj.io.cuteanimals.model;

import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class CompoundResult implements IResult {
    private final List<IResult> results;

    public CompoundResult(List<IResult> results) {
        this.results = results;
    }

    @Override
    public String getMessage() {
        return String.join("\n", (CharSequence) results);
    }

    @Override
    public void setMessage(String message) {
        results.add(new Result(message));
    }

    @Override
    public Color getColor() {
        return Color.NORMAL;
    }

    public void addResult(IResult result) {
        results.add(result);
    }

    public List<IResult> getResults() {
        return results;
    }
}
