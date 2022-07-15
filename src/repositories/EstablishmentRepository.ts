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
        const establishment = await EstablishmentModel.where({
            _id: id
        }).updateOne({
            name,
            cnpj,
            address,
            phonenumber,
            qtmotospace,
            qtcarspace
        });

        return establishment;
    }

    async deleteOne(id: string) {
        const establishment = await EstablishmentModel.deleteOne({ _id: id });

        return establishment;
    }
}

export { EstablishmentRepository };
