package pl.uj.io.cuteanimals.model;

import java.util.List;
import java.util.stream.Collectors;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class CompoundResult implements IResult {
    private final List<IResult> results;
    private final Color color;

    public CompoundResult(List<IResult> results) {
        this.results = results;
        this.color = null;
    }

    public CompoundResult(List<IResult> results, Color color) {
        this.results = results;
        this.color = color;
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
        if (color == null) {
            return results.get(results.size() - 1).getColor();
        }

        return color;
    }

    public void addResult(IResult result) {
        results.add(result);
    }

    public List<IResult> getResults() {
        return results;
    }
}
