package br.com.brunobrolesi.parking.model;

public enum ParkingSpaceState {
    FREE(1, "livre"),
    BUSY(2, "ocupado");

    private int id;
    private String  description;

    ParkingSpaceState(int id, String  description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static ParkingSpaceState toEnum(Integer id) {
        if (id == null) {
            return null;
        }

        for (ParkingSpaceState type: ParkingSpaceState.values()) {
            if(id.equals(type.getId())) {
                return type;
            }
        }

        throw new IllegalArgumentException("Estado de vaga invalido: " + id);
    }
}
