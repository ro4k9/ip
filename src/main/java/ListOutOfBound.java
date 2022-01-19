class ListOutOfBound extends Exception {
    public ListOutOfBound(int num) {
        super("Task " + num + " does not exist");
    }
}
