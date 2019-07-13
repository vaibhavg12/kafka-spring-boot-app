package org.vb.practice.kafka.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * POJO defining a greet message.
 */
public class Greet implements Serializable {

    private String msg;
    private String name;

    /**
     * Constructor.
     */
    public Greet() {
        //empty constructor
    }

    /**
     * Constructor.
     *
     * @param msg  the message
     * @param name the name
     */
    public Greet(String msg, String name) {
        this.msg = msg;
        this.name = name;
    }

    /**
     * Returns the message
     *
     * @return the message
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the message
     *
     * @param msg the message
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greet greet = (Greet) o;
        return Objects.equals(msg, greet.msg) &&
                Objects.equals(name, greet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msg, name);
    }

    @Override
    public String toString() {
        return msg + ", " + name + "!";
    }
}
