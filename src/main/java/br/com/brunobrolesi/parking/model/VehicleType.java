package br.com.brunobrolesi.parking.model;

public enum VehicleType {
    CARRO(1, "carro"),
    MOTO(2, "moto");

    private int id;
    private String  description;

    VehicleType(int id, String  description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static VehicleType toEnum(Integer id) {
        if (id == null) {
            return null;
        }

        for (VehicleType type: VehicleType.values()) {
            if(id.equals(type.getId())) {
                return type;
            }
        }

        throw new IllegalArgumentException("Código de veículo invalido: " + id);
    }
}
