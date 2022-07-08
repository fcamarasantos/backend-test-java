import { Request, Response } from "express";
import { VehicleService } from "../services/VehicleService";

class VehicleController {
    async create(req: Request, res: Response) {
        try {
            const { brand, color, model, plate, type } = req.body;

            const service = new VehicleService();
            const vehicle = service.execute(brand, color, model, plate, type);

            await vehicle.save();
            res.status(201).send({ data: vehicle });
        } catch (error) {
            res.status(500).send(error);
        }
    }
}

export { VehicleController };
