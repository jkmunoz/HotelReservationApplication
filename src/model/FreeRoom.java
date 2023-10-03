package model;

public class FreeRoom extends Room {
    public Double price;
    public FreeRoom(String roomNumber, RoomType roomType, Double roomPrice) {
        super(roomNumber, roomPrice, roomType);
        this.price = 0.0;
    }

    @Override
    public String toString() {
        return "Enjoy a night, on us!";
    }
}
