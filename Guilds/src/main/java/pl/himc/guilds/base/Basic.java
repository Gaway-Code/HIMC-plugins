package pl.himc.guilds.base;

public interface Basic {

    void changes();

    void setChanges(boolean bool);

    boolean isChanges();

    BasicType getType();

    String getName();

}