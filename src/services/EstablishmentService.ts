import { EstablishmentModel } from "../models/EstablishmentModel";

class EstablishmentService {
    execute(
        name: string,
        cnpj: string,
        address: string,
        phonenumber: number,
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

        return establishment;
    }
}

export { EstablishmentService };
