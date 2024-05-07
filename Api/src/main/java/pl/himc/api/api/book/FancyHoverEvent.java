package pl.himc.api.api.book;

import java.util.ArrayList;

public class FancyHoverEvent {

    private ArrayList<FancyTextComponent> text = new ArrayList<>();

    public FancyHoverEvent(FancyTextComponent fancyTextComponent) {
        fancyTextComponent.clearEvents();
        this.text.add(fancyTextComponent);
    }

    public static FancyHoverEvent fancyHoverEvent(FancyTextComponent fancyTextComponent) {
        return new FancyHoverEvent(fancyTextComponent);
    }

    public void addText(FancyTextComponent fancyTextComponent) {
        fancyTextComponent.clearEvents();
        this.text.add(fancyTextComponent);
    }

    public ArrayList<FancyTextComponent> getText() {
        return text;
    }
}