import { model, Schema } from "mongoose";

interface EstablishmentTypes {
    id: string;
    name: string;
    cnpj: string;
    address: string;
    phonenumber: string;
    qtmotospace: number;
    qtcarspace: number;
}

const EstablishmentSchema = new Schema<EstablishmentTypes>({
    id: String,
    name: { type: String, required: true },
    cnpj: { type: String, required: true, unique: true },
    address: { type: String, required: true },
    phonenumber: { type: String, required: true },
    qtmotospace: { type: Number, required: true },
    qtcarspace: { type: Number, required: true }
});

export const EstablishmentModel = model<EstablishmentTypes>(
    "Establishment",
    EstablishmentSchema
);
