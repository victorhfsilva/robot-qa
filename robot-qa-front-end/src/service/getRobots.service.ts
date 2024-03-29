import { api } from "../lib/api";
import { RobotModel } from "../models/robotModel";

export const getRobots = async (token: string) => {
    const response = await api.get<RobotModel[]>(
        "/api/v1/robots", {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
    return response.data
} 