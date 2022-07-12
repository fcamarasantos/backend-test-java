import express from "express";
import { establishmentRoutes } from "./routes/EstablishmentRoutes";
import { vehicleRoutes } from "./routes/VehicleRoutes";

export const routes = express.Router();

routes.use(establishmentRoutes, vehicleRoutes);
