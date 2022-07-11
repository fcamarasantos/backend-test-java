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
}

export { EstablishmentRepository };
