package data;
/// 图书类别
public enum BookType {
    LITERATURE_FICTION("文学"),
    SCIENCE_TECHNOLOGY_PROGRAMMING("科学"),
    ECONOMICS_MANAGEMENT("经济"),
    CHILDREN_S_BOOKS("儿童"),
    E_BOOK("电子");

    private final String displayName;

    BookType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
