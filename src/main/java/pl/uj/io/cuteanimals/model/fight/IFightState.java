package pl.uj.io.cuteanimals.model.fight;

import pl.uj.io.cuteanimals.model.interfaces.IResult;

public interface IFightState {
    IResult attack();

    IResult contrAttack();
}
