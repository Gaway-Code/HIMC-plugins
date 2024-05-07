package pl.himc.api.api.book;

public class FancyClickEvent {

    public final static String runCommand = "run_command";
    public final static String changePage = "change_page";

    private String clickEvent;
    private String value;

    public FancyClickEvent(String event, String value) {
        this.clickEvent = event;
        this.value = value;
    }

    public static FancyClickEvent fancyClickEvent(String event, String value) {
        return new FancyClickEvent(event, value);
    }

    public String getClickEvent() {
        return clickEvent;
    }

    public String getValue() {
        return value;
    }
}