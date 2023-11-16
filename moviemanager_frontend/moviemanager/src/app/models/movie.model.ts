import { Actor } from "./actor.model";
import { Director } from "./director.model";

export interface Movie {
    id?: number;
    title: string;
    productionYear: number;
    description: string;
    imgPath?: string;
    directorId?: number;
    director?: Director;
    actors?: Actor[];
}
