import express from "express";
import { EstablishmentController } from "../controllers/EstablishmentController";

export const establishmentRoutes = express.Router();

const establishmentController = new EstablishmentController();

establishmentRoutes.post(
    "/establishments",
    establishmentController.createEstablishment
);
