import { Request, Response } from "express";
import { EstablishmentService } from "../services/EstablishmentService";

class EstablishmentController {
    async create(req: Request, res: Response) {
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
            const establishment = service.execute(
                name,
                cnpj,
                address,
                phonenumber,
                qtmotospace,
                qtcarspace
            );

            await establishment.save();
            res.status(201).send({ data: establishment });
        } catch (error) {
            res.status(500).send(error);
        }
    }
}

export { EstablishmentController };
