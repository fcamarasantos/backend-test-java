import express from "express";
import { EstablishmentController } from "./controllers/EstablishmentController";

export const routes = express.Router();

const establishmentController = new EstablishmentController();

routes.post("/establishments", establishmentController.create);
