package pl.uj.io.cuteanimals.model.interfaces;

public interface ILocation {
  String getDescription();

  IAction getAvaliableActions();

  ICharacter getNPCs();

  IEquipment getItems();

  // ILocation getGates(); (?)
}
