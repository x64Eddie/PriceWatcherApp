class ConsoleUI implements UI{
    
    /**
     * We are going to draw the welcome screen in the console, this is the first method
     * that is going be called when the program starts.
     */
    @override
    public void onDraw() {
        System.out.println("Welcome to Price Watcher!!\n");
    }
    @override
    public void addItem(Item item) {
        System.out.println("Name:\t"+item.getName()+"\nURL:\t"+item.getUrl()+
        "\nPrice:\t"+item.getCurrentPrice()+"\nChange:\t"+item.getPriceChange()+"\nAdded:\t"+item.getDate());
    }
}