package domain.entities;

import domain.interfaces.ContainerActions;

public class ContainerObj <T extends ContainerActions> {
    private T item;

    public ContainerObj(T item) {
        this.item = item;
    }

    public void add(Object item) {
        this.item = (T) item;
    }

    public T getItem() {
        return this.item;
    }

    public void deleteItem() {
        this.item = null;
    }

    public boolean isEmpty() {
        return this.item == null;
    }
}

