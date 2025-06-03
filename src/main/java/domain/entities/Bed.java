package domain.entities;

import domain.interfaces.ContainerActions;

public class Bed implements ContainerActions {
    public Bed() {};

    @Override
    public boolean isPet() {
        return false;
    }
}
