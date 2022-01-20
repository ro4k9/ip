class ListOutOfBound extends Exception {
    public ListOutOfBound(int num) {
        super("OOPS!!! You exceeded the bound!!\nTask " + num + " does not exist.");
    }
}
