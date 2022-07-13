import { EstablishmentModel } from "../models/EstablishmentModel";

class EstablishmentRepository {
    async create(
        name: string,
        cnpj: string,
        address: string,
        phonenumber: string,
        qtmotospace: number,
        qtcarspace: number
    ) {
        const establishment = new EstablishmentModel({
            name,
            cnpj,
            address,
            phonenumber,
            qtmotospace,
            qtcarspace
        });

        await establishment.save();
        return establishment;
    }

    async findAll() {
        const allEstablishments = await EstablishmentModel.find();

        return allEstablishments;
    }

    async updateOne(
        id: string,
        name: string,
        cnpj: string,
        address: string,
        phonenumber: string,
        qtmotospace: number,
        qtcarspace: number
    ) {
        const establishment = await EstablishmentModel.updateOne({
            id,
            name,
            cnpj,
            address,
            phonenumber,
            qtmotospace,
            qtcarspace
        });

        return establishment;
    }
}

export { EstablishmentRepository };
