import { EstablishmentRepository } from "../repositories/EstablishmentRepository";

class EstablishmentService {
    create(
        name: string,
        cnpj: string,
        address: string,
        phonenumber: string,
        qtmotospace: number,
        qtcarspace: number
    ) {
        const establishment = new EstablishmentRepository();

        return establishment.create(
            name,
            cnpj,
            address,
            phonenumber,
            qtmotospace,
            qtcarspace
        );
    }

    listAll() {
        const establishment = new EstablishmentRepository();

        return establishment.findAll();
    }
}

export { EstablishmentService };
