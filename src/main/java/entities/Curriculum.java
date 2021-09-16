package entities;

public enum Curriculum {
    JavaDeveloper("Java Developer"), AQA("Automate Quality Assurance");

    private String desc;

    Curriculum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
