package pl.himc.guilds.base;

public abstract class AbstractBasic implements Basic {

    private boolean wasChanged = false;

    @Override
    public void changes() {
        this.wasChanged = true;
    }

    @Override
    public boolean isChanges() {
        return this.wasChanged;
    }

    @Override
    public void setChanges(boolean bool) {
        this.wasChanged = bool;
    }
}