package pl.polsl.modelpackage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *This class represents a pair of an Integer key and a Double value.
 * @author Piotr
 * @version 1.0
 */
public class IntegerDoublePair {
    /**
     * The Integer key.
     */
    private Integer key;
    /**
     * The Double value.
     */
    private Double value;
    /**
     * Constructs a new IntegerDoublePair with the specified key and value.
     *
     * @param key   The Integer key.
     * @param value The Double value.
     */
    public IntegerDoublePair(Integer key, Double value) {
        this.key = key;
        this.value = value;
    }
    /**
     * Gets the key of this pair.
     *
     * @return The Integer key.
     */
    public Integer getKey() {
        return key;
    }
    /**
     * Gets the value of this pair.
     *
     * @return The Double value.
     */
    public Double getValue() {
        return value;
    }
    /**
     * Sets the value of this pair.
     *
     * @param value The Double value to set.
     */
    public void setValue(Double value) {
        this.value = value;
    }
}
