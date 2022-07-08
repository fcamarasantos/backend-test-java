import { model, Schema } from "mongoose";

interface VehicleTypes {
    id: string;
    brand: string;
    model: string;
    color: string;
    plate: string;
    type: string;
}

const VehicleSchema = new Schema<VehicleTypes>({
    id: String,
    brand: { type: String, require: true },
    color: { type: String, require: true },
    model: { type: String, require: true },
    plate: { type: String, require: true, unique: true },
    type: { type: String, require: true }
});

export const VehicleModel = model<VehicleTypes>("Vehicle", VehicleSchema);
