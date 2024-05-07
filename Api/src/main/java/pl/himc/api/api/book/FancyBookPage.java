package pl.himc.api.api.book;

import java.util.ArrayList;

public class FancyBookPage {

    private ArrayList<FancyTextComponent> text = new ArrayList<>();

    public FancyBookPage() {
    }

    public FancyBookPage(FancyTextComponent text) {
        this.text.add(text);
    }

    public static FancyBookPage fancyBookPage() {
        return new FancyBookPage();
    }

    public static FancyBookPage fancyBookPage(FancyTextComponent text) {
        return new FancyBookPage(text);
    }

    public void addText(FancyTextComponent text) {
        this.text.add(text);
    }

    public ArrayList<FancyTextComponent> getText() {
        return text;
    }
}