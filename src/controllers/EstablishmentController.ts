import { Request, Response } from "express";
import { EstablishmentService } from "../services/EstablishmentService";

class EstablishmentController {
    async createEstablishment(req: Request, res: Response) {
        try {
            const {
                name,
                cnpj,
                address,
                phonenumber,
                qtmotospace,
                qtcarspace
            } = req.body;

            const service = new EstablishmentService();
            const establishment = await service.create(
                name,
                cnpj,
                address,
                phonenumber,
                qtmotospace,
                qtcarspace
            );

            return res.status(201).send({ data: establishment });
        } catch (error) {
            return res.status(500).send(error);
        }
    }

    async listAllEstablishments(req: Request, res: Response) {
        try {
            const service = new EstablishmentService();

            const establishments = await service.listAll();

            return res.status(200).send({ data: establishments });
        } catch (error) {
            return res.status(500).send(error);
        }
    }
}

export { EstablishmentController };
