import express from "express";
import { routes } from "./routes";
import { runDb } from "./config/dbConnection";

const app = express();
const port = 8080;

app.use(express.json(), routes);

app.listen(port, () => {
    console.log(`Server is running on Port http://localhost:${port}`);
});

runDb().catch((err) => console.log(err));
