package com.twu.refactoring;

public class Direction {
    private final char direction;

    public Direction(char direction) {
        this.direction = direction;
    }

    String directions = "NESW";

    public Direction turnRight() {
        int index = directions.indexOf(direction);
        if(index ==  -1){
            throw new IllegalArgumentException();
        }
        return new Direction(directions.charAt((index+1)%directions.length()));

    }

    public Direction turnLeft() {
        int index = directions.indexOf(direction);
        if(index ==  -1){
            throw new IllegalArgumentException();
        }
        return new Direction(directions.charAt(((index-1)+directions.length())%directions.length()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        if (direction != direction1.direction) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) direction;
    }

    @Override
    public String toString() {
        return "Direction{direction=" + direction + '}';
    }
}
