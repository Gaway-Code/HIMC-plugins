package pl.himc.api.store;

public abstract class Entry {

    private boolean changes;

    public Entry() {
        this.changes = true;
    }

    public boolean isChanges() {
        return this.changes;
    }

    public void changes() {
        this.changes = true;
    }

    public void setChanges(boolean changes) {
        this.changes = changes;
    }
}
