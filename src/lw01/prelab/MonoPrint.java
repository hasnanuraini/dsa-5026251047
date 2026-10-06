public class MonoPrint extends PrintJob {

    public MonoPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int printingCost;

        if (pages <= 10) {
            printingCost = pages * 1000;
        } else {
            printingCost = (10 * 1000) + ((pages - 10) * 500);
        }

        return printingCost + 1000;
    }

    @Override
    public String label() {
        return "Mono";
    }
}