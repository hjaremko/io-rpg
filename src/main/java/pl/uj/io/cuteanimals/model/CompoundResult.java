package pl.uj.io.cuteanimals.model;

import java.util.List;
import java.util.stream.Collectors;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class CompoundResult implements IResult {
    private final List<IResult> results;

    public CompoundResult(List<IResult> results) {
        this.results = results;
    }

    @Override
    public String getMessage() {
        return results.stream().map(IResult::getMessage).collect(Collectors.joining("\n"));
    }

    @Override
    public void setMessage(String message) {
        results.add(new Result(message));
    }

    @Override
    public Color getColor() {
        return results.get(0).getColor();
    }

    public void addResult(IResult result) {
        results.add(result);
    }

    public List<IResult> getResults() {
        return results;
    }
}
