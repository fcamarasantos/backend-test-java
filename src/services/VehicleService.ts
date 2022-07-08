import { VehicleModel } from "../models/VehicleModel";

class VehicleService {
    execute(
        brand: string,
        color: string,
        model: string,
        plate: string,
        type: string
    ) {
        const vehicle = new VehicleModel({
            brand,
            color,
            model,
            plate,
            type
        });
        return vehicle;
    }
}

export { VehicleService };
