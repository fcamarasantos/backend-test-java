import express from "express";
import { runDb } from "./services/connection";

const app = express();
const port = 8080;

app.get("/", (req, res) => {
    res.send("Hellow World");
});

app.listen(port, () => {
    console.log(`Server is running on Port http://localhost:${port}`);
});

runDb().catch((err) => console.log(err));
