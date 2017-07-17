package model;

public class Chain implements DominoModel {
    private int id;
    private String resultChain;

    public Chain(int id, String resultChain) {
        this.id = id;
        this.resultChain = resultChain;
    }

    public int getId() {
        return id;
    }

    public String getResultChain() {
        return resultChain;
    }
}
