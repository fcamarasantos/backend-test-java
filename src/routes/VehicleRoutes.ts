import express from "express";
import { VehicleController } from "../controllers/VehicleController";

export const vehicleRoutes = express.Router();

const vehicleController = new VehicleController();

vehicleRoutes.post("/vehicles", vehicleController.createVehicle);
