package lw01.prelab;

public class ColourPrint extends PrintJob {
    private static final int FIRST_TIER_LIMIT = 10;
    private static final int FIRST_TIER_RATE = 1500;
    private static final int SECOND_TIER_RATE = 1000;
    private static final int SETUP_FEE = 2000;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int firstTierPages = Math.min(pages, FIRST_TIER_LIMIT);
        int secondTierPages = Math.max(0, pages - FIRST_TIER_LIMIT);
        return (firstTierPages * FIRST_TIER_RATE)
                + (secondTierPages * SECOND_TIER_RATE)
                + SETUP_FEE;
    }

    @Override
    public String label() {
        return "Colour";
    }
}