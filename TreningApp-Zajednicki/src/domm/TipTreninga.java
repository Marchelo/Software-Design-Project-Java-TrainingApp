package domm;

public enum TipTreninga {
    INDIVIDUALNI("Individualni trening"),
    GRUPNI("Grupni trening"),
    PERSONALNI("Personalni trening"),
    ONLINE("Online trening");

    private final String label;

    TipTreninga(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}