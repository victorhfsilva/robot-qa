import { api } from "../lib/api";
import { RobotModel } from "../models/robotModel";

export const getRobots = async () => {
    const response = await api.get<RobotModel[]>("/api/v1/robots")
    return response.data
} 