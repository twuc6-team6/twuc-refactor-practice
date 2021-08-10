package com.twu.refactoring;

public class Direction {
    private final char direction;

    public Direction(char direction) {
        this.direction = direction;
    }

    String directions = "NESW";

    public Direction turnRight() {
        return turn(1);
    }

    public Direction turnLeft() {
        return turn(-1);
    }

    private Direction turn(int leftOrRight){
        int index = directions.indexOf(direction);
        if(index ==  -1){
            throw new IllegalArgumentException();
        }
        return new Direction(directions.charAt(((index+leftOrRight)+directions.length())%directions.length()));
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
